package br.com.wesleyeduardo.stockcontrol.controller;

import br.com.wesleyeduardo.stockcontrol.dto.ProductDto;
import br.com.wesleyeduardo.stockcontrol.form.ProductForm;
import br.com.wesleyeduardo.stockcontrol.form.ProductFormUpdate;
import br.com.wesleyeduardo.stockcontrol.model.Product;
import br.com.wesleyeduardo.stockcontrol.repository.ProductRepository;
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
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository ;

    @GetMapping
    public Page<ProductDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC,
                                 page = 0, size = 10) Pageable pagination) {

        Page<Product> products = productRepository .findAll(pagination);

        return ProductDto.converter(products);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductForm productForm, UriComponentsBuilder uriBuilder){

       Product product = productForm.toConvert();

       productRepository .save(product);

       URI uri = uriBuilder.path("products/{id}").buildAndExpand(product.getId()).toUri();

       return ResponseEntity.created(uri).body(new ProductDto(product));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){

        Optional<Product> product = productRepository .findById(id);

        if(product.isPresent()){
            return ResponseEntity.ok(new ProductDto(product.get()));
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody @Valid ProductFormUpdate productFormUpdate){

        Optional<Product> optional = productRepository .findById(id);

        if(optional.isPresent()){
            Product Product =  productFormUpdate.update(id,productRepository );
            return ResponseEntity.ok(new ProductDto(Product));
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity delete(@PathVariable Long id){

        Optional<Product> optional = productRepository .findById(id);

        if(optional.isPresent()){

            productRepository .deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
