package br.com.ernanilima.jmercado.service.validacao;

import br.com.ernanilima.jmercado.service.IService;

import br.com.ernanilima.jmercado.service.componente.Legenda;
import br.com.ernanilima.jmercado.service.constante.MensagemAlerta;
import br.com.ernanilima.jmercado.utils.Filtro;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarCodigo {

    @Autowired private Legenda legenda;

    /** Validacao de codigo, verifica se ja existe ou codigo em branco
     * @param campoCodigo TextField - campo do codigo
     * @param service IService - servico de busca
     * @return boolean - false se codigo nao existir */
    public boolean novo(TextField campoCodigo, IService service) {
        if (!campoCodigo.isDisable() && // campo codigo esta habilitado, novo cadastro
                !campoCodigo.getText().equals("") && // existe codigo para gravar
                Integer.parseInt(campoCodigo.getText()) > 0 && // codigo maior que zero
                service.getPorId(Integer.parseInt(campoCodigo.getText())) != null // codigo ja cadastrado
        ) {
            // codigo existe no banco de dados
            legenda.exibirAlerta(MensagemAlerta.CODIGO_EXISTENTE, campoCodigo);
            return true;

        } else if (!campoCodigo.getText().equals("") && Integer.parseInt(campoCodigo.getText()) <= 0) {
            // codigo nao informado ou igual a zero(0)
            legenda.exibirAlerta(MensagemAlerta.CODIGOZERO, campoCodigo);
            return true;

        } else {
            // codigo informado no parametro
            // codigo novo nao existe no existe no banco de dados
            return false;
        }
    }

    /** Verifica se o cadastro existe com base no codigo passado no parametro
     * Usado em buscas onde eh digitado apenas o docigo do vinculado
     * Ex: vincular grupo de usuario no cadastro de usuario
     * @param campoCodigo TextField - campo do codigo
     * @param campoDescricao TextField - campo de descricao
     * @param service IService - servico de busca
     * @param nomeCampo Label - campo do campo
     * @return boolean - true se for localizado (tudo ok) */
    public boolean buscarExistente(TextField campoCodigo, TextField campoDescricao, IService service, Label nomeCampo) {
        if (!campoCodigo.getText().equals("") && // existe codigo para buscar
                Integer.parseInt(campoCodigo.getText()) > 0) { // codigo maior que zero

            if (service.getPorId(Filtro.pInt(campoCodigo.getText())) != null) {
                // cadastro localizado
                return true;

            } else {
                // cadastro nao localizado
                campoDescricao.setText("");
                legenda.exibirAlerta(MensagemAlerta.naoLocalizado(nomeCampo.getText()), campoCodigo);
                return false;
            }
        } else {
            // nada informado, eh validado em campo vazio
            campoDescricao.setText("");
            return false;
        }
    }
}
