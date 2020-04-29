package br.com.wesleyeduardo.stockcontrol.form;

import br.com.wesleyeduardo.stockcontrol.model.Product;
import br.com.wesleyeduardo.stockcontrol.repository.ProductRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductForm {

    @NotNull @NotEmpty @Length(min = 3)
    private String name;

    @NotNull @NotEmpty @Length(min = 3)
    private String sequential;

    @NotNull @NotEmpty @Length(min = 3)
    private String barcode;

    @NotNull @NotEmpty @Length(min = 3)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSequential() {
        return sequential;
    }

    public void setSequential(String sequential) {
        this.sequential = sequential;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product toConvert() {
        return new Product(name,sequential,barcode,description);
    }

    public Product update(Long id, ProductRepository productRepository) {

        Product product = productRepository.getOne(id);

        product.setName(this.name);
        product.setSequential(this.sequential);
        product.setBarcode(this.barcode);
        product.setDescription(this.description);

        return  product;

    }

}
