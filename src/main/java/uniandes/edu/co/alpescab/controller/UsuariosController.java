package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Usuario;
import uniandes.edu.co.alpescab.repositorio.UsuarioRepository;

@Controller
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public String listar(Model model){
        model.addAttribute("usuarios", usuarioRepository.todos());
        return "usuarios";
    }

    @GetMapping("/usuarios/new")
    public String form(Model model){
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String guardar(@ModelAttribute Usuario u){
        usuarioRepository.insertar(u.getCedula(), u.getEmail(), u.getCelular(),
                                   u.getNombre(), u.getRol());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/edit")
    public String editarForm(@PathVariable("id") Long id, Model model){
        var u = usuarioRepository.porId(id);
        if(u != null){
            model.addAttribute("usuario", u);
            return "usuarioEditar";
        } else return "redirect:/usuarios";
    }

    @PostMapping("/usuarios/{id}/edit/save")
    public String editarGuardar(@PathVariable("id") Long id, @ModelAttribute Usuario u){
        usuarioRepository.actualizar(id, u.getCedula(), u.getEmail(), u.getCelular(),
                                     u.getNombre(), u.getRol());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/delete")
    public String eliminar(@PathVariable("id") Long id){
        usuarioRepository.eliminar(id);
        return "redirect:/usuarios";
    }
}
