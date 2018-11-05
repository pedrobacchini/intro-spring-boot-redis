package com.github.pedrobacchini.introspringbootredis.repository;

import com.github.pedrobacchini.introspringbootredis.domain.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class EmpregadosDAO {

    private static final String CHAVE_EMPREGADO = "empregados";

    private RedisTemplate<String, Pessoa> redisTemplate;

    private HashOperations<String, Integer, Pessoa> hashOperations;

    @Autowired
    public EmpregadosDAO(RedisTemplate<String, Pessoa> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    public void adicionarEmpregado(Pessoa pessoa) { hashOperations.putIfAbsent(CHAVE_EMPREGADO, pessoa.getId(), pessoa); }

    public boolean existeEmoregados() { return redisTemplate.hasKey(CHAVE_EMPREGADO); }

    public void atualizarEmpregado(Pessoa pessoa) { hashOperations.put(CHAVE_EMPREGADO, pessoa.getId(), pessoa); }

    public Pessoa obterEmpregado(Integer id) { return hashOperations.get(CHAVE_EMPREGADO, id); }

    public long obterNumeroEmpregados() { return hashOperations.size(CHAVE_EMPREGADO); }

    public Map<Integer, Pessoa> obterTodosEmpregados() { return hashOperations.entries(CHAVE_EMPREGADO); }

    public long deletarEmpregados(Integer... ids) { return hashOperations.delete(CHAVE_EMPREGADO, ids); }

    public boolean deletarTodosEmpregados() { return redisTemplate.delete(CHAVE_EMPREGADO); }
}
