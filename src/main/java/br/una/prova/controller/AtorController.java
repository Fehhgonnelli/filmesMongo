package br.una.prova.controller;

import br.una.prova.entity.Ator;
import br.una.prova.repository.AtorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

import javax.validation.Valid;

@Controller
@RequestMapping("/ator")
public class AtorController {

    private AtorRepository atorRepository;

    public AtorController(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("atores", atorRepository.findAll());
        return "ator/listar";
    }

    @GetMapping("/editar")
    public String edit(Model model, @RequestParam BigInteger id) {
        model.addAttribute("ator", atorRepository.findOne(id));
        return "ator/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("ator", new Ator());
        return "ator/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Ator ator, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ator/formulario";
        }
        atorRepository.save(ator);
        return "redirect:/ator";
    }

    @GetMapping("/excluir")
    public String excluir(Model model, @RequestParam BigInteger id) {
        atorRepository.delete(id);
        return "redirect:/ator";
    }
}