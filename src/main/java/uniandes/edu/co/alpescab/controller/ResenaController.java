package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Resena;
import uniandes.edu.co.alpescab.repositorio.ResenaRepository;

@Controller
public class ResenaController {

    @Autowired
    private ResenaRepository resenaRepository;

    @GetMapping("/resenas")
    public String listar(Model model){
        model.addAttribute("resenas", resenaRepository.todas());
        return "resenas";
    }

    @GetMapping("/resenas/new")
    public String form(Model model){
        model.addAttribute("resena", new Resena());
        return "resenaNuevo";
    }

    @PostMapping("/resenas/new/save")
    public String guardar(@ModelAttribute Resena r){
        Long idViaje = r.getPk() != null && r.getPk().getViaje()!=null ? r.getPk().getViaje().getId() : null;
        Long idAutor = r.getPk() != null && r.getPk().getAutor()!=null ? r.getPk().getAutor().getId() : null;
        Long idObjetivo = r.getObjetivo()!=null ? r.getObjetivo().getId() : null;

        resenaRepository.insertar(idViaje, idAutor, idObjetivo, r.getCalificacion(), r.getComentario());
        return "redirect:/resenas";
    }

    @GetMapping("/resenas/{viaje}/{autor}/delete")
    public String eliminar(@PathVariable("viaje") Long idViaje, @PathVariable("autor") Long idAutor){
        resenaRepository.eliminar(idViaje, idAutor);
        return "redirect:/resenas";
    }
}
