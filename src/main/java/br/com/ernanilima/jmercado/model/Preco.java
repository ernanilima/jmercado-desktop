package br.com.ernanilima.jmercado.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prodpreco")
public class Preco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cod_produt")
    private int id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "cod_produt", nullable = false)
    private Produto mProduto;

    private double precoVenda;
    private Calendar dataUltAltPrecoVenda;
}
