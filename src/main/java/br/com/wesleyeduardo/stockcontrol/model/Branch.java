package br.com.wesleyeduardo.stockcontrol.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_branch")
public class Branch{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "idbranch")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state",unique = true, nullable = false)
	private String state;

	@Column(name = "telephone", nullable = false)
	private String telephone;

	@Column(name = "manager", nullable = false)
	private String manager;

	public Branch() {}

	public Branch(String name, String street , String city, String state, String telephone, String manager){
		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.manager = manager;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
}
