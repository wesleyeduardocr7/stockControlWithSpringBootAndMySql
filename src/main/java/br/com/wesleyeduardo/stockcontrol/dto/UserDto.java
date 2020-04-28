package br.com.wesleyeduardo.stockcontrol.dto;

import br.com.wesleyeduardo.stockcontrol.model.User;
import org.springframework.data.domain.Page;


public class UserDto {

    private Long id;
    private String name;
    private String cpf;
    private String login;

    public UserDto (User user) {

        this.id = user.getId();
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.login = user.getLogin();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getLogin() {
        return login;
    }

    public static Page<UserDto> converter(Page<User> users) {
        return users.map(UserDto::new);
    }
}
