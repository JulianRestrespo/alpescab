package uniandes.edu.co.alpescab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.alpescab.modelo.Usuario;
import uniandes.edu.co.alpescab.repositorio.UsuarioRepository;

import java.util.Collection;
@RestController
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public Collection<Usuario> listar(){
        return usuarioRepository.todos();
    }

    @GetMapping("/usuarios/new")
    public Usuario form(){
        return new Usuario();
    }

    @PostMapping("/usuarios/new/save")
    public void guardar(@RequestBody Usuario u){
        usuarioRepository.insertar(u.getCedula(), u.getEmail(), u.getCelular(),
                                   u.getNombre(), u.getRol());
    }

    @GetMapping("/usuarios/{id}/edit")
    public ResponseEntity<Usuario> editarForm(@PathVariable("id") Long id){
        var u = usuarioRepository.porId(id);
        if(u != null){
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/usuarios/{id}/edit/save")
    public void editarGuardar(@PathVariable("id") Long id, @RequestBody Usuario u){
        usuarioRepository.actualizar(id, u.getCedula(), u.getEmail(), u.getCelular(),
                                     u.getNombre(), u.getRol());
    }

    @GetMapping("/usuarios/{id}/delete")
    public void eliminar(@PathVariable("id") Long id){
        usuarioRepository.eliminar(id);
    }
}
