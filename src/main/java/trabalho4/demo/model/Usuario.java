package trabalho4.demo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nome;

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

        String sql = "INSERT INTO usuario (nome) VALUES (?)";

        try{
            PreparedStatement stmt = dbCon.prepareStatement(sql);
            
             stmt.setString(1, this.getNome());
             stmt.execute();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void update(){  
        Conexao c = new Conexao();
        Connection dbConn = c.getConexao();

        String sql = "UPDATE usuario SET nome = ? WHERE id = ?";

        try {
            PreparedStatement  ps = dbConn.prepareStatement(sql);
            ps.setString(1, this.nome);
            ps.setInt(2, this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(){   
        Conexao c = new Conexao();
        Connection dbConn = c.getConexao();

        String sql = "DELETE FROM usuario WHERE id = ?";
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
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            PreparedStatement  ps = dbConn.prepareStatement(sql);
            ps.setInt(1, this.id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                this.nome = rs.getString("nome");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Usuario> getAll(){
        Conexao c = new Conexao();
        Connection dbConn = c.getConexao();
        ArrayList<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuario";
        try {
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + "]";
    }


}
