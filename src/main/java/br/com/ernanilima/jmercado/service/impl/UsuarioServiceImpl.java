package br.com.ernanilima.jmercado.service.impl;

import br.com.ernanilima.jmercado.model.Usuario;
import br.com.ernanilima.jmercado.repository.UsuarioRepository;
import br.com.ernanilima.jmercado.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired private UsuarioRepository rUsuario;

    @Override
    public Usuario gravar(Usuario departamento) {
        return rUsuario.save(departamento);
    }

    @Override
    public Usuario getPorId(int codigo) {
        Optional<Usuario> model = rUsuario.findById(codigo);
        return model.orElse(null);
    }

    @Override
    public void remover(int codigo) {
        try {
            getPorId(codigo);
            rUsuario.deleteById(codigo);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public void remover(Usuario departamento) {
        try {
            rUsuario.delete(departamento);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public List<Usuario> listarTudo() {
        return rUsuario.findAll();
    }

    @Async
    @Override
    public CompletableFuture<List<Usuario>> listarTudoAsinc() {
        final List<Usuario> list = rUsuario.findAll();
        return CompletableFuture.completedFuture(list);
    }
}
