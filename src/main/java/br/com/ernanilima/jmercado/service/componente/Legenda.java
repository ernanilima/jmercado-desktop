package br.com.ernanilima.jmercado.service.componente;

import br.com.ernanilima.jmercado.controller.InicioController;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Legenda {

    @Autowired private InicioController cInicio;

    private Thread threadCampoLegenda;

    /** Exibe legenda no fxml inicial
     * @param legenda String - legenda que deve ser exibida */
    public void exibirLegenda(String legenda) {
        cInicio.setLegenda(legenda);
    }

    /** Define mensagem de erro, usado principalmente em popup
     * @param legendaErro String - mensagem de erro */
    public void exibirAlerta(String legendaErro) {
        this.exibirAlerta(legendaErro, new TextField());
    }

    /** Define mensagem de erro, exibe mensagem na tela {@link InicioController}
     * @param legendaErro String - mensagem de erro
     * @param campoRetornoErro Node - campo do erro */
    public void exibirAlerta(String legendaErro, Node campoRetornoErro) {
        this.exibirAlerta(legendaErro, campoRetornoErro, cInicio.getCampoAlerta());
    }

    /** Define mensagem de erro, usado principalmente em login
     * @param legendaErro String - mensagem de erro
     * @param campoRetornoErro Node - campo do erro
     * @param camposMensagemErro Label... - campo(s) para exibir a mensagem de erro */
    public void exibirAlerta(String legendaErro, Node campoRetornoErro, Label... camposMensagemErro) {
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
                            for (Label campoMensagemErro : camposMensagemErro) {
                                campoMensagemErro.setText(legendaErro);
                                campoMensagemErro.setVisible(true);
                            }
                        });

                        // PAUSA A THREAD
                        Thread.sleep(TEMPO_EXECUCAO);

                        Platform.runLater(() -> {
                            // ADICIONA O BACKGROUND ORIGINAL NO CAMPO OBRIGATORIO
                            for (Label campoMensagemErro : camposMensagemErro) {
                                campoMensagemErro.setVisible(false);
                                campoMensagemErro.setText("");
                            }
                        });
                    } catch (InterruptedException e) {e.printStackTrace();}
                    loop++;
                }
            });
            threadCampoLegenda.start();
        }
    }
}
