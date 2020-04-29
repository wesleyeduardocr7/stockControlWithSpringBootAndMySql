package br.com.wesleyeduardo.stockcontrol.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_client")
public class Client{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idclient")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;


	public Client() {}

	public Client(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


}
