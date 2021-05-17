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
}
