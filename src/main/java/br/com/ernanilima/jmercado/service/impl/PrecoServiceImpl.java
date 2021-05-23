package br.com.ernanilima.jmercado.service.impl;

import br.com.ernanilima.jmercado.model.Preco;
import br.com.ernanilima.jmercado.repository.PrecoRepository;
import br.com.ernanilima.jmercado.service.PrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrecoServiceImpl implements PrecoService {

    @Autowired private PrecoRepository rPreco;

    @Override
    public Preco gravar(Preco preco) {
        return rPreco.save(preco);
    }

    @Override
    public Preco getPorId(int codigo) {
        Optional<Preco> model = rPreco.findById(codigo);
        return model.orElse(null);
    }
}
