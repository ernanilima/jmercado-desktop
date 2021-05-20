package br.com.ernanilima.jmercado.suporte;

import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.model.Usuario;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;

@Getter
@Component
public class UsuarioSuporte implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer codigo = 9999;
    private String nomeSistema = "SUPORTE";

    public Usuario getUsuarioSuporte() {
        Usuario mUsuario = new Usuario(getCodigo(), getNomeSistema(), getNomeSistema(), false, null);
        mUsuario.setSenha(this.getSenha());
        mUsuario.setLiberacoes(Arrays.asList(Liberacoes.toEnum(getCodigo())));
        return mUsuario;
    }

    public String getSenha() {
        // necessario sua chamada no get
        // dessa forma sempre sera chamado
        // mesmo que passe dias aberto
        return SenhaSuporte.getSenha();
    }
}
