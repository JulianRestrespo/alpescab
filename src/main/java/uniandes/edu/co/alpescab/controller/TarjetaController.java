package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Tarjeta;
import uniandes.edu.co.alpescab.repositorio.TarjetaRepository;

import java.sql.Date;
import java.time.LocalDate;

@Controller
public class TarjetaController {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @GetMapping("/tarjetas")
    public String listar(Model model){
        model.addAttribute("tarjetas", tarjetaRepository.todas());
        return "tarjetas";
    }

    @GetMapping("/tarjetas/new")
    public String form(Model model){
        model.addAttribute("tarjeta", new Tarjeta());
        return "tarjetaNuevo";
    }

    @PostMapping("/tarjetas/new/save")
    public String guardar(@ModelAttribute Tarjeta t){
        Long idUsuario = t.getUsuario()!=null ? t.getUsuario().getId() : null;
        LocalDate v = t.getVencimiento();
        tarjetaRepository.insertar(
            t.getNumero(),
            t.getNombreTarjeta(),
            (v!=null ? Date.valueOf(v) : null),
            t.getCvv(),
            idUsuario
        );
        return "redirect:/tarjetas";
    }

    @GetMapping("/tarjetas/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var t = tarjetaRepository.porId(id);
        if(t != null){
            model.addAttribute("tarjeta", t);
            return "tarjetaEditar";
        } else return "redirect:/tarjetas";
    }

    @PostMapping("/tarjetas/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute Tarjeta t){
        LocalDate v = t.getVencimiento();
        Long idUsuario = t.getUsuario()!=null ? t.getUsuario().getId() : null;
        tarjetaRepository.actualizar(
            id,
            t.getNumero(),
            t.getNombreTarjeta(),
            (v!=null ? Date.valueOf(v) : null),
            t.getCvv(),
            idUsuario
        );
        return "redirect:/tarjetas";
    }

    @GetMapping("/tarjetas/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        tarjetaRepository.eliminar(id);
        return "redirect:/tarjetas";
    }
}
