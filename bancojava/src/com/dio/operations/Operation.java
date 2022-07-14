package com.dio.operations;
import com.dio.getPath.GetPath;
import java.io.*;
public class Operation {

    private String agency;
    private String account;

    private Double value;
    private String operacao;

    private String directory;

    public Operation(String agency, String account, Double value, String directory, String operacao) {
        this.agency = agency;
        this.account = account;
        this.value = value;
        this.operacao = operacao;
        this.directory = directory;
    }

    public Operation() {
        GetPath arquivo = new GetPath();
        arquivo.setTabela("operacao.txt");
        this.setDirectory(arquivo.tabelaExiste());
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public boolean realizarOperacao() {
        try {
            FileWriter fw = new FileWriter(getDirectory(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.getAgency() + "-" + this.getAccount() + "-" +
                    this.getValue() + "-" + this.getOperacao());
            bw.newLine();
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean verificarSaldo(String agencia, String conta, Double valor) {
        try {

            String[] saldo;
            double saldoTotal = 0;
            File file = new File(getDirectory());
            FileReader readerFile = new FileReader(file);
            BufferedReader item = new BufferedReader(readerFile);
            String text = item.readLine();
            while (text != null) {
                saldo = text.split("-");
                if (saldo[0].equalsIgnoreCase(agencia) && saldo[1].equalsIgnoreCase(conta)) {
                    if (saldo[3].equalsIgnoreCase("d")) {
                        saldoTotal += Double.parseDouble(saldo[2]);
                    } else if (saldo[3].equalsIgnoreCase("s") || saldo[3].equalsIgnoreCase("t")) {
                        saldoTotal -= Double.parseDouble(saldo[2]);
                    }
                }
                text = item.readLine();
            }
            item.close();
            if(saldoTotal >= valor) return true;
            return false;

        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
            return false;
        }
    }

    public void imprimirExtrato(String agencia, String conta) {

        try {

            String[] saldo;
            double saldoTotal = 0;
            File file = new File(getDirectory());
            FileReader readerFile = new FileReader(file);
            BufferedReader item = new BufferedReader(readerFile);
            String text = item.readLine();
            while (text != null) {
                saldo = text.split("-");
                if (saldo[0].equalsIgnoreCase(agencia) && saldo[1].equalsIgnoreCase(conta)) {
                    if (saldo[3].equalsIgnoreCase("d")) {
                        saldoTotal += Double.parseDouble(saldo[2]);
                        System.out.println("Deposito na conta no valor de " + String.format("%.2f", Double.parseDouble(saldo[2])));
                    } else if (saldo[3].equalsIgnoreCase("s")) {
                        saldoTotal -= Double.parseDouble(saldo[2]);
                        System.out.println("Saque na conta no valor de " + String.format("%.2f", Double.parseDouble(saldo[2])));
                    } else if (saldo[3].equalsIgnoreCase("t")) {
                        saldoTotal -= Double.parseDouble(saldo[2]);
                        System.out.println("Transferencia da conta no valor de " + String.format("%.2f", Double.parseDouble(saldo[2])));
                    }
                }
                text = item.readLine();
            }
            item.close();
            System.out.println();
            System.out.println("Saldo na conta de " + String.format("%.2f",saldoTotal));
            System.out.println();

        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }
    }


}
