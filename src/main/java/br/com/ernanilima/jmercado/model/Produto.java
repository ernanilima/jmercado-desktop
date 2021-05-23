package br.com.ernanilima.jmercado.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto implements Serializable, IModel {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 6, unique = true)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(length = 14, unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private long codigoBarras;

    @Column(length = 100, nullable = false)
    private String descricao;

    @Column(length = 100, nullable = false)
    private String descricaoCupom;

    @Column(length = 100)
    private String descricaoCliente;

    @Column(length = 100)
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "cod_depart")
    private Departamento mDepartamento;

    @ManyToOne
    @JoinColumn(name = "cod_grupo")
    private Grupo mGrupo;

    @ManyToOne
    @JoinColumn(name = "cod_sgrupo")
    private Subgrupo mSubgrupo;

    @OneToOne(mappedBy = "mProduto", cascade = CascadeType.ALL)
    private Preco mPreco;

    public Produto(int codigo, long codigoBarras, String descricao, String descricaoCupom, String descricaoCliente, String complemento, Departamento mDepartamento, Grupo mGrupo, Subgrupo mSubgrupo) {
        this.codigo = codigo;
        this.codigoBarras = codigoBarras;
        this.descricao = descricao;
        this.descricaoCupom = descricaoCupom;
        this.descricaoCliente = descricaoCliente;
        this.complemento = complemento;
        this.mDepartamento = mDepartamento;
        this.mGrupo = mGrupo;
        this.mSubgrupo = mSubgrupo;
    }
}
