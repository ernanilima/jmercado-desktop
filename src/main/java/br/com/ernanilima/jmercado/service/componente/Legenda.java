package br.com.ernanilima.jmercado.service.componente;

import br.com.ernanilima.jmercado.controller.InicioController;
import javafx.application.Platform;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Legenda {

    @Autowired private InicioController cInicio;

    private Thread threadCampoLegenda;

    /** Define mensagem de erro
     * @param legendaErro String - mensagem de erro
     * @param campoRetornoErro Node - campo do erro */
    public void exibeAlerta(String legendaErro, Node campoRetornoErro) {
        final int TEMPO_EXECUCAO = 3000;
        campoRetornoErro.requestFocus();

        // thread null apenas na primeira execucao
        // verificado se thread esta ativa
        if (threadCampoLegenda == null || !threadCampoLegenda.isAlive()) {
            threadCampoLegenda = new Thread(() -> {
                int loop = 0;

                while (loop < 1) {
                    try {
                        Platform.runLater(() -> {
                            // ADICIONA A LEGENDA DE ERRO
                            cInicio.getCampoAlerta().setText(legendaErro);
                            cInicio.getCampoAlerta().setVisible(true);
                        });

                        // PAUSA A THREAD
                        Thread.sleep(TEMPO_EXECUCAO);

                        Platform.runLater(() -> {
                            // ADICIONA O BACKGROUND ORIGINAL NO CAMPO OBRIGATORIO
                            cInicio.getCampoAlerta().setVisible(false);
                            cInicio.getCampoAlerta().setText("");
                        });
                    } catch (InterruptedException e) {e.printStackTrace();}
                    loop++;
                }
            });
            threadCampoLegenda.start();
        }
    }
}
