package br.una.prova.entity;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Document
public class Filme {
    @Id
    @GeneratedValue
    private BigInteger id;

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    private Integer ano;

    @Formula("(select AVG(v.avaliacao + 0.0) from voto v where v.filme_id = id)")
    private Double mediaAvaliacoes;
    
    @ManyToOne
    @JoinColumn(name = "diretor_id", referencedColumnName = "id")
    private Diretor diretor;

    private List<Ator> atores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setMediaAvaliacoes(Double mediaAvaliacoes) {
		this.mediaAvaliacoes = mediaAvaliacoes;
	}

	public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public Double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return Objects.equals(id, filme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}

	public List<Ator> getAtores() {
		return atores;
	}
}
