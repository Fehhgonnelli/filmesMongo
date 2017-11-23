package br.una.prova.repository;

import br.una.prova.entity.Filme;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilmeRepository extends MongoRepository<Filme,BigInteger> {
}
