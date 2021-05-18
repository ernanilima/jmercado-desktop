package br.com.ernanilima.jmercado.service.constante;

/** Mensagens do sistema */
public class Mensagem {

    public static final String PESQUISA = "INFORME SUA PESQUISA E PRESSIONE ENTER.";
    public static final String LIBERACAO_GRUPUSUA  = "LIBERAÇÕES DE GRUPO/USUÁRIO";

    /** Mensagens para cadastro de departamento
     * {@link br.com.ernanilima.jmercado.controller.DepartamentoController} */
    public static class ProdDepartamento {
        public static final String CODIGO = "CÓDIGO DO DEPARTAMENTO.";
        public static final String DESCRICAO = "DESCRIÇÃO DO DEPARTAMENTO.";
    }

    /** Mensagens para cadastro de grupo de usuario
     * {@link br.com.ernanilima.jmercado.controller.GrupoUsuarioController} */
    public static class GrupoUsuario {
        public static final String CODIGO = "CÓDIGO DO GRUPO DE USUÁRIO";
        public static final String DESCRICAO = "DESCRIÇÃO DO GRUPO DE USUÁRIO";
    }
}
