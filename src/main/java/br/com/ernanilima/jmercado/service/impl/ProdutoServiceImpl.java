package br.com.ernanilima.jmercado.service.impl;

import br.com.ernanilima.jmercado.model.Produto;
import br.com.ernanilima.jmercado.repository.ProdutoRepository;
import br.com.ernanilima.jmercado.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired private ProdutoRepository rProduto;

    @Override
    public Produto gravar(Produto produto) {
        return rProduto.save(produto);
    }

    @Override
    public Produto getPorId(int codigo) {
        Optional<Produto> model = rProduto.findById(codigo);
        return model.orElse(null);
    }

    @Override
    public Produto getPorCodigoBarras(long codigoBarras) {
        return null;
    }

    @Override
    public void remover(int codigo) {
        try {
            getPorId(codigo);
            rProduto.deleteById(codigo);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public void remover(Produto produto) {
        try {
            rProduto.delete(produto);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public List<Produto> listarTudo() {
        return rProduto.findAll();
    }

    @Async
    @Override
    public CompletableFuture<List<Produto>> listarTudoAsinc() {
        final List<Produto> list = rProduto.findAll();
        return CompletableFuture.completedFuture(list);
    }
}
