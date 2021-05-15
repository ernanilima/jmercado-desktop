package br.com.ernanilima.jmercado.service.impl;

import br.com.ernanilima.jmercado.model.Departamento;
import br.com.ernanilima.jmercado.repository.DepartamentoRepository;
import br.com.ernanilima.jmercado.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired private DepartamentoRepository rDepartamento;

    @Override
    public Departamento gravar(Departamento departamento) {
        return rDepartamento.save(departamento);
    }

    @Override
    public Departamento getPorId(int codigo) {
        Optional<Departamento> model = rDepartamento.findById(codigo);
        return model.orElse(null);
    }

    @Override
    public void remover(int codigo) {
        try {
            getPorId(codigo);
            rDepartamento.deleteById(codigo);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public void remover(Departamento departamento) {
        try {
            rDepartamento.delete(departamento);
        } catch (DataIntegrityViolationException e) {
            System.out.println("NAO PODE REMOVER");
        }
    }

    @Override
    public List<Departamento> listarTudo() {
        return rDepartamento.findAll();
    }

    @Async
    @Override
    public CompletableFuture<List<Departamento>> listarTudoAsinc() {
        final List<Departamento> list = rDepartamento.findAll();
        return CompletableFuture.completedFuture(list);
    }
}
