package br.com.ernanilima.jmercado.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prodgrupo")
public class Grupo implements Serializable, IModel {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 3, unique = true)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(length = 50, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cod_depart")
    private Departamento mDepartamento;
}
