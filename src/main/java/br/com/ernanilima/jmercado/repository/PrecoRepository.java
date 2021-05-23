package br.com.ernanilima.jmercado.repository;

import br.com.ernanilima.jmercado.model.Preco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecoRepository extends JpaRepository<Preco, Integer> {
}
