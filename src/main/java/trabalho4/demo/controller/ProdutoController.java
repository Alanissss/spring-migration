package trabalho4.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho4.demo.model.Produto;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoController {
//NO PHPMYADMIN TEM QUE COLOCAR A (A.I) MARCADO NO IDDDDD
    @PostMapping("/produto")
    public Produto cadastrar(Produto produto){
        produto.insert();
        return produto;
    }

    @GetMapping("/produto/{id}")
    public Produto getProduto(@PathVariable int id) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.load();
        return produto;
    }

    @GetMapping("/produtos")
    public List<Produto> getAllProdutos() {
        return Produto.getAll();
    }

    @PutMapping("/produto")
    public Produto atualizar( Produto produto) {
        produto.update();
        return produto;
    }

    @DeleteMapping("/produtodelete/{id}")
    public void deleteProduto(@PathVariable int id) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.delete();
    }
}



