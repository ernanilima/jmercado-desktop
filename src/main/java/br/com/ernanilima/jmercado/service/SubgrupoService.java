package br.com.ernanilima.jmercado.service;

import br.com.ernanilima.jmercado.model.Grupo;
import br.com.ernanilima.jmercado.model.Subgrupo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SubgrupoService extends IService {
    Subgrupo gravar(Subgrupo subgrupo);
    Subgrupo getPorId(int codigo);
    void remover(int codigo);
    void remover(Subgrupo subgrupo);
    List<Subgrupo> listarTudo();
    CompletableFuture<List<Subgrupo>> listarPorGrupoAsinc(Grupo mGrupo);
    CompletableFuture<List<Subgrupo>> listarTudoAsinc();
}
