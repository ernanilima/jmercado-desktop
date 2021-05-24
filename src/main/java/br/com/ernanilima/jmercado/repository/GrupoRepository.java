package br.com.ernanilima.jmercado.repository;

import br.com.ernanilima.jmercado.model.Departamento;
import br.com.ernanilima.jmercado.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    @Transactional(readOnly = true)
    List<Grupo> findAllBymDepartamento(Departamento mDepartamento);
}
