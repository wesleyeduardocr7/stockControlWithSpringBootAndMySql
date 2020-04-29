package br.com.wesleyeduardo.stockcontrol.dto;
import br.com.wesleyeduardo.stockcontrol.model.Product;
import org.springframework.data.domain.Page;

public class ProductDto {

    private Long id;
    private String name;
    private String sequential;
    private String barcode;
    private String description;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.sequential = product.getSequential();
        this.barcode = product.getBarcode();
        this.description = product.getDescription();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSequential() {
        return sequential;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getDescription() {
        return description;
    }

    public static Page<ProductDto> converter(Page<Product> products) {
        return products.map(ProductDto::new);
    }
}
