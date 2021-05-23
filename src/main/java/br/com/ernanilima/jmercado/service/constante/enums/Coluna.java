package br.com.ernanilima.jmercado.service.constante.enums;

import br.com.ernanilima.jmercado.service.constante.Mensagem;

import java.util.ArrayList;
import java.util.List;

public class Coluna {

    public static final String GERAL = "GERAL";

    /** DEPARTAMENTO */
    public enum ProdDepartamento {

        // nome da coluna e a mensagem da mesma
        CODIGO("CÓDIGO", Mensagem.ProdDepartamento.CODIGO),
        DESCRICAO("DESCRIÇÃO", Mensagem.ProdDepartamento.DESCRICAO);

        private final String coluna;
        private final String legenda;
        private static String[][] COLUNAS;

        static {
            // 2 linhas, 2 campos (descricao e mensagem)
            COLUNAS = new String[2][2];
            int i = 0;
            for (ProdDepartamento coluna : ProdDepartamento.values()) {
                // [numero da linha][numero do conteudo] = conteudo recebido
                COLUNAS[i][0] = coluna.getColuna();
                COLUNAS[i][1] = coluna.getLegenda();
                i++;
            }
        }

        ProdDepartamento (String coluna, String legenda) {
            this.coluna = coluna;
            this.legenda = legenda;
        }

        public String getColuna() {
            return coluna;
        }

        public String getLegenda() {
            return legenda;
        }

        public static String[][] getColunasLegendas() {
            return COLUNAS;
        }

        public static List<String> getColunas() {
            List<String> colunas = new ArrayList<>();
            for (String[] strings : getColunasLegendas())
                colunas.add(strings[0]);
            return colunas;
        }
    }

    /** GRUPO */
    public enum ProdGrupo {

        CODIGO("CÓDIGO", Mensagem.ProdGrupo.CODIGO),
        DESCRICAO("DESCRIÇÃO", Mensagem.ProdGrupo.DESCRICAO),
        CODIGO_DEPARTAMENTO("CÓD. DEPART.", Mensagem.ProdDepartamento.CODIGO),
        DESCRICAO_DEPARTAMENTO("DESC. DEPART.", Mensagem.ProdDepartamento.DESCRICAO);

        private final String coluna;
        private final String legenda;
        private static String[][] COLUNAS;

        static {
            // 4 linhas, 2 campos (descricao e mensagem)
            COLUNAS = new String[4][2];
            int i = 0;
            for (ProdGrupo coluna : ProdGrupo.values()) {
                // [numero da linha][numero do conteudo] = conteudo recebido
                COLUNAS[i][0] = coluna.getColuna();
                COLUNAS[i][1] = coluna.getLegenda();
                i++;
            }
        }

        ProdGrupo(String coluna, String legenda) {
            this.coluna = coluna;
            this.legenda = legenda;
        }

        public String getColuna() {
            return coluna;
        }

        public String getLegenda() {
            return legenda;
        }

        public static String[][] getColunasLegendas() {
            return COLUNAS;
        }

        public static List<String> getColunas() {
            List<String> colunas = new ArrayList<>();
            for (String[] strings : getColunasLegendas())
                colunas.add(strings[0]);
            return colunas;
        }
    }

    /** SUBGRUPO */
    public enum ProdSubgrupo {

        CODIGO("CÓDIGO", Mensagem.ProdSubgrupo.CODIGO),
        DESCRICAO("DESCRIÇÃO", Mensagem.ProdSubgrupo.DESCRICAO),
        CODIGO_GRUPO("CÓD. GRUPO", Mensagem.ProdGrupo.CODIGO),
        DESCRICAO_GRUPO("DESC. GRUPO", Mensagem.ProdGrupo.DESCRICAO),
        CODIGO_DEPARTAMENTO("CÓD. DEPART.", Mensagem.ProdDepartamento.CODIGO),
        DESCRICAO_DEPARTAMENTO("DESC. DEPART.", Mensagem.ProdDepartamento.DESCRICAO);

        private final String coluna;
        private final String legenda;
        private static String[][] COLUNAS;

        static {
            // 6 linhas, 2 campos (descricao e mensagem)
            COLUNAS = new String[6][2];
            int i = 0;
            for (ProdSubgrupo coluna : ProdSubgrupo.values()) {
                // [numero da linha][numero do conteudo] = conteudo recebido
                COLUNAS[i][0] = coluna.getColuna();
                COLUNAS[i][1] = coluna.getLegenda();
                i++;
            }
        }

        ProdSubgrupo(String coluna, String legenda) {
            this.coluna = coluna;
            this.legenda = legenda;
        }

        public String getColuna() {
            return coluna;
        }

        public String getLegenda() {
            return legenda;
        }

        public static String[][] getColunasLegendas() {
            return COLUNAS;
        }

