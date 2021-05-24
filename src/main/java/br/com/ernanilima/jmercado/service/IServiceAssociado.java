package br.com.ernanilima.jmercado.service;

import br.com.ernanilima.jmercado.model.IModel;

public interface IServiceAssociado {
    IModel getAssociadoPorId(int codigoPrincipal, int codigoAssociado);
}
