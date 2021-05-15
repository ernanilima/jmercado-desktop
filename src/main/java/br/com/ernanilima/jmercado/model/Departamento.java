package br.com.ernanilima.jmercado.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proddepart")
public class Departamento implements Serializable, IModel {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 3, unique = true)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(length = 50, nullable = false)
    private String descricao;

}
