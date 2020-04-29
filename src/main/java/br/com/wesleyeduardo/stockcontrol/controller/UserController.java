package br.com.wesleyeduardo.stockcontrol.controller;

import br.com.wesleyeduardo.stockcontrol.dto.UserDto;
import br.com.wesleyeduardo.stockcontrol.form.UserForm;
import br.com.wesleyeduardo.stockcontrol.form.UserFormUpdate;
import br.com.wesleyeduardo.stockcontrol.model.User;
import br.com.wesleyeduardo.stockcontrol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @Cacheable(value = "listUsers")
    public Page<UserDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC,
                                 page = 0, size = 10) Pageable pagination) {

        Page<User> users = userRepository.findAll(pagination);

        return UserDto.converter(users);

    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listUsers", allEntries = true)
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder){

       User user = userForm.toConvert();

       userRepository.save(user);

       URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

       return ResponseEntity.created(uri).body(new UserDto(user));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            return ResponseEntity.ok(new UserDto(user.get()));
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listUsers", allEntries = true)
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserFormUpdate userFormUpdate){

        Optional<User> optional = userRepository.findById(id);

        if(optional.isPresent()){
            User user =  userFormUpdate.update(id,userRepository);
            return ResponseEntity.ok(new UserDto(user));
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listUsers", allEntries = true)
    public  ResponseEntity delete(@PathVariable Long id){

        Optional<User> optional = userRepository.findById(id);

        if(optional.isPresent()){

            userRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
