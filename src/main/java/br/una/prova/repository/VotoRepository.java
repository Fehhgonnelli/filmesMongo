package br.una.prova.repository;

import br.una.prova.entity.Voto;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotoRepository extends MongoRepository<Voto, Long> {
}
