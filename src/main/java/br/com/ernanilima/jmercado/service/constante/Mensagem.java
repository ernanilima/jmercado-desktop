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

    /** Mensagens para cadastro de usuario
     * {@link br.com.ernanilima.jmercado.controller.UsuarioController} */
    public static class Usuario {
        public static final String CODIGO = "CÓDIGO DO USUÁRIO";
        public static final String NOME_COMPLETO = "NOME COMPLETO DO USUÁRIO";
        public static final String NOME_SISTEMA = "NOME DO USUÁRIO QUE SERÁ EXIBIDO NO SISTEMA";
        public static final String STATUS = "STATUS DO CADASTRO DO USUÁRIO";
        public static final String SENHA = "SENHA DO USUÁRIO";
        public static final String SENHA_NOVA1 = "NOVA SENHA DO USUÁRIO";
        public static final String SENHA_NOVA2 = "REPETIR NOVA SENHA DO USUÁRIO";
    }
}
