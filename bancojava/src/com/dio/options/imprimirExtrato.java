package com.dio.options;
import com.dio.operations.Accounts;
import com.dio.operations.Operation;
import com.dio.uiScanner.userInput;

public class imprimirExtrato {
    public static void imprimirExtratoCliente() {

        userInput ui = new userInput();
        String numeroDaAgenciaOrigem;
        String numeroDaContaOrigem;

        Integer op = 0;
        Accounts conta = new Accounts();
        Utils utils = new Utils();
        Operation operacao = new Operation();

        //Verificar se tem agência Cadastrada
        if(utils.agenciaCadastrada()) return;
        //Verificar se tem cliente Cadastrado
        if(utils.clienteCadastrado()) return;

        // Pequisa se o cliente tem conta
        do {

            System.out.println("Informe o numero da Agencia:");
            numeroDaAgenciaOrigem = ui.getString("");

            System.out.println("Informe o numero da Conta:");
            numeroDaContaOrigem = ui.getString("");

            if (conta.accountExists(numeroDaAgenciaOrigem, numeroDaContaOrigem) == 0) {
                System.out.println("Agencia/Conta nao encontrado!");
                op = ui.getInt("Deseja tentar novamente?  1-Sim/2-Nao");
                if (op == 1){
                    numeroDaAgenciaOrigem = "";
                }else{
                    return;
                }
            }
        } while (numeroDaAgenciaOrigem.equalsIgnoreCase(""));

        operacao.imprimirExtrato(numeroDaAgenciaOrigem, numeroDaContaOrigem);
        op = ui.getInt("Deseja solicitar novo extrato? 1-Sim/2-Nao ");
        if (op == 1){
            imprimirExtratoCliente();
        }
    }
}
