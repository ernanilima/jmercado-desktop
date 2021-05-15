package br.com.ernanilima.jmercado.service;

import br.com.ernanilima.jmercado.model.Departamento;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DepartamentoService extends IService {
    Departamento gravar(Departamento departamento);
    Departamento getPorId(int codigo);
    void remover(int codigo);
    void remover(Departamento departamento);
    List<Departamento> listarTudo();
    CompletableFuture<List<Departamento>> listarTudoAsinc();
}
