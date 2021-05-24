package br.com.ernanilima.jmercado.service;

import br.com.ernanilima.jmercado.model.Departamento;
import br.com.ernanilima.jmercado.model.Grupo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GrupoService extends IService, IServiceAssociado {
    Grupo gravar(Grupo grupo);
    Grupo getPorId(int codigo);
    Grupo getAssociadoPorId(int codigoPrincipal, int codigoAssociado);
    void remover(int codigo);
    void remover(Grupo grupo);
    List<Grupo> listarTudo();
    CompletableFuture<List<Grupo>> listarPorDepartamentoAsinc(Departamento mDepartamento);
    CompletableFuture<List<Grupo>> listarTudoAsinc();
}
