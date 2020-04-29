package br.com.wesleyeduardo.stockcontrol.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "idproduct")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "sequential", nullable = false)
	private String sequential;

	@Column(name = "barcode", nullable = false)
	private String barcode;

	@Column(name = "description", nullable = false)
	private String description;

	public Product() {}

	public Product(String name, String sequential , String barcode, String description){
		this.name = name;
		this.sequential = sequential;
		this.barcode = barcode;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

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
}
