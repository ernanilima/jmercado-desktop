package br.com.ernanilima.jmercado.repository;

import br.com.ernanilima.jmercado.model.Departamento;
import br.com.ernanilima.jmercado.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    @Transactional(readOnly = true)
    List<Grupo> findAllBymDepartamento(Departamento mDepartamento);

    @Transactional(readOnly = true)
    @Query("SELECT gru FROM Grupo gru WHERE gru.codigo = :codigoprincipal AND gru.mDepartamento.codigo = :codigoassociado")
    Optional<Grupo> findByCodigoAndMDepartamento(@Param("codigoprincipal") int codigoPrincipal, @Param("codigoassociado") int codigoAssociado);
    //Optional<Grupo> findByCodigoAndMDepartamento(int codigo, Departamento mDepartamento);
}
