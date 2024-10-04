package trabalho4.demo.model;
//PARA CONECTAR O MYSQL VA N CTRL+SHIFT+P E VA EM ADD DEPENDECY EESCREVA MYSQL E ESCOLHA O CONECTOR DE JAVA

//pq o localhost:3306 nao pega?
//trabalho será (fazer só as tabelas VBERMELHAS) 
//
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private String banco = "loja_de_tatareco";
    private String usuario = "root";
    private String senha = "";

    public Connection getConexao(){
        String stringDeConexao = "jdbc:mysql://localhost:3306/" + banco;
        Connection conexao = null;
        try {
             conexao = DriverManager.getConnection(stringDeConexao, usuario, senha);
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco de dados");
            e.printStackTrace();
        }
        return conexao;
    }
}

