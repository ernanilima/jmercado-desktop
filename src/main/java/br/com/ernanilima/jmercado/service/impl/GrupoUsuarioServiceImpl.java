package br.com.ernanilima.jmercado.service.impl;

import br.com.ernanilima.jmercado.model.GrupoUsuario;
import br.com.ernanilima.jmercado.repository.GrupoUsuarioRepository;
import br.com.ernanilima.jmercado.service.GrupoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GrupoUsuarioServiceImpl implements GrupoUsuarioService {

    @Autowired private GrupoUsuarioRepository rGrupoUsuario;

    @Override
    public GrupoUsuario gravar(GrupoUsuario grupoUsuario) {
        return rGrupoUsuario.save(grupoUsuario);
    }

    @Override
    public GrupoUsuario getPorId(int codigo) {
        Optional<GrupoUsuario> model = rGrupoUsuario.findById(codigo);
        return model.orElse(null);
    }

    @Override
    public void remover(int codigo) {
        getPorId(codigo);
        try {
            rGrupoUsuario.deleteById(codigo);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public void remover(GrupoUsuario grupoUsuario) {
        try {
            rGrupoUsuario.delete(grupoUsuario);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public List<GrupoUsuario> listarTudo() {
        return rGrupoUsuario.findAll();
    }

    @Async
    @Override
    public CompletableFuture<List<GrupoUsuario>> listarTudoAsinc() {
        final List<GrupoUsuario> list = rGrupoUsuario.findAll();
        return CompletableFuture.completedFuture(list);
    }
}
