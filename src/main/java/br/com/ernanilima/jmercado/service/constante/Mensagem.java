package br.com.ernanilima.jmercado.service.constante;

/** Mensagens do sistema */
public class Mensagem {

    public static final String PESQUISA = "INFORME SUA PESQUISA E PRESSIONE ENTER.";
    public static final String LIBERACAO_GRUPUSUA  = "LIBERAÇÕES DE GRUPO/USUÁRIO";
    public static final String PRECO_VENDA  = "PREÇO DE VENDA";

    /** Mensagens para cadastro de departamento
     * {@link br.com.ernanilima.jmercado.controller.DepartamentoController} */
    public static class ProdDepartamento {
        public static final String CODIGO = "CÓDIGO DO DEPARTAMENTO.";
        public static final String DESCRICAO = "DESCRIÇÃO DO DEPARTAMENTO.";
    }

    /** Mensagem para cadastro de grupo
     * {@link br.com.ernanilima.jmercado.controller.GrupoController} */
    public static class ProdGrupo {
        public static final String CODIGO = "CÓDIGO DO GRUPO.";
        public static final String DESCRICAO = "DESCRIÇÃO DO GRUPO.";
    }

    /** Mensagem para cadastro de subgrupo
     * {@link br.com.ernanilima.jmercado.controller.SubgrupoController} */
    public static class ProdSubgrupo {
        public static final String CODIGO = "CÓDIGO DO SUBGRUPO.";
        public static final String DESCRICAO = "DESCRIÇÃO DO SUBGRUPO.";
    }

    /** Mensagem para cadastro de produto
     * {@link br.com.ernanilima.jmercado.controller.ProdutoController} */
    public static class Produto {
        public static final String CODIGO = "CÓDIGO DO PRODUTO.";
        public static final String CODIGO_BARRAS = "CÓDIGO DE BARRAS DO PRODUTO.";
        public static final String DESCRICAO_PRODUTO = "DESCRIÇÃO DO PRODUTO.";
        public static final String DESCRICAO_CUPOM = "DESCRIÇÃO DO PRODUTO PARA O CUPOM.";
        public static final String DESCRICAO_CLIENTE = "DESCRIÇÃO DO PRODUTO PARA O CLIENTE.";
        public static final String COMPLEMENTO = "COMPLEMENTO DO PRODUTO.";
        public static final String PRECO_VENDA = "PREÇO DE VENDA.";
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
