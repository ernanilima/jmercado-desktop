package br.com.ernanilima.jmercado.liberacao;

public enum TipoLiberacao {
    USUARIO(1),
    GRUPO(2);

    private int codigo;

    TipoLiberacao(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoLiberacao toEnum(Integer codigo) {
        if (codigo == null) { return null; }
        for (TipoLiberacao x : TipoLiberacao.values()) {
            if (codigo.equals(x.codigo))
                return x;
        } throw new IllegalArgumentException("Invalido");
    }
}
