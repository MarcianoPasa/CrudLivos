package dao;

import banco.BancoDeDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Livro;

public class LivroDao {
    private Connection connection;

    public LivroDao() {
        connection = BancoDeDados.getConnection();
    }
    
    public void adicionarLivro(Livro livro) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into livros(nome,autor,datalancamento) values (?, ?, ?)");
            
            preparedStatement.setString(1, livro.getNome());
            preparedStatement.setString(2, livro.getAutor());
            
            if (livro.getDatalancamento() == null) {            
                preparedStatement.setDate(3, null);
            } else {
                preparedStatement.setDate(3, new java.sql.Date(livro.getDatalancamento().getTime()));                
            }
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void apagarLivro(int livroId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from livros where id=?");
            
            preparedStatement.setInt(1, livroId);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizarLivros(Livro livro) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "update livros set nome=?, autor=?, datalancamento=?" +
                            "where id=?"
                    );
            
            preparedStatement.setString(1, livro.getNome());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setDate(3, new java.sql.Date(livro.getDatalancamento().getTime()));
            
            preparedStatement.setInt(4, livro.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Livro> buscarTotosOsLivros() {
        List<Livro> listaDeLivros = new ArrayList<Livro>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from livros order by nome");
            
            while (rs.next()) {
                Livro livro = new Livro();
                
                livro.setId(rs.getInt("id"));
                livro.setNome(rs.getString("nome"));
                livro.setAutor(rs.getString("autor"));
                livro.setDatalancamento(rs.getDate("dataLancamento"));
                
                listaDeLivros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDeLivros;
    }
    
    public Livro buscarLivroPorId(int livroId) {
        Livro livro = new Livro();
        
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from livros where id=?");
            
            preparedStatement.setInt(1, livroId);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                livro.setId(rs.getInt("id"));
                livro.setNome(rs.getString("nome"));
                livro.setAutor(rs.getString("autor"));
                livro.setDatalancamento(rs.getDate("dataLancamento"));                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livro;
    }
}
