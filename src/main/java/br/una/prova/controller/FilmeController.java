package br.una.prova.controller;

import br.una.prova.entity.Filme;
import br.una.prova.entity.Voto;
import br.una.prova.repository.AtorRepository;
import br.una.prova.repository.DiretorRepository;
import br.una.prova.repository.FilmeRepository;
import com.mongodb.DBObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

import javax.validation.Valid;


@Controller
@RequestMapping("/filme")
public class FilmeController {
    private FilmeRepository filmeRepository;
    private DiretorRepository diretorRepository;
    private AtorRepository atorRepository;
    private MongoTemplate mongoTemplate;


    public FilmeController(FilmeRepository filmeRepository, DiretorRepository diretorRepository, AtorRepository atorRepository,MongoTemplate mongoTemplate) {
        this.filmeRepository = filmeRepository;
        this.diretorRepository = diretorRepository;
        this.atorRepository = atorRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping
    public String list(Model model, @PageableDefault(size = 5)  Pageable pageable) {
        GroupOperation groupOperation = Aggregation.group("filme")
                .avg("avaliacao").as("mediaAvaliacoes");

        Aggregation aggregation = Aggregation.newAggregation( groupOperation);

        AggregationResults<DBObject> output = mongoTemplate.aggregate(aggregation, Voto.class, DBObject.class);

        model.addAttribute("filmes", output.getMappedResults());
        return "filme/listar";
    }

    @GetMapping("/editar")
    public String edit(Model model, @RequestParam BigInteger id) {
        model.addAttribute("filme", filmeRepository.findOne(id));
        model.addAttribute("diretores", diretorRepository.findAll());
        model.addAttribute("atores", atorRepository.findAll());
        return "filme/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("filme", new Filme());
        model.addAttribute("diretores", diretorRepository.findAll());
        model.addAttribute("atores", atorRepository.findAll());
        return "filme/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Filme filme, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "filme/formulario";
        }
        filmeRepository.save(filme);
        return "redirect:/filme";
    }

}
