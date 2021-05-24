package br.com.ernanilima.jmercado.utils;

import br.com.ernanilima.jmercado.model.*;
import br.com.ernanilima.jmercado.repository.DepartamentoRepository;
import br.com.ernanilima.jmercado.repository.GrupoRepository;
import br.com.ernanilima.jmercado.repository.ProdutoRepository;
import br.com.ernanilima.jmercado.repository.SubgrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;

@Component
public class Temporario {

    @Autowired private DepartamentoRepository rDepartamento;
    @Autowired private GrupoRepository rGrupo;
    @Autowired private SubgrupoRepository rSubgrupo;
    @Autowired private ProdutoRepository rProduto;

    public void criarBancoDeDados() {
        System.out.println("CRIA DEPARTAMENTOS");
        Departamento departamento1 = new Departamento(1, "MERCEARIA SECA");
        Departamento departamento2 = new Departamento(2, "BEBIDA");

        System.out.println("CRIA GRUPOS");
        Grupo grupo1 = new Grupo(1, "ARROZ", departamento1);
        Grupo grupo2 = new Grupo(2, "FEIJAO", departamento1);
        Grupo grupo3 = new Grupo(3, "COM ALCOOL", departamento2);
        Grupo grupo4 = new Grupo(4, "SEM ALCOOL", departamento2);

        System.out.println("CRIA SUBGRUPOS");
        Subgrupo subGrupo1 = new Subgrupo(1, "ARROZ PARBOILIZADO", grupo1, departamento1);
        Subgrupo subGrupo2 = new Subgrupo(2, "ARROZ INTEGRAL", grupo1, departamento1);
        Subgrupo subGrupo3 = new Subgrupo(3, "FEIJAO CARIOCA", grupo2, departamento1);
        Subgrupo subGrupo4 = new Subgrupo(4, "FEIJAO PRETO", grupo2, departamento1);
        Subgrupo subGrupo5 = new Subgrupo(5, "CERVEJA", grupo3, departamento2);
        Subgrupo subGrupo6 = new Subgrupo(6, "WHISKY", grupo3, departamento2);
        Subgrupo subGrupo7 = new Subgrupo(7, "COCA-COLA", grupo4, departamento2);
        Subgrupo subGrupo8 = new Subgrupo(8, "AGUA", grupo4, departamento2);

        System.out.println("CRIA PRODUTOS");
        Produto produto1 = new Produto(1, 7891, "ARROZ PARB TIO JOAO 1KG", "ARROZ PARB TIO JOAO 1KG", "ARROZ PARB TIO JOAO 1KG", "", departamento1, grupo1, subGrupo1);
        produto1.setMPreco(new Preco(produto1, 6.99, Calendar.getInstance()));
        Produto produto2 = new Produto(2, 7892, "ARROZ PARB CAMIL 1KG", "ARROZ PARB CAMIL 1KG", "ARROZ PARB CAMIL 1KG", "", departamento1, grupo1, subGrupo1);
        produto2.setMPreco(new Preco(produto2, 6.80, Calendar.getInstance()));
        Produto produto3 = new Produto(3, 7893, "ARROZ INTEGRAL KIARROZ 1KG", "ARROZ INTEGRAL KIARROZ 1KG", "ARROZ INTEGRAL KIARROZ 1KG", "", departamento1, grupo1, subGrupo2);
        produto3.setMPreco(new Preco(produto3, 6.49, Calendar.getInstance()));
        Produto produto4 = new Produto(4, 7894, "ARROZ INTEGRAL TIO JOAO 1KG", "ARROZ INTEGRAL TIO JOAO 1KG", "ARROZ INTEGRAL TIO JOAO 1KG", "", departamento1, grupo1, subGrupo2);
        produto4.setMPreco(new Preco(produto4, 7.29, Calendar.getInstance()));
        Produto produto5 = new Produto(5, 7895, "FEIJAO CARIOCA CALDO BOM 1KG", "FEIJAO CARIOCA CALDO BOM 1KG", "FEIJAO CARIOCA CALDO BOM 1KG", "", departamento1, grupo2, subGrupo3);
        produto5.setMPreco(new Preco(produto5, 9.19, Calendar.getInstance()));
        Produto produto6 = new Produto(6, 7896, "FEIJAO CARIOCA TIPO 1 KICALDO 1KG", "FEIJAO CARIOCA TIPO 1 KICALDO 1KG", "FEIJAO CARIOCA TIPO 1 KICALDO 1KG", "", departamento1, grupo2, subGrupo3);
        produto6.setMPreco(new Preco(produto6, 7.19, Calendar.getInstance()));
        Produto produto7 = new Produto(7, 7897, "FEIJAO PRETO SABOROSO 1KG", "FEIJAO PRETO SABOROSO 1KG", "FEIJAO PRETO SABOROSO 1KG", "", departamento1, grupo2, subGrupo4);
        produto7.setMPreco(new Preco(produto7, 7.09, Calendar.getInstance()));
        Produto produto8 = new Produto(8, 7898, "FEIJAO PRETO BROTO LEGAL 1KG", "FEIJAO PRETO BROTO LEGAL 1KG", "FEIJAO PRETO BROTO LEGAL 1KG", "", departamento1, grupo2, subGrupo4);
        produto8.setMPreco(new Preco(produto8, 7.49, Calendar.getInstance()));
        Produto produto9 = new Produto(9, 7899, "CERVEJA BRAHMA CHOPP 350ML", "CERVEJA BRAHMA CHOPP 350ML", "CERVEJA BRAHMA CHOPP 350ML", "", departamento2, grupo3, subGrupo5);
        produto9.setMPreco(new Preco(produto9, 2.59, Calendar.getInstance()));
        Produto produto10 = new Produto(10, 78910, "CERVEJA SKOL PILSEN 350ML", "CERVEJA SKOL PILSEN 350ML", "CERVEJA SKOL PILSEN 350ML", "", departamento2, grupo3, subGrupo5);
        produto10.setMPreco(new Preco(produto10, 2.19, Calendar.getInstance()));
        Produto produto11 = new Produto(11, 78911, "WHISKY JACK DANIELS HONEY 1L", "WHISKY JACK DANIELS HONEY 1L", "WHISKY JACK DANIELS HONEY 1L", "", departamento2, grupo3, subGrupo6);
        produto11.setMPreco(new Preco(produto11, 131.29, Calendar.getInstance()));
        Produto produto12 = new Produto(12, 78912, "WHISKY JOHNNIE WALKER RED LABEL 1L", "WHISKY JOHNNIE WALKER RED LABEL 1L", "WHISKY JOHNNIE WALKER RED LABEL 1L", "", departamento2, grupo3, subGrupo6);
        produto12.setMPreco(new Preco(produto12, 90.49, Calendar.getInstance()));
        Produto produto13 = new Produto(13, 78913, "COCA-COLA SEM ACUCAR LATA 350ML", "COCA-COLA SEM ACUCAR LATA 350ML", "COCA-COLA SEM ACUCAR LATA 350ML", "", departamento2, grupo4, subGrupo7);
        produto13.setMPreco(new Preco(produto13, 2.85, Calendar.getInstance()));
        Produto produto14 = new Produto(14, 78914, "COCA-COLA ORIGINAL PET 2L", "COCA-COLA ORIGINAL PET 2L", "COCA-COLA ORIGINAL PET 2L", "", departamento2, grupo4, subGrupo7);
        produto14.setMPreco(new Preco(produto14, 7.39, Calendar.getInstance()));
        Produto produto15 = new Produto(15, 78915, "AGUA MINERAL SEM GAS 500ML", "AGUA MINERAL SEM GAS 500ML", "AGUA MINERAL SEM GAS 500ML", "", departamento2, grupo4, subGrupo8);
        produto15.setMPreco(new Preco(produto15, 1.95, Calendar.getInstance()));
        Produto produto16 = new Produto(16, 78916, "AGUA MINERAL RETORNAVEL 20L", "AGUA MINERAL RETORNAVEL 20L", "AGUA MINERAL RETORNAVEL 20L", "", departamento2, grupo4, subGrupo8);
        produto16.setMPreco(new Preco(produto16, 14.99, Calendar.getInstance()));

        System.out.println("GRAVA DEPARTAMENTOS");
        rDepartamento.saveAll(Arrays.asList(departamento1, departamento2));

        System.out.println("GRAVA GRUPOS");
        rGrupo.saveAll(Arrays.asList(grupo1, grupo2, grupo3, grupo4));

        System.out.println("GRAVA SUBGRUPOS");
        rSubgrupo.saveAll(Arrays.asList(subGrupo1, subGrupo2, subGrupo3, subGrupo4, subGrupo5, subGrupo6, subGrupo7, subGrupo8));

        System.out.println("GRAVA PRODUTOS");
        rProduto.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7, produto8,
                produto9, produto10, produto11, produto12, produto13, produto14, produto15, produto16));
    }
}
