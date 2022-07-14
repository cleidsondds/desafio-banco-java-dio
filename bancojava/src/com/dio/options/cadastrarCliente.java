package com.dio.options;
import com.dio.operations.Client;
import com.dio.uiScanner.userInput;
import java.util.List;
public class cadastrarCliente {
    public static void cadastrarCliente() {

        try {

            userInput ui = new userInput();
            String numeroCPF;
            String nomeCliente;
            String[] CPF;

            int op;
            Client cliente = new Client();
            Utils utils = new Utils();
            List<String> clientes = cliente.listNameCustomer();

            if(utils.agenciaCadastrada()) return;

            do {
                op = 1;
                System.out.println();
                numeroCPF = ui.getString("Informe o numero do CPF: ");

                if (numeroCPF.equalsIgnoreCase("") || (!utils.isNumeric(numeroCPF))) {
                    op = 0;
                    System.out.println("Informe um numero de CPF valido!");
                }else{
                    if (clientes.size() > 0 ){
                        for (String c : clientes)
                        {
                            CPF = c.split("-");
                            if (CPF[0].equalsIgnoreCase(numeroCPF)) {
                                System.out.println("Cliente ja Cadastrado!");
                                op = ui.getInt("Deseja sair?  1 - Sim / 2 - Nao  " );
                                if (op == 1){
                                    return;
                                }else{
                                    op=0;
                                }
                            }else{
                                op=1;
                            }
                        }
                    }
                }

            }while (op==0);

            System.out.println("");
            do {
                System.out.println("Informe o nome do Cliente: ");
                nomeCliente =ui.getString("");
                if (nomeCliente.equalsIgnoreCase("")){
                    System.out.println("Informe um nome de Cliente valido!");
                }
                System.out.println();
            }while (nomeCliente.equalsIgnoreCase(""));

            cliente.setIdentity(numeroCPF);
            cliente.setNameCustomer(nomeCliente);

            if (cliente.registerNewCustomer()) {
                System.out.println("Cadastrado com sucesso!");
                op = ui.getInt("Deseja cadastrar novo cliente? 1- Sim 2 - Nao");
                if (op == 1) {
                    cadastrarCliente();
                }
            } else {
                System.out.println("Erro no cadastro!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}