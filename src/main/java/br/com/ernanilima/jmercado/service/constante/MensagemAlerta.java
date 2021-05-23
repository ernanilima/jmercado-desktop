package br.com.ernanilima.jmercado.service.constante;

public class MensagemAlerta {

    public static final String CODIGO_EXISTENTE = "CÓDIGO JÁ EXISTE, INFORME OUTRO!";
    public static final String CODIGO_BARRAS_EXISTENTE = "CÓDIGO DE BARRAS JÁ EXISTE, INFORME OUTRO!";
    public static final String CODIGOZERO = "NÃO É PERMITIDO CÓDIGO IGUAL A \"0\"";
    public static final String LOGIN_INVALIDO = "LOGIN INVÁLIDO";
    public static final String USUARIO_BLOQUEADO = "USUÁRIO BLOQUEADO";
    public static final String SENHA_NAO_MODIFICADA = "DADOS INVÁLIDOS, SENHA NÃO MODIFICADA";
    public static final String SENHA_NAO_COMBINAM = "SENHAS NOVAS NÃO COMBINAM";

    public static String excluir(String mensagem) {
        return "EXCLUIR " + mensagem + "?";
    }

    /** Mensagem personalizada
     * @param textoCampoObrigatorio String - descricao do campo obrigatorio
     * @return String - mensagem personalizada com o texto do parametro */
    public static String campoObrigatorio(String textoCampoObrigatorio) {
        return "É OBRIGATÓRIO INFORMAR O CAMPO: " + textoCampoObrigatorio.replace(":", "").toUpperCase();
    }

    /** Mensagem personalizada
     * @param naoLocalizado String - tipo nao localizado
     * @return String - XXX NÃO LOCALIZADO! */
    public static String naoLocalizado(String naoLocalizado) {
        return naoLocalizado.replace(":", "").toUpperCase() + " NÃO LOCALIZADO";
    }

    /** Mensagem personalizada
     * @param numCaracteres int - numero de caractere(s)
     * @param textoCampo String - descricao do campo
     * @return String - mensagem personalizada com informacoes do parametro */
    public static String minimoCaracteres(int numCaracteres, String textoCampo) {
        return "INFORME NO MÍNIMO " + numCaracteres + " CARACTERE(S) NO CAMPO " + textoCampo.replace(":", "").toUpperCase();
    }
}
