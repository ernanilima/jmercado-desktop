package br.com.ernanilima.jmercado.service;

import br.com.ernanilima.jmercado.model.Produto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProdutoService extends IService {
    Produto gravar(Produto produto);
    Produto getPorId(int codigo);
    Produto getPorCodigoBarras(long codigoBarras);
    void remover(int codigo);
    void remover(Produto produto);
    List<Produto> listarTudo();
    CompletableFuture<List<Produto>> listarTudoAsinc();
}
