package com.example.Biblioteca;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        BibliotecaView view = new BibliotecaView();
        BibliotecaModel model = new BibliotecaModel();
        BibliotecaController controller = new BibliotecaController(view, model);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Emprestar Livro");
            System.out.println("4. Devolver Livro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o nome do autor do livro: ");
                    String autor = scanner.nextLine();
                    controller.cadastrarLivro(titulo, autor);
                    break;
                case 2:
                    controller.listarLivros();
                    break;
                case 3:
                    System.out.print("Digite o título do livro que deseja emprestar: ");
                    titulo = scanner.nextLine();
                    controller.emprestarLivro(titulo);
                    break;
                case 4:
                    System.out.print("Digite o título do livro que deseja devolver: ");
                    titulo = scanner.nextLine();
                    controller.devolverLivro(titulo);
                    break;
                case 5:
                    System.exit(0);
                    scanner.close();
            }
        }
    }
}