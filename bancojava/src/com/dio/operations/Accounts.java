package com.dio.operations;

import com.dio.getPath.GetPath;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Accounts {
    private String nameCustomer;
    private String nameAgency;
    private String numberAccount;
    private String accountType;
    private String directory;


    public Accounts(String nameCustomer, String nameAgency, String numberAccount, String accountType, String directory) {
        this.nameCustomer = nameCustomer;
        this.nameAgency = nameAgency;
        this.numberAccount = numberAccount;
        this.accountType = accountType;
        this.directory = directory;
    }

    public Accounts(){
        GetPath arquivo = new GetPath();
        arquivo.setTabela("contas.txt");
        this.setDirectory(arquivo.tabelaExiste());
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getNameAgency() {
        return nameAgency;
    }

    public void setNameAgency(String nameAgency) {
        this.nameAgency = nameAgency;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public boolean registerNewAccount() {

        try {
            // Fluxo de saida de um arquivo
            FileWriter fw = new FileWriter(getDirectory(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.getNameCustomer() + "-" + this.getNameAgency() + "-" +
                    this.getAccountType() + "-" + this.getNumberAccount());
            bw.newLine();
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Byte countAccounts() {

        try {
            Byte cont = 0;
            File file = new File(getDirectory());
            FileReader readerFile = new FileReader(file);
            BufferedReader item = new BufferedReader(readerFile);
            String text = item.readLine();
            while (text != null) {
                cont++;
                if (cont ==2){
                    return cont;
                }
                text = item.readLine();
            }
            item.close();
            return cont;
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
            return 0;
        }
    }
      public int accountExists(String agencia, String conta) {

        try {

            String[] contas;
            File file = new File(getDirectory());
            FileReader readerFile = new FileReader(file);
            BufferedReader item = new BufferedReader(readerFile);

            List<String> nome = new ArrayList<>();
            String text = item.readLine();
            while (text != null) {
                contas = text.split("-");
                if (contas[1].equalsIgnoreCase(agencia) && contas[3].equalsIgnoreCase(conta)){
                    return 1;
                }
               text = item.readLine();
            }
            item.close();
            return 0;

        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
            return 0;
        }
    }
}