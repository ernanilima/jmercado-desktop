package br.com.ernanilima.jmercado.repository;

import br.com.ernanilima.jmercado.model.Grupo;
import br.com.ernanilima.jmercado.model.Subgrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubgrupoRepository extends JpaRepository<Subgrupo, Integer> {
    @Transactional(readOnly = true)
    List<Subgrupo> findAllBymGrupo(Grupo mGrupo);

    @Transactional(readOnly = true)
    @Query("SELECT sgr FROM Subgrupo sgr WHERE sgr.codigo = :codigoprincipal AND sgr.mGrupo.codigo = :codigoassociado")
    Optional<Subgrupo> findByCodigoAndMGrupo(@Param("codigoprincipal") int codigoPrincipal, @Param("codigoassociado") int codigoAssociado);
}
