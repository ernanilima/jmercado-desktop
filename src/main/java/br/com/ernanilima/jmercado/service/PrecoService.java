package br.com.ernanilima.jmercado.service;

import br.com.ernanilima.jmercado.model.Preco;

public interface PrecoService {
    Preco gravar(Preco preco);
    Preco getPorId(int codigo);
}
