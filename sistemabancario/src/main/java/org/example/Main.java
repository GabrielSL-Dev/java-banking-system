package org.example;

import org.example.model.ContaBancaria;
import org.example.service.Conta;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Conta> contas = new ArrayList<>();

        while (true) {

            System.out.println("\n===================================");
            System.out.println("SISTEMA BANCÁRIO");
            System.out.println("===================================");

            System.out.println("1 - Criar conta");
            System.out.println("2 - Acessar conta");
            System.out.println("3 - Listar conta");
            System.out.println("4 - Sair");

            System.out.print("\nEscolha a opção: ");
            int opcao = lerInteiro(sc);

            if (opcao == 4) {
                System.out.println("Obrigado por utilizar o sistema!");
                System.out.println("Saindo do sistema...");
                break;
            }


            switch (opcao) {
                case 1:
                    System.out.println("\n---------CADASTRO----------");

                    String nome;

                   while(true){
                       System.out.print("\nDigite nome completo: ");
                       nome = sc.nextLine();

                       if (nomeValido(nome)){
                           break;
                       }

                       System.out.println("Nome inálido!");
                   }

                   String usuario;
                   while(true) {

                       System.out.print("Digite seu Usuário: ");
                       usuario = sc.nextLine();

                       boolean usuarioExistente = false;
                       for (Conta c : contas) {
                           if (c.getUsuario().equalsIgnoreCase(usuario)) {
                               usuarioExistente = true;
                               break;
                           }

                       }
                       if (usuarioExistente){
                           System.out.println("Usuário já existente! Digite outro usário.\n");
                           continue;
                       }
                       if(usuario.contains(" ")){
                           System.out.println("Usuario não pode conter espaços!\n");
                           continue;
                       }
                       if (!usuario.matches(".*[a-zA-Z].*")){
                           System.out.println("Usuário precisa ter pelo menos uma letra!\n");
                           continue;
                       }


                       break;
                   }

                    System.out.print("Digite seu senha: ");
                    String senha = sc.nextLine();

                    Conta contaNova = new Conta(nome, usuario, senha);
                    contas.add(contaNova);

                    System.out.println("\nConta criada com sucesso! \n");
                    break;

                case 2:

                    if (contas.isEmpty()){
                        System.out.println("Nenhuma conta cadastrada!");
                        break;
                    }

                    System.out.println("\n--------LOGIN--------");


                    System.out.print("Usuário: ");
                    String usuarioLogin = sc.nextLine();

                    System.out.print("Senha: ");
                    String senhaLogin = sc.nextLine();

                    Conta contaLogada = null;

                    for(Conta c : contas){
                        if (c.getUsuario().equals(usuarioLogin) && c.getSenha().equals(senhaLogin)){
                            contaLogada = c;
                            break;
                        }
                    }

                    if(contaLogada == null){
                        System.out.println("Usuário ou senha inválida!");
                        break;
                    }


                    System.out.println("\nLogin realizado!");


                    System.out.println("\n======");
                    System.out.println("CONTA");
                    System.out.println("======");

                    System.out.print("Titular: " + contaLogada.getNome());
                    ContaBancaria contaBancaria = contaLogada.getContaBancaria();

                    while (true) {
                        System.out.println("\n1 - Depositar");
                        System.out.println("2 - Sacar");
                        System.out.println("3 - Ver saldo");
                        System.out.println("4 - Ver histórico");
                        System.out.println("5 - Sair");

                        System.out.print("\nEscolha a opção: ");
                        opcao = lerInteiro(sc);


                        if (opcao == 5) {
                            System.out.println("Saindo da conta...");
                            break;
                        }

                        switch (opcao) {
                            case 1:
                                System.out.print("\nDigite o valor para depósito: ");
                                double valor = lerDouble(sc);

                                if (valor <= 0) {
                                    System.out.println("Valor inválido!");
                                } else {
                                    contaBancaria.depositar(valor);
                                    System.out.println("Depósito realizado com sucesso!");
                                }
                                break;

                            case 2:
                                System.out.print("\nDigite o valor para sacar: ");
                                double saque = lerDouble(sc);

                                if (contaBancaria.sacar(saque)) {
                                    System.out.println("Saque realizado com sucesso!");
                                } else {
                                    System.out.println("Saldo insuficiente! ");
                                }
                                break;

                            case 3:
                                System.out.println("\nTitular: " + contaBancaria.getTitular());
                                System.out.println("Saldo Atual: R$" + contaBancaria.getSaldo());
                                break;

                            case 4:
                                System.out.println("\n==============================");
                                System.out.println("HISTÓRICO DE TRANSAÇÕES");
                                System.out.println("==============================\n");

                                contaBancaria.mostrarHistorico();
                                break;

                            default:
                                System.out.println("Número inválido!");
                        }

                    }
                    break;

                case 3:

                    if (contas.isEmpty()){
                        System.out.println("Nenhuma conta cadastrada!");
                    }
                    System.out.println("\n---CONTAS CADASTRADAS---");

                    for (Conta c : contas) {
                        System.out.printf("Titular: %s | Usuário: %s%n", c.getNome(), c.getUsuario());
                    }
                    break;

                default:
                    System.out.println("Opção inválida! ");

            }
        }
    }

    public static boolean nomeValido(String nome){
        return nome != null && nome.trim().matches("[a-zA-Z]+( [a-zA-Z]+)+");
    }

    public static int lerInteiro(Scanner sc){
        while (true){
            try{
                return Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.print("Digite apenas números: ");
            }
        }
    }

    public static double lerDouble(Scanner sc){
        while (true){
            try{
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Digite um valor númerico válido: ");
            }
        }
    }

}

