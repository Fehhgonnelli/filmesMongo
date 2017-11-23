package br.una.prova.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.math.BigInteger;
import java.util.Objects;

@Document
public class Diretor {
    @Id
    @GeneratedValue
    private BigInteger id;
    @NotBlank(message = "Campo obrigatório")
    private String nome;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diretor diretor = (Diretor) o;
        return Objects.equals(id, diretor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
