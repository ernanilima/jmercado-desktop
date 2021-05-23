package br.com.ernanilima.jmercado.repository;

import br.com.ernanilima.jmercado.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Transactional(readOnly = true)
    Optional<Produto> findByCodigoBarras(long codigoBarras);
}
