package br.com.ernanilima.jmercado.service;

import br.com.ernanilima.jmercado.model.GrupoUsuario;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GrupoUsuarioService extends IService {
    GrupoUsuario gravar(GrupoUsuario usuario);
    GrupoUsuario getPorId(int codigo);
    void remover(int codigo);
    void remover(GrupoUsuario grupoUsuario);
    List<GrupoUsuario> listarTudo();
    CompletableFuture<List<GrupoUsuario>> listarTudoAsinc();
}
