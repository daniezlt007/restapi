package com.projeto.restapi.model;

import javax.persistence.*;

@Entity
@Table(name = "conta")
public class Conta {

    @Id
    private Long numero;

    private Long agencia;

    private String banco;

    @OneToOne
    @JoinColumn(name = "usuarioid", referencedColumnName = "id")
    private Usuario usuario;

}
