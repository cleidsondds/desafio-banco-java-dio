package com.dio.operations;

import com.dio.getPath.GetPath;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private String identity;
    private String nameCustomer;
    private String directory;

    public Client(String identity, String nameCustomer, String directory) {
        this.identity = identity;
        this.nameCustomer = nameCustomer;
        this.directory = directory;
    }

    public Client() {
        GetPath arquivo = new GetPath();
        arquivo.setTabela("cliente.txt");
        this.setDirectory(arquivo.tabelaExiste());
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public boolean registerNewCustomer() {

        try {
            // Fluxo de saida de um arquivo
            FileWriter fw = new FileWriter(getDirectory(), true);
            BufferedWriter bw = new BufferedWriter(fw); // adiciono a um escritor de buffer
            bw.write(this.getIdentity() + "-" + this.getNameCustomer());
            bw.newLine();
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public List<String> listNameCustomer() {

        List<String> nome = new ArrayList<>();
        try {

            File file = new File(getDirectory());
            FileReader readerFile = new FileReader(file);
            BufferedReader item = new BufferedReader(readerFile);
            String text = item.readLine();
            while (text != null) {
                nome.add(text);
                text = item.readLine();
            }
            item.close();
            return nome;
        } catch (IOException e) {
            nome.add("");
            return nome;
        }
    }

    public boolean countCliente() {

        try {
            Byte cont = 0;
            File file = new File(getDirectory());
            FileReader readerFile = new FileReader(file);
            BufferedReader item = new BufferedReader(readerFile);
            String text = item.readLine();
            if (text != null) {
                item.close();
                return true;
            }
            return false;
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
            return false;
        }
    }


}
