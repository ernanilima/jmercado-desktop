package br.com.ernanilima.jmercado.suporte;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Calendar;

/** Classe que gera a senha do suporte */
public class SenhaSuporte {

    protected static String getSenha() {
        Calendar hoje = Calendar.getInstance();
        int ano = hoje.get(Calendar.YEAR);
        int mes = hoje.get(Calendar.MONTH) + 1; // mes comeca com zero
        int dia = hoje.get(Calendar.DAY_OF_MONTH);

        String calculo = String.valueOf(ano * (mes * dia));
        System.out.println("SENHA DO SUPORTE EH " + calculo);

        return new BCryptPasswordEncoder().encode(calculo);
    }
}
