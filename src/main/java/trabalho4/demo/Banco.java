package trabalho4.demo;

import java.sql.Connection;

import trabalho4.demo.model.Categoria;
import trabalho4.demo.model.Conexao;
import trabalho4.demo.model.Produto;
import trabalho4.demo.model.Usuario;

public class Banco {
    public static void main(String[] args) throws Exception {
        Conexao c = new Conexao();
        Connection con = c.getConexao();

        /*Categoria categoria = new Categoria();
        categoria.setId(103); 
        categoria.setDescricao("Bom");
        categoria.salvar();*/
    }
}
