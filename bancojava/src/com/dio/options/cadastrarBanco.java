package com.dio.options;
import com.dio.operations.Bank;
import com.dio.uiScanner.userInput;
public class cadastrarBanco {
    public static void cadastrarBanco() {

        try {

            userInput ui = new userInput();
            Utils utils = new Utils();
            String id;
            String nome;
            String op;

            Bank banco = new Bank();
            if (!banco.countBank()) {

                do {
                    id = ui.getString("Informe o numero do Banco: ");
                    nome  = ui.getString("Informe o nome do Banco: ");
                    if (!utils.isNumeric(id)) {
                        System.out.println("Informe um numero valido para o Banco!");
                        id="";
                    }else{
                        if (id.equalsIgnoreCase("") || nome.equalsIgnoreCase("")){
                            System.out.println("Informe o Numero/Nome do Banco!");
                            System.out.println();
                            op = ui.getString("Deseja sair?  1 - Sim / 2 - Nao  ");
                            if (Integer.parseInt(op) == 1) return;
                        }
                    }

                }while (id.equalsIgnoreCase("") || nome.equalsIgnoreCase(""));

                banco.setIdBanco(id);
                banco.setNome(nome);

                if (banco.registerNewBank()) {
                    System.out.println("Cadastrado com sucesso!");
                } else {
                    System.out.println("Erro no cadastro!");
                }
            }else{
                System.out.println("Banco ja cadastrado!");
            }
        } catch (Exception e) {
            //return false;
        }
    }
}
