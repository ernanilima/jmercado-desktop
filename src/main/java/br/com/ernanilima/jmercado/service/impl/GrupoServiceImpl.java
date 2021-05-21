package br.com.ernanilima.jmercado.service.impl;

import br.com.ernanilima.jmercado.model.Grupo;
import br.com.ernanilima.jmercado.repository.GrupoRepository;
import br.com.ernanilima.jmercado.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired private GrupoRepository rGrupo;

    @Override
    public Grupo gravar(Grupo departamento) {
        return rGrupo.save(departamento);
    }

    @Override
    public Grupo getPorId(int codigo) {
        Optional<Grupo> model = rGrupo.findById(codigo);
        return model.orElse(null);
    }

    @Override
    public void remover(int codigo) {
        try {
            getPorId(codigo);
            rGrupo.deleteById(codigo);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public void remover(Grupo departamento) {
        try {
            rGrupo.delete(departamento);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public List<Grupo> listarTudo() {
        return rGrupo.findAll();
    }

    @Async
    @Override
    public CompletableFuture<List<Grupo>> listarTudoAsinc() {
        final List<Grupo> list = rGrupo.findAll();
        return CompletableFuture.completedFuture(list);
    }
}
