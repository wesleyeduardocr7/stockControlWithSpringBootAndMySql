package br.com.wesleyeduardo.stockcontrol.controller;

import br.com.wesleyeduardo.stockcontrol.dto.BranchDto;
import br.com.wesleyeduardo.stockcontrol.form.BranchForm;
import br.com.wesleyeduardo.stockcontrol.model.Branch;
import br.com.wesleyeduardo.stockcontrol.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/branchs")
public class BranchController {

    @Autowired
    private BranchRepository branchRepository;

    @GetMapping
    @Cacheable(value = "listBranchs")
    public Page<BranchDto> list(@RequestParam(required = false) String state,
                                @PageableDefault(sort = "id", direction = Sort.Direction.DESC,
                                 page = 0, size = 10) Pageable pagination) {
        if (state == null) {
            Page<Branch> branchs = branchRepository.findAll(pagination);
            return BranchDto.converter(branchs);
        } else {
            Page<Branch> branchs = branchRepository.findByState(state, pagination);
            return BranchDto.converter(branchs);
        }

    }

    @PostMapping
    @Transactional
    public ResponseEntity<BranchDto> create(@RequestBody @Valid BranchForm branchForm, UriComponentsBuilder uriBuilder){

       Branch branch = branchForm.toConvert();

       branchRepository.save(branch);

       URI uri = uriBuilder.path("/branchs/{id}").buildAndExpand(branch.getId()).toUri();

       return ResponseEntity.created(uri).body(new BranchDto(branch));
    }


    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranch(@PathVariable Long id){

        Optional<Branch> branch = branchRepository.findById(id);

        if(branch.isPresent()){
            return ResponseEntity.ok(new BranchDto(branch.get()));
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<BranchDto> update(@PathVariable Long id, @RequestBody @Valid BranchForm branchForm){

        Optional<Branch> optional = branchRepository.findById(id);

        if(optional.isPresent()){
            Branch branch =  branchForm.update(id,branchRepository);
            return ResponseEntity.ok(new BranchDto(branch));
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity delete(@PathVariable Long id){

        Optional<Branch> optional = branchRepository.findById(id);

        if(optional.isPresent()){

            branchRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
