package br.com.ernanilima.jmercado.utils;

import br.com.ernanilima.jmercado.model.Departamento;
import br.com.ernanilima.jmercado.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Temporario {
    @Autowired DepartamentoRepository rDepartamento;

    public void criarBancoDeDados() {
        // cria departamentos
        Departamento departamento1 = new Departamento(1, "MERCEARIA SECA");
        Departamento departamento2 = new Departamento(2, "BEBIDA");

        // salva departamentos
        rDepartamento.saveAll(Arrays.asList(departamento1, departamento2));
    }
}
