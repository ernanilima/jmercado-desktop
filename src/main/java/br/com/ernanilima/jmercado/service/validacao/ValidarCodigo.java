package br.com.ernanilima.jmercado.service.validacao;

import br.com.ernanilima.jmercado.service.IService;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class ValidarCodigo {

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
            System.out.println("codigo exite");
            return true;

        } else if (!campoCodigo.getText().equals("") && Integer.parseInt(campoCodigo.getText()) <= 0) {
            // codigo nao informado ou igual a zero(0)
            System.out.println("codigo em branco ou negativo");
            return true;

        } else {
            // codigo informado no parametro
            // codigo novo nao existe no existe no banco de dados
            return false;
        }
    }
}
