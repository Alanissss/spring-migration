package trabalho4.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho4.demo.model.Usuario;


@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {
//NO PHPMYADMIN TEM QUE COLOCAR A (A.I) MARCADO NO IDDDDD
    @PostMapping("/usuario")
    public Usuario cadastrar(Usuario usuario){
        usuario.insert();
        return usuario;
    }

    @GetMapping("/usuario/{id}")
    public Usuario getUsuario(@PathVariable int id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.load();
        return usuario;
    }

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return Usuario.getAll();
    }

    @PutMapping("/usuario")
    public Usuario atualizar( Usuario usuario) {
        usuario.update();
        return usuario;
    }

    @DeleteMapping("/usuariodelete/{id}")
    public void deleteUsuario(@PathVariable int id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.delete();
    }
}


