package br.com.ernanilima.jmercado.service.impl;

import br.com.ernanilima.jmercado.model.Subgrupo;
import br.com.ernanilima.jmercado.repository.SubgrupoRepository;
import br.com.ernanilima.jmercado.service.SubgrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SubgrupoServiceImpl implements SubgrupoService {

    @Autowired private SubgrupoRepository rSubgrupo;

    @Override
    public Subgrupo gravar(Subgrupo departamento) {
        return rSubgrupo.save(departamento);
    }

    @Override
    public Subgrupo getPorId(int codigo) {
        Optional<Subgrupo> model = rSubgrupo.findById(codigo);
        return model.orElse(null);
    }

    @Override
    public void remover(int codigo) {
        try {
            getPorId(codigo);
            rSubgrupo.deleteById(codigo);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public void remover(Subgrupo departamento) {
        try {
            rSubgrupo.delete(departamento);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public List<Subgrupo> listarTudo() {
        return rSubgrupo.findAll();
    }

    @Async
    @Override
    public CompletableFuture<List<Subgrupo>> listarTudoAsinc() {
        final List<Subgrupo> list = rSubgrupo.findAll();
        return CompletableFuture.completedFuture(list);
    }
}
