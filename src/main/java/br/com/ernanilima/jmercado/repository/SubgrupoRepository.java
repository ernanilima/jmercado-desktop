package br.com.ernanilima.jmercado.repository;

import br.com.ernanilima.jmercado.model.Grupo;
import br.com.ernanilima.jmercado.model.Subgrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubgrupoRepository extends JpaRepository<Subgrupo, Integer> {
    @Transactional(readOnly = true)
    List<Subgrupo> findAllBymGrupo(Grupo mGrupo);
}
