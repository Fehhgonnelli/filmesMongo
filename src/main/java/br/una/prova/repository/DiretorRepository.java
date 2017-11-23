package br.una.prova.repository;

import br.una.prova.entity.Diretor;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiretorRepository extends MongoRepository<Diretor,BigInteger>{
}
