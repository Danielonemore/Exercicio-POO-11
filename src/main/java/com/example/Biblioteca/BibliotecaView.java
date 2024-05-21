package com.example.Biblioteca;

import java.util.List;

public class BibliotecaView {
    public void mostrarLivros(List<Livro> livros) {
        for (Livro livro : livros) {
            System.out.println(livro.getTitulo() + " - " + livro.getAutor() + " - " + (livro.isEmprestado() ? "Emprestado" : "Disponível"));
        }
    }
}
