package trabalho4.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho4.demo.model.Categoria;

@RestController
@CrossOrigin(origins = "*")
public class CategoriaController {
//NO PHPMYADMIN TEM QUE COLOCAR A (A.I) MARCADO NO IDDDDD
    @PostMapping("/categoria")
    public Categoria cadastrar(Categoria categoria){
        categoria.insert();
        return categoria;
    }

    @GetMapping("/categoria/{id}")
    public Categoria getCategoria(@PathVariable int id) {
        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.load();
        return categoria;
    }

    @GetMapping("/categorias")
    public List<Categoria> getAllCategorias() {
        return Categoria.getAll();
    }

    @PutMapping("/categoria")
    public Categoria atualizar( Categoria categoria) {
        categoria.update();
        return categoria;
    }

    @DeleteMapping("/categoriadelete/{id}")
    public void deleteCategoria(@PathVariable int id) {
        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.delete();
    }
}



