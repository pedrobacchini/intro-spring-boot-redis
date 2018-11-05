package com.github.pedrobacchini.introspringbootredis.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@RedisHash("Estudante")
public class Estudante implements Serializable {

    public enum Sexo {
        MASCULINO,
        FEMININO;
    }

    private String id;
    private String nome;
    private Sexo sexo;
    private int grau;
}
