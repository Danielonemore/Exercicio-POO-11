package com.example.Biblioteca;

import java.util.List;

public class BibliotecaController {
    private BibliotecaView view;
    private BibliotecaModel model;

    public BibliotecaController(BibliotecaView view, BibliotecaModel model) {
        this.view = view;
        this.model = model;
    }

    public void cadastrarLivro(String titulo, String autor) {
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        model.adicionarLivro(livro);
    }

    public void listarLivros() {
        List<Livro> livros = model.getLivros();
        view.mostrarLivros(livros);
    }

    public void emprestarLivro(String titulo) {
        model.emprestarLivro(titulo);
    }

    public void devolverLivro(String titulo) {
        model.devolverLivro(titulo);
    }
}
