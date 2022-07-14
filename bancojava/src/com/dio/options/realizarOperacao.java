package com.dio.options;
import com.dio.operations.Accounts;
import com.dio.operations.Operation;
import com.dio.uiScanner.userInput;

public class realizarOperacao {
    public static void realizarOperacao(String operacao) {

        userInput ui = new userInput();
        String numeroDaAgenciaOrigem;
        String numeroDaContaOrigem;
        Double valorOrigem;
        String op;

        String numeroDaAgenciaDestino = null;
        String numeroDaContaDestino = null;
        Utils utils = new Utils();

        Accounts conta = new Accounts();

        //Validações de agencias e contas
        if ((operacao.equalsIgnoreCase("d")) || (operacao.equalsIgnoreCase("s"))) {
            //Verificar se tem agência Cadastrada
            if (utils.agenciaCadastrada()) return;
            if (utils.contasCadastradas()) return;
        }else if (operacao.equalsIgnoreCase("t")){
            if (utils.contasCadastradas("t")) return;
        }
        //Validação do cliente
        //Verificar se tem cliente Cadastrado
        if(utils.clienteCadastrado()) return;

        // Pequisa se a agencia conta existe
        do {

            System.out.println("Informe o numero da Agencia:");
            numeroDaAgenciaOrigem = ui.getString("");

            System.out.println("Informe o numero da Conta:");
            numeroDaContaOrigem = ui.getString("");

            if (numeroDaAgenciaOrigem.equalsIgnoreCase("") | numeroDaContaOrigem.equalsIgnoreCase("")){
                System.out.println("Informe numero de agencia ou conta valido");
                op = ui.getString("Deseja sair?  1 - Sim / 2 - Nao  ");
                if (Integer.parseInt(op) == 1) return;
            }else {
                if (conta.accountExists(numeroDaAgenciaOrigem, numeroDaContaOrigem) == 0) {
                    System.out.println("Agencia/Conta nao encontrado!");
                    numeroDaAgenciaOrigem = "";
                    op = ui.getString("Deseja sair?  1 - Sim / 2 - Nao  ");
                    if (Integer.parseInt(op) == 1) return;
                }
            }

        } while (numeroDaAgenciaOrigem.equalsIgnoreCase(""));

        if (operacao.equalsIgnoreCase("t")){
            do {

                System.out.println("Informe o numero da Agencia Destino:");
                numeroDaAgenciaDestino = ui.getString("");

                System.out.println("Informe o numero da Conta Destino:");
                numeroDaContaDestino = ui.getString("");

                if (numeroDaAgenciaDestino.equalsIgnoreCase("") | numeroDaContaDestino.equalsIgnoreCase("")){
                    System.out.println("Informe numero de agencia ou conta valido");
                    op = ui.getString("Deseja sair?  1 - Sim / 2 - Nao  ");
                    if (Integer.parseInt(op) == 1) return;
                }else {
                    if (conta.accountExists(numeroDaAgenciaDestino, numeroDaContaDestino) == 0) {
                        System.out.println("Agencia/Conta de Destino nao encontrado!");
                        numeroDaAgenciaDestino = "";
                        op = ui.getString("Deseja sair?  1 - Sim / 2 - Nao  ");
                        if (Integer.parseInt(op) == 1) return;
                    }
                }

            } while (numeroDaAgenciaDestino.equalsIgnoreCase(""));
        }

        do {
            valorOrigem = ui.getDouble("Informe o valor da operacao:");
            if (valorOrigem == 0d) {
                System.out.println("Informe valor valido!");
                op = ui.getString("Deseja sair?  1 - Sim / 2 - Nao  ");
                if (Integer.parseInt(op) == 1) return;
            }
        }while (valorOrigem==0d);

        if (operacao.equalsIgnoreCase("d")) {
            realizarDeposito(numeroDaAgenciaOrigem, numeroDaContaOrigem, valorOrigem);
        } else if (operacao.equalsIgnoreCase("s")) {
            realizarSaque(numeroDaAgenciaOrigem, numeroDaContaOrigem, valorOrigem);
        } else if (operacao.equalsIgnoreCase("t")) {
            realizarTransferencia(numeroDaAgenciaOrigem, numeroDaContaOrigem, numeroDaAgenciaDestino, numeroDaContaDestino, valorOrigem);
        }
    }
    private static void realizarDeposito(String agencia, String conta, Double valor) {

        int op;
        userInput ui = new userInput();
        Operation deposito = new Operation();
        deposito.setAgency(agencia);
        deposito.setAccount(conta);
        deposito.setValue(valor);
        deposito.setOperacao("d");

        if (deposito.realizarOperacao()) {
            System.out.println("Cadastrado com sucesso!");
            op = ui.getInt("Deseja realizar novo deposito? 1- Sim 2 - Nao");
            if (op == 1) realizarOperacao("d");
        } else {
            System.out.println("Erro no cadastro!");
        }
    }
    private static void realizarSaque(String agencia, String conta, Double valor) {

        int op;
        userInput ui = new userInput();
        Operation saque = new Operation();

        //Verificar se tem Saldo Suficiente
        if (saque.verificarSaldo(agencia, conta, valor)) {
            saque.setAgency(agencia);
            saque.setAccount(conta);
            saque.setValue(valor);
            saque.setOperacao("s");
            if (saque.realizarOperacao()) {
                System.out.println("Cadastrado com sucesso!");
                op = ui.getInt("Deseja realizar novo saque? 1- Sim 2 - Nao");
                if (op == 1) realizarOperacao("s");
            }
        }else{
            System.out.println("Saldo Insuficiente!");
            op = ui.getInt("Deseja realizar outra operacao de saque? 1- Sim 2 - Nao");
            if (op == 1) realizarOperacao("s");
        }
    }
    private static void realizarTransferencia(String agenciaOrigem, String contaOrigem,
                                              String agenciaDestino, String contaDestino,  Double valor) {
        int op;
        userInput ui = new userInput();

        Operation transferencia = new Operation();

        if (contaOrigem.equalsIgnoreCase(contaDestino)) {
            System.out.println("As contas são iguais!");
            System.out.println("Informe uma conta diferente!");
            realizarOperacao("t");
        }

        if (transferencia.verificarSaldo(agenciaOrigem, contaOrigem, valor)) {

            //saque
            transferencia.setAgency(agenciaOrigem);
            transferencia.setAccount(contaOrigem);
            transferencia.setValue(valor);
            transferencia.setOperacao("t");

            //Operação de saque
            transferencia.realizarOperacao();

            //Operação de depósito
            transferencia.setAgency(agenciaDestino);
            transferencia.setAccount(contaDestino);
            transferencia.setValue(valor);
            transferencia.setOperacao("d");
    
            if (transferencia.realizarOperacao()) {
                System.out.println("Operacao realizada com sucesso!");
                op = ui.getInt("Deseja realizar nova transferencia? 1- Sim 2 - Nao");
                if (op == 1) realizarOperacao("t");
            }
        }else{
            System.out.println("Saldo Insuficiente!");
            op = ui.getInt("Deseja realizar outra operacao de transferencia? 1- Sim 2 - Nao");
            if (op == 1) realizarOperacao("t");
        }
    }
}