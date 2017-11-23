package br.una.prova.repository;

import br.una.prova.entity.Ator;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AtorRepository extends MongoRepository<Ator,BigInteger>{
}
