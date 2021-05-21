package br.com.ernanilima.jmercado.repository;

import br.com.ernanilima.jmercado.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
}
