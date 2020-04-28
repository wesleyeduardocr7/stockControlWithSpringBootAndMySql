package br.com.wesleyeduardo.stockcontrol.repository;

import br.com.wesleyeduardo.stockcontrol.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

        Page<User> findByName(String name, Pageable paginacao);

}
