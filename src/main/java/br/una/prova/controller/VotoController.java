package br.una.prova.controller;

import br.una.prova.entity.Voto;
import br.una.prova.repository.FilmeRepository;
import br.una.prova.repository.VotoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/voto")
public class VotoController {

    private FilmeRepository filmeRepository;
    private VotoRepository votoRepository;

    public VotoController(FilmeRepository filmeRepository, VotoRepository votoRepository) {
        this.filmeRepository = filmeRepository;
        this.votoRepository = votoRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("voto", new Voto());
        model.addAttribute("filmes", filmeRepository.findAll());
        return "voto/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Voto voto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("filmes", filmeRepository.findAll());
            return "voto/formulario";
        }
        votoRepository.save(voto);
        return "redirect:/filme";
    }

}
