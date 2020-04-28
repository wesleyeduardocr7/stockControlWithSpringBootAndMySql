package br.com.wesleyeduardo.stockcontrol.form;

import br.com.wesleyeduardo.stockcontrol.model.User;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String name;

    @NotNull @NotEmpty @Length(min = 11)
    private String cpf;

    @NotNull @NotEmpty @Length(min = 5)
    private String login;

    @NotNull @NotEmpty @Length(min = 5)
    private String password;

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

    public User toConvert() {
        return new User(name,cpf,login,password);
    }
}
