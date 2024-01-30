package br.com.ada.tech.service;

import br.com.ada.tech.model.Contato;
import br.com.ada.tech.model.Telefone;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static br.com.ada.tech.service.MenuService.dados;
import static br.com.ada.tech.service.MenuService.entrada;

public class ContatoService {

    public static void adicionarContato() {

        Contato novoContato = new Contato();

        System.out.println("Digite o nome do contato:");
        String nome = entrada.nextLine();
        novoContato.setNome(nome);

        System.out.println("Digite o sobrenome do contato:");
        String sobreNome = entrada.nextLine();
        novoContato.setSobreNome(sobreNome);

        boolean salvar = true;
        do {
            System.out.println("Digite o DDD do Telefone");
            String ddd = MenuService.entrada.nextLine();

            if (ddd.length() == 2) {
                System.out.println("Digite o número de Telefone");
                Long numero = MenuService.entrada.nextLong();
                MenuService.entrada.nextLine();
                Telefone novoTelefone = new Telefone();
                if ((((Math.floor(Math.log10(numero + 1)))) == 9)) {
                    novoTelefone.setDdd(ddd);
                    novoTelefone.setNumero(numero);
                    novoContato.getTelefones().add(novoTelefone);
                    salvar = false;
                    break;
                } else {
                    System.out.println("Número Inválido. Insira novamente!");
                }
            } else {
                System.out.println("DDD Inválido. Insira novamente!");
            }
        } while (salvar);

        dados.salvar(novoContato);
        System.out.println("Contato Salvo!");
    }

    public static void editarContato() {

        long id;

        Contato contato;

        do {
            System.out.println("Digite o ID do contato para edição:");
            id = entrada.nextLong();
            entrada.nextLine();

            contato = dados.buscarPorId(id);

            if (contato == null) {
                System.out.println("Contato não encontrado. Informe um novo ID.");
            }
        } while (contato == null);

        System.out.println("Contato selecionado: " + contato.getNome());
        System.out.println("1 - Editar Nome");
        System.out.println("2 - Editar Sobrenome");
        System.out.println("3 - Editar Telefone");
        System.out.println("4 - Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = entrada.nextInt();
        entrada.nextLine(); //

        switch (opcao) {
            case 1:
                System.out.print("Informe o novo nome do contato: ");
                String novoNome = entrada.nextLine();
                contato.setNome(novoNome);
                dados.atualizar(contato);
                System.out.println("Contato atualizado com sucesso!");
                break;
            case 2:
                System.out.print("Informe o novo sobrenome do contato: ");
                String novosobreNome = entrada.nextLine();
                contato.setSobreNome(novosobreNome);
                dados.atualizar(contato);
                System.out.println("Contato atualizado com sucesso!");
                break;
            case 3:
                editarTelefone(contato);
                break;
            case 4:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }

    private static void editarTelefone(Contato contato) {
        List<Telefone> telefones = contato.getTelefones();
        System.out.println("Telefones do contato:");
        for (int i = 0; i < telefones.size(); i++) {
            Telefone telefone = telefones.get(i);
            System.out.println((i + 1) + " - " + telefone.getDdd() + "-" + telefone.getNumero());
        }
        System.out.println("Digite o indice do telefone que deseja alterar.");
        int indice = entrada.nextInt();
        entrada.nextLine();

        if (indice <= telefones.size()) {
            Telefone telefoneEscolhido = telefones.get(indice - 1);
            System.out.println("Para editar -> Digite 1");
            System.out.println("Para excluir -> Digite 2");
            int e = entrada.nextInt();
            entrada.nextLine();
            if (e == 1) {
                System.out.println("Informe o novo DDD:");
                String novoDDD = entrada.nextLine();
                if (novoDDD.length() == 2) {
                    System.out.println("Informe o novo número:");
                    long novoNumero = entrada.nextLong();
                    entrada.nextLine();
                    if ((((Math.floor(Math.log10(novoNumero + 1)))) == 9)) {
                        telefoneEscolhido.setDdd(novoDDD);
                        telefoneEscolhido.setNumero(novoNumero);
                        contato.getTelefones().set(indice - 1, telefoneEscolhido);
                        dados.atualizar(contato);
                        System.out.println("Telefone Editado!");
                    } else {
                        System.out.println("Número Inválido. tente novamente!");
                    }
                } else {
                    System.out.println("DDD Inválido, tente novamente!");
                }
            } else if (e == 2) {

                System.out.println("Telefone Excluido!");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    public static void removerContato() {
        System.out.print("Informe o ID do contato que deseja remover: ");
        Long id = entrada.nextLong();
        dados.remover(id);
        System.out.println("Contato removido com sucesso!");

    }

    public static void todosContatos() {
        dados.todos().forEach(contato -> {
            System.out.println(contato.getId() + " | " + contato.getNome() + " " + contato.getSobreNome());
        });
    }
}

