package br.com.wesleyeduardo.stockcontrol.dto;

import br.com.wesleyeduardo.stockcontrol.model.Client;
import br.com.wesleyeduardo.stockcontrol.model.User;
import org.springframework.data.domain.Page;


public class ClientDto {

    private Long id;
    private String name;
    private String cpf;

    public ClientDto(Client client) {

        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
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


    public static Page<ClientDto> converter(Page<Client> clients){
        return clients.map(ClientDto::new);
    }
}
