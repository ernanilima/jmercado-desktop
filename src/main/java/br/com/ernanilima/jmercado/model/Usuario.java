package br.com.ernanilima.jmercado.model;

import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.TipoLiberacao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "usuario")
public class Usuario implements Serializable, IModel {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 3, unique = true)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name =  "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    private Integer codigo;

    @Column(length = 50, nullable = false)
    private String nomeCompleto;

    @Column(length = 50, nullable = false)
    private String nomeSistema;

    private String senha;
    private Boolean bloqueado;

    @ManyToOne
    @JoinColumn(name = "cod_grupousuario")
    private GrupoUsuario mGrupoUsuario;

    @JoinColumn(name = "cod_tipoliberacao")
    private Integer tipoLiberacao;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libusuario")
    private Set<Integer> liberacoes = new HashSet<>();

    public Usuario(Integer codigo, String nomeCompleto, String nomeSistema, Boolean bloqueado, GrupoUsuario mGrupoUsuario) {
        this.codigo = codigo;
        this.nomeCompleto = nomeCompleto;
        this.nomeSistema = nomeSistema;
        this.bloqueado = bloqueado;
        this.mGrupoUsuario = mGrupoUsuario;
    }

    public void setTipoLiberacao(TipoLiberacao tipoLiberacao) {
        this.tipoLiberacao = tipoLiberacao.getCodigo();
    }

    public void setLiberacoes(List<Liberacoes> liberacoes) {
        this.liberacoes.addAll(liberacoes.stream().map(Liberacoes::getCodigo).collect(Collectors.toSet()));
    }
}
