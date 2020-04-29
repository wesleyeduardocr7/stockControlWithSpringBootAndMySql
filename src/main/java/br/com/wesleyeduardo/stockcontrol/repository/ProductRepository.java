package br.com.wesleyeduardo.stockcontrol.repository;

import br.com.wesleyeduardo.stockcontrol.model.Product;
import br.com.wesleyeduardo.stockcontrol.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

        Optional<Product> findByName(String name);

}
