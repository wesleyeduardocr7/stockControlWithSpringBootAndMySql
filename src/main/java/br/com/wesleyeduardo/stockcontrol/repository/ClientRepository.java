package br.com.wesleyeduardo.stockcontrol.repository;

import br.com.wesleyeduardo.stockcontrol.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {}
