package br.com.ernanilima.jmercado.suporte.validacao;

import br.com.ernanilima.jmercado.model.Usuario;
import br.com.ernanilima.jmercado.suporte.UsuarioSuporte;

public class ValidarUsuarioSuporte {

    /** Verifica se o usuario atualmente logado tem o mesmo codigo do usuario de suporte
     * Tambem eh possivel realizar outras validacoes para ter certeza de que eh um usuario de suporte,
     * visto que, a validacao eh apenas pelo codigo e seria facil alterar o codigo de um usuario
     * qualquer, para que o mesmo possa ter as liberacoes de suporte
     * @param usuarioLogado Usuario - usuario logado atualmente
     * @return boolean - true se for um usuario de suporte */
    public static boolean conectado(Usuario usuarioLogado) {
        UsuarioSuporte mUsuarioSuporte = new UsuarioSuporte();
        return usuarioLogado.getCodigo().equals(mUsuarioSuporte.getCodigo());
    }
}
