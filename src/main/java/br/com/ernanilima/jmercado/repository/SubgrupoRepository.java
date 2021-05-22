package br.com.ernanilima.jmercado.repository;

import br.com.ernanilima.jmercado.model.Subgrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubgrupoRepository extends JpaRepository<Subgrupo, Integer> {
}
