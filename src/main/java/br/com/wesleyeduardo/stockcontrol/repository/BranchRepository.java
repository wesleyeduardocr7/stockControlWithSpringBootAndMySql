package br.com.wesleyeduardo.stockcontrol.repository;

import br.com.wesleyeduardo.stockcontrol.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BranchRepository extends JpaRepository<Branch,Long> {

        Page<Branch> findByState(String state, Pageable paginacao);

}
