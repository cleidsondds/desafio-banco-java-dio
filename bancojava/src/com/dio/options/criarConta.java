package com.dio.options;
import com.dio.operations.Accounts;
import com.dio.operations.Agency;
import com.dio.operations.Client;
import com.dio.uiScanner.userInput;

import java.util.List;
import java.util.Random;

public class criarConta {
    public static void criarConta() {

        userInput ui = new userInput();
        List<String> nomeAgencia;

        String numeroDoCPF;
        String numeroDaAgencia;
        String numeroDaConta="";
        String tipoConta;
        String op;
        Accounts create = new Accounts();

        Utils utils = new Utils();
        //Verificar se tem agencia Cadastrada
        if(utils.agenciaCadastrada()) return;
        //Verificar se tem cliente cadastrado
        if(utils.clienteCadastrado()) return;

        //Pesquisa se o Cliente est� cadastrado
        do {
            System.out.println("Informe o n�mero de CPF do Cliente:");
            numeroDoCPF = ui.getString("");
            if (numeroDoCPF.equalsIgnoreCase("")){
                System.out.println("Informe um numero de CPF valido!");
            }else {
                if (listarClienteBanco(numeroDoCPF) == 0) {
                    System.out.println("Cliente nao encontrado!");
                    numeroDoCPF = "";
                    op = ui.getString("Deseja sair?  1 - Sim / 2 - Nao  ");
                    if (Integer.parseInt(op) == 1) return;
                }

            }
       } while(numeroDoCPF.equalsIgnoreCase(""));

        do {

            nomeAgencia = listarAgencias();
            System.out.println("Informe uma Ag�ncia:");
            numeroDaAgencia = ui.getString("");

            if (numeroDaAgencia.equalsIgnoreCase("")){
                System.out.println("Informe uma agencia valida!");
                op = ui.getString("Deseja sair?  1 - Sim / 2 - Nao  ");
                if (Integer.parseInt(op) == 1) return;

            }else {
                if (escolherAgencia(nomeAgencia, numeroDaAgencia) == null) {
                    System.out.println("Ag�ncia n�o encontrada!");
                    numeroDaAgencia = "";
                    op = ui.getString("Deseja sair?  1 - Sim / 2 - Nao  ");
                    if (Integer.parseInt(op) == 1) return;
                }

            }

        } while(numeroDaAgencia.equalsIgnoreCase(""));
//
        //Escolher o tipo da Conta
        do {
            System.out.println("Escolha o tipo da Conta:");
            System.out.println("1 - Conta Corrente");
            System.out.println("2 - Conta Poupan�a");
            op = ui.getString("");

            if (Integer.parseInt(op) < 1 | Integer.parseInt(op) > 2){
                System.out.println("Informe um tipo de conta valido!");
                op = ui.getString("Deseja sair?  1 - Sim / 2 - Nao  ");
                if (Integer.parseInt(op) == 1) {
                    return;
                }else{
                    op = "0";
                }
            }

        } while (Integer.parseInt(op) < 1 | Integer.parseInt(op) > 2);

        if (Integer.parseInt(op) == 1){
            tipoConta= "Corrente";
        }else{
            tipoConta= "Poupan�a";
        }

        System.out.println("Conta Escolhida:  "  + tipoConta);
        //Gerar n�mero de conta aleat�rio de 5 digitos
        Random random = new Random();
        while (numeroDaConta.length() <= 5){
            numeroDaConta += random.nextInt(9);
        }

        System.out.println("Numero da Conta: " + numeroDaConta);
        create.setNameCustomer(numeroDoCPF);
        create.setNameAgency(numeroDaAgencia);
        create.setAccountType(tipoConta);
        create.setNumberAccount(numeroDaConta);

        if (create.registerNewAccount()) {
            System.out.println("Cadastrado com sucesso!");
            System.out.println("Anote o numero da sua agencia e conta!");
            op = ui.getString("Deseja cadastrar nova Conta? 1- Sim 2 - Nao");
            if (Integer.parseInt(op) == 1) criarConta();

        } else {
            System.out.println("Erro no cadastro!");
        }
    }
    private static int listarClienteBanco(String CPF) {

        String[] dadosCliente;
        String nomedoCliente = null;

        Client client = new Client();
        List<String> cliente = client.listNameCustomer();
        try {

            for (String customer : cliente) {
                dadosCliente = customer.split("-");
                if (nomedoCliente == null) {
                    if(dadosCliente[0].equalsIgnoreCase(CPF)){
                        nomedoCliente = dadosCliente[1];
                        System.out.println("Nome do Cliente:");
                        System.out.println("----------------------------------");
                        System.out.println(nomedoCliente);
                        return 1;
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    private static List<String> listarAgencias(){
        Agency agencia = new Agency();
        return agencia.listAgencies();
    }
    private static String escolherAgencia( List<String> agencia, String numeroAgencia){

        String[] dadosCliente;
        try {

            for (String customer : agencia) {
                dadosCliente = customer.split("-");
                if (dadosCliente[0].equalsIgnoreCase(numeroAgencia)){
                    System.out.println("Agencia Escolhida:");
                    System.out.println("----------------------------------");
                    System.out.println(customer);
                    return customer;
                }
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }
}