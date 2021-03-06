package br.com.wesleyeduardo.stockcontrol.repository;

import br.com.wesleyeduardo.stockcontrol.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

        Optional<User> findByLogin(String login);

}