        public static List<String> getColunas() {
            List<String> colunas = new ArrayList<>();
            for (String[] strings : getColunasLegendas())
                colunas.add(strings[0]);
            return colunas;
        }
    }

    /** PRODUTO */
    public enum Produto {

        CODIGO("CÓDIGO", Mensagem.Produto.CODIGO),
        DESCRICAO_PRODUTO("DESCRIÇÃO", Mensagem.Produto.DESCRICAO_PRODUTO),
        CODIGO_BARRAS("CÓD. BARRAS", Mensagem.Produto.CODIGO_BARRAS),
        CODIGO_SUBGRUPO("CÓD. SUBGRUPO", Mensagem.ProdSubgrupo.CODIGO),
        DESCRICAO_SUBGRUPO("DESC. SUBGRUPO", Mensagem.ProdSubgrupo.DESCRICAO),
        PRECO_DE_VENDA("PREÇO DE VENDA", Mensagem.Produto.PRECO_VENDA);

        private final String coluna;
        private final String legenda;
        private static String[][] COLUNAS;

        static {
            // 6 linhas, 2 campos (descricao e mensagem)
            COLUNAS = new String[6][2];
            int i = 0;
            for (Produto coluna : Produto.values()) {
                // [numero da linha][numero do conteudo] = conteudo recebido
                COLUNAS[i][0] = coluna.getColuna();
                COLUNAS[i][1] = coluna.getLegenda();
                i++;
            }
        }

        Produto(String coluna, String legenda) {
            this.coluna = coluna;
            this.legenda = legenda;
        }

        public String getColuna() {
            return coluna;
        }

        public String getLegenda() {
            return legenda;
        }

        public static String[][] getColunasLegendas() {
            return COLUNAS;
        }

        public static List<String> getColunas() {
            List<String> colunas = new ArrayList<>();
            for (String[] strings : getColunasLegendas())
                colunas.add(strings[0]);
            return colunas;
        }
    }

    /** GRUPOS USUARIOS */
    public enum GrupoUsuario {

        CODIGO("CÓDIGO", Mensagem.GrupoUsuario.CODIGO),
        DESCRICAO("DESCRICAO", Mensagem.GrupoUsuario.DESCRICAO);

        private final String coluna;
        private final String legenda;
        private static String[][] COLUNAS;

        static {
            // 2 linhas, 2 campos (descricao e mensagem)
            COLUNAS = new String[2][2];
            int i = 0;
            for (GrupoUsuario coluna : GrupoUsuario.values()) {
                // [numero da linha][numero do conteudo] = conteudo recebido
                COLUNAS[i][0] = coluna.getColuna();
                COLUNAS[i][1] = coluna.getLegenda();
                i++;
            }
        }

        GrupoUsuario (String coluna, String legenda) {
            this.coluna = coluna;
            this.legenda = legenda;
        }

        public String getColuna() {
            return coluna;
        }

        public String getLegenda() {
            return legenda;
        }

        public static String[][] getColunasLegendas() {
            return COLUNAS;
        }

        public static List<String> getColunas() {
            List<String> colunas = new ArrayList<>();
            for (String[] strings : getColunasLegendas())
                colunas.add(strings[0]);
            return colunas;
        }
    }

    /** USUARIOS */
    public enum Usuario {

        CODIGO("CÓDIGO", Mensagem.Usuario.CODIGO),
        NOME_COMPLETO("NOME COMPLETO", Mensagem.Usuario.NOME_COMPLETO),
        NOME_SISTEMA("NOME SISTEMA", Mensagem.Usuario.NOME_SISTEMA),
        CODIGO_GRUPOUSUARIO("CÓD GRU", Mensagem.GrupoUsuario.CODIGO),
        DESCRICAO_GRUPOUSUARIO("DESCRIÇÃO GRUPO", Mensagem.GrupoUsuario.DESCRICAO),
        BLOQUEADO("BLOQ", Mensagem.Usuario.STATUS);

        private final String coluna;
        private final String legenda;
        private static String[][] COLUNAS;

        static {
            // 6 linhas, 2 campos (descricao e mensagem)
            COLUNAS = new String[6][2];
            int i = 0;
            for (Usuario coluna : Usuario.values()) {
                // [numero da linha][numero do conteudo] = conteudo recebido
                COLUNAS[i][0] = coluna.getColuna();
                COLUNAS[i][1] = coluna.getLegenda();
                i++;
            }
        }

        Usuario(String coluna, String legenda) {
            this.coluna = coluna;
            this.legenda = legenda;
        }

        public String getColuna() {
            return coluna;
        }

        public String getLegenda() {
            return legenda;
        }

        public static String[][] getColunasLegendas() {
            return COLUNAS;
        }

        public static List<String> getColunas() {
            List<String> colunas = new ArrayList<>();
            for (String[] strings : getColunasLegendas())
                colunas.add(strings[0]);
            return colunas;
        }
    }
}
