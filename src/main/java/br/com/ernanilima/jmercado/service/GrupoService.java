package br.com.ernanilima.jmercado.service;

import br.com.ernanilima.jmercado.model.Grupo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GrupoService extends IService {
    Grupo gravar(Grupo departamento);
    Grupo getPorId(int codigo);
    void remover(int codigo);
    void remover(Grupo departamento);
    List<Grupo> listarTudo();
    CompletableFuture<List<Grupo>> listarTudoAsinc();
}
