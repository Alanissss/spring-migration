package trabalho4.demo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Produto {
    private int id;
    private double valor;
    private String nome;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void insert(){
        Conexao c = new Conexao();
        Connection dbCon = c.getConexao();

        String sql = "INSERT INTO produto (valor, nome) VALUES ( ?, ?)";

        try{
            PreparedStatement stmt = dbCon.prepareStatement(sql);
             stmt.setDouble(1, this.getValor());
             stmt.setString(2, this.getNome());
             stmt.execute();
          
        }catch(Exception e){
          
            e.printStackTrace();
        }

    }

    public void update(){  
        Conexao c = new Conexao();
        Connection dbConn = c.getConexao();

        String sql = "UPDATE produto SET valor = ?, nome = ? WHERE id = ?";

        try {
            PreparedStatement  ps = dbConn.prepareStatement(sql);
            ps.setDouble(1, this.valor);
            ps.setString(2, this.nome);
            ps.setInt(3, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){   
        Conexao c = new Conexao();
        Connection dbConn = c.getConexao();

        String sql = "DELETE FROM produto WHERE id = ?";
        try {
            PreparedStatement  ps = dbConn.prepareStatement(sql);
            ps.setInt(1, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void load(){
        Conexao c = new Conexao();
        Connection dbConn = c.getConexao();
        String sql = "SELECT * FROM produto WHERE id = ?";
        try {
            PreparedStatement  ps = dbConn.prepareStatement(sql);
            ps.setInt(1, this.id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                this.valor = rs.getDouble("valor");
                this.nome = rs.getString("nome");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Produto> getAll(){
        Conexao c = new Conexao();
        Connection dbConn = c.getConexao();
        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produto";
        try {
            Statement  st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setValor(rs.getDouble("valor"));
                produto.setNome(rs.getString("nome"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + "]";
    }


}

