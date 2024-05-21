package com.example.Biblioteca;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
class BibliotecaModel {
    private List<Livro> livros;
    private Connection conn;

    public BibliotecaModel() {
        this.livros = new ArrayList<>();
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:5432/banco", "usuario", "senha");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS livros (titulo TEXT, autor TEXT, emprestado BOOLEAN)");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO livros (titulo, autor, emprestado) VALUES (?, ?, ?)");
            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setBoolean(3, livro.isEmprestado());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> getLivros() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM livros");
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEmprestado(rs.getBoolean("emprestado"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public void emprestarLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo)) {
                livro.setEmprestado(true);
                try {
                    PreparedStatement pstmt = conn.prepareStatement("UPDATE livros SET emprestado = ? WHERE titulo = ?");
                    pstmt.setBoolean(1, true);
                    pstmt.setString(2, titulo);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void devolverLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo)) {
                livro.setEmprestado(false);
                try {
                    PreparedStatement pstmt = conn.prepareStatement("UPDATE livros SET emprestado = ? WHERE titulo = ?");
                    pstmt.setBoolean(1, false);
                    pstmt.setString(2, titulo);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}