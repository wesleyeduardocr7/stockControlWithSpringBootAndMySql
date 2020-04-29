package br.com.wesleyeduardo.stockcontrol.form;

import br.com.wesleyeduardo.stockcontrol.model.Client;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClientForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String name;

    @NotNull @NotEmpty @Length(min = 11)
    private String cpf;

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

    public Client toConvert() {
        return new Client(name,cpf);
    }
}
