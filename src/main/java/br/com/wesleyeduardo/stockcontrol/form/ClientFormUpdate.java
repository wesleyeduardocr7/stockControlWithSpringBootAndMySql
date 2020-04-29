package br.com.wesleyeduardo.stockcontrol.form;

import br.com.wesleyeduardo.stockcontrol.model.Client;
import br.com.wesleyeduardo.stockcontrol.repository.ClientRepository;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClientFormUpdate {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Client update(Long id, ClientRepository clientRepository) {

        Client client = clientRepository.getOne(id);

        client.setName(this.name);

        return  client;

    }
}
