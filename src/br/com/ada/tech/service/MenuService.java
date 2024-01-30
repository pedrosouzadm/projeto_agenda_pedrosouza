package br.com.ada.tech.service;

import br.com.ada.tech.helper.ContatoHelper;
import br.com.ada.tech.model.Contato;
import br.com.ada.tech.repository.ContatoRepository;

import java.util.Scanner;

public class MenuService {

    public static Scanner entrada;
    public static ContatoRepository dados = new ContatoRepository();

    public MenuService() {
        dados.todos();
        MenuService.entrada = new Scanner(System.in);
    }

    public void iniciarPrograma() {
        escolherAcao();
        entrada.close();
    }

    private void escolherAcao() {
        int opcao;

        do {
            menuInicial();
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {
                case 1:
                    ContatoService.adicionarContato();
                    break;
                case 2:
                    ContatoService.editarContato();
                    break;
                case 3:
                    ContatoService.removerContato();
                    break;
                case 4:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente Novamente!");
            }
        } while (opcao != 4);
    }

    private void menuInicial() {
        System.out.println(""" 
                ##################
                ##### AGENDA #####
                ##################
                """);
        System.out.println(">>>> Contatos <<<<");
        System.out.println("ID  |  Nome \n");


        ContatoService.todosContatos();


        System.out.println("""
                >>>> Menu <<<<
                1 - Adicionar Contato
                2 - Editar Contato
                3 - Remover Contato
                4 - Sair
                Escolha uma opção:""");
    }
}
