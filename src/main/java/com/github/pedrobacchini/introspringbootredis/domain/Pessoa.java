package com.github.pedrobacchini.introspringbootredis.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 4874568269279897845L;

    private int id;
    private String nome;
    private String sobrenome;
}
