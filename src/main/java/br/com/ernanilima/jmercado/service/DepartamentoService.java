package br.com.ernanilima.jmercado.service;

import br.com.ernanilima.jmercado.model.Departamento;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DepartamentoService {
    public Departamento gravar(Departamento departamento);
    public Departamento getPorId(int codigo);
    public void remover(int codigo);
    public void remover(Departamento departamento);
    public List<Departamento> listarTudo();
    public CompletableFuture<List<Departamento>> listarTudoAsinc();
}
