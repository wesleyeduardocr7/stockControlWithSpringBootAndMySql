package br.com.wesleyeduardo.stockcontrol.controller;

import br.com.wesleyeduardo.stockcontrol.dto.ClientDto;
import br.com.wesleyeduardo.stockcontrol.form.ClientForm;
import br.com.wesleyeduardo.stockcontrol.form.ClientFormUpdate;
import br.com.wesleyeduardo.stockcontrol.model.Client;
import br.com.wesleyeduardo.stockcontrol.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public Page<ClientDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC,
                                 page = 0, size = 10) Pageable pagination) {

        Page<Client> clients = clientRepository.findAll(pagination);

        return ClientDto.converter(clients);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClientDto> create(@RequestBody @Valid ClientForm clientForm, UriComponentsBuilder uriBuilder){

       Client client = clientForm.toConvert();

       clientRepository.save(client);

       URI uri = uriBuilder.path("/Clients/{id}").buildAndExpand(client.getId()).toUri();

       return ResponseEntity.created(uri).body(new ClientDto(client));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long id){

        Optional<Client> client = clientRepository.findById(id);

        if(client.isPresent()){
            return ResponseEntity.ok(new ClientDto(client.get()));
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody @Valid ClientFormUpdate clientFormUpdate){

        Optional<Client> optional = clientRepository.findById(id);

        if(optional.isPresent()){
            Client Client =  clientFormUpdate.update(id,clientRepository);
            return ResponseEntity.ok(new ClientDto(Client));
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity delete(@PathVariable Long id){

        Optional<Client> optional = clientRepository.findById(id);

        if(optional.isPresent()){

            clientRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
