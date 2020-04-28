package br.com.wesleyeduardo.stockcontrol.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "iduser")
	private Long id;

	private String name;

	@Column(unique = true)
	private String cpf;

	@Column(unique = true)
	private String login;

	private String password;

	public User() {}

	public User(String name, String cpf, String login, String password) {
		this.name = name;
		this.cpf = cpf;
		this.login = login;
		this.password = password;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
