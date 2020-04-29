package br.com.wesleyeduardo.stockcontrol.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails, GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "iduser")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "cpf", unique = true)
	private String cpf;

	@Column(name = "login",unique = true)
	private String login;

	@Column(name = "password")
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

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getAuthority() {
		return login;
	}
}
