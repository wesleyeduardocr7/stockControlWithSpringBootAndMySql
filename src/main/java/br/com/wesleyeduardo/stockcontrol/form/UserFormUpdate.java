package br.com.wesleyeduardo.stockcontrol.form;

import br.com.wesleyeduardo.stockcontrol.model.User;
import br.com.wesleyeduardo.stockcontrol.repository.UserRepository;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserFormUpdate {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String login;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User update(Long id, UserRepository userRepository) {

        User user = userRepository.getOne(id);

        user.setName(this.name);
        user.setLogin(this.login);

        return  user;

    }
}
