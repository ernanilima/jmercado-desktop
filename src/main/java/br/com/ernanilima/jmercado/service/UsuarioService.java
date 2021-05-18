package br.com.ernanilima.jmercado.service;

import br.com.ernanilima.jmercado.model.Usuario;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UsuarioService {
    Usuario gravar(Usuario departamento);
    Usuario getPorId(int codigo);
    void remover(int codigo);
    void remover(Usuario departamento);
    List<Usuario> listarTudo();
    CompletableFuture<List<Usuario>> listarTudoAsinc();
}
