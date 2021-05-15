package br.com.ernanilima.jmercado.service.constante;

public class MensagemErro {

    public static final String CODIGO_EXISTENTE = "CÓDIGO JÁ EXISTE, INFORME OUTRO!";
    public static final String CODIGOZERO = "NÃO É PERMITIDO CÓDIGO IGUAL A \"0\"";

    /** Mensagem personalizada
     * @param textoCampoObrigatorio String - descricao do campo obrigatorio
     * @return String - mensagem personalizada com o texto do parametro */
    public static String campoObrigatorio(String textoCampoObrigatorio) {
        return "É OBRIGATÓRIO INFORMAR O CAMPO: " + textoCampoObrigatorio.replace(":", "").toUpperCase();
    }
}
