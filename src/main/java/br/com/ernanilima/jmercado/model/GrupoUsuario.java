package br.com.ernanilima.jmercado.model;

import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gruusuario")
public class GrupoUsuario implements Serializable, IModel {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 3, unique = true)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gruusuario")
    @SequenceGenerator(name =  "seq_gruusuario", sequenceName = "seq_gruusuario", allocationSize = 1)
    private int codigo;

    @Column(length = 50, nullable = false)
    private String descricao;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libgruusua")
    private Set<Integer> liberacoes = new HashSet<>();

    public GrupoUsuario(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Set<Liberacoes> getLiberacoes() {
        return liberacoes.stream().map(Liberacoes::toEnum).collect(Collectors.toSet());
    }

    public void setLiberacoes(List<Liberacoes> liberacoes) {
        this.liberacoes.addAll(liberacoes.stream().map(Liberacoes::getCodigo).collect(Collectors.toSet()));
    }
}
