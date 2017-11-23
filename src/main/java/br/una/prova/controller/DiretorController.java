package br.una.prova.controller;

import br.una.prova.entity.Diretor;
import br.una.prova.repository.DiretorRepository;
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
@RequestMapping("/diretor")
public class DiretorController {
    private DiretorRepository diretorRepository;

    public DiretorController(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("diretores", diretorRepository.findAll());
        return "diretor/listar";
    }

    @GetMapping("/editar")
    public String edit(Model model, @RequestParam BigInteger id) {
        model.addAttribute("diretor", diretorRepository.findOne(id));
        return "diretor/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("diretor", new Diretor());
        return "diretor/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Diretor diretor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "diretor/formulario";
        }
        diretorRepository.save(diretor);
        return "redirect:/diretor";
    }

    @GetMapping("/excluir")
    public String excluir(Model model, @RequestParam BigInteger id) {
        diretorRepository.delete(id);
        return "redirect:/diretor";
    }
}
