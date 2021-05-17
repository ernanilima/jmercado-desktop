package br.com.ernanilima.jmercado.repository;

import br.com.ernanilima.jmercado.model.GrupoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoUsuarioRepository extends JpaRepository<GrupoUsuario, Integer> {
}
