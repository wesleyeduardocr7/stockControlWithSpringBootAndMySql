package br.com.wesleyeduardo.stockcontrol.form;

import br.com.wesleyeduardo.stockcontrol.model.Product;
import br.com.wesleyeduardo.stockcontrol.repository.ProductRepository;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductFormUpdate {

    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product update(Long id, ProductRepository productRepository) {

        Product product = productRepository.getOne(id);

        product.setName(this.name);
        product.setDescription(this.description);

        return  product;

    }
}
