package com.dio.operations;

import com.dio.getPath.GetPath;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String idBanco;
    public String nome;
    private String directory;

    public Bank(String idBanco, String nome, String directory) {
        this.idBanco = idBanco;
        this.nome = nome;
        this.directory = directory;
    }

    public Bank(){
        GetPath arquivo = new GetPath();
        arquivo.setTabela("banco.txt");
        this.setDirectory(arquivo.tabelaExiste());
    }

    public String getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(String idBanco) {
        this.idBanco = idBanco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public boolean registerNewBank() {

        try {
            // Fluxo de saida de um arquivo
            FileWriter fw = new FileWriter(getDirectory(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.getIdBanco() + "-" + this.getNome());
            bw.newLine();
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean countBank() {

        try {

            File file = new File(getDirectory());
            FileReader readFile = new FileReader(file);
            BufferedReader item = new BufferedReader(readFile);
            String text = item.readLine();
            if (text != null){
                return true;
            }
            item.close();
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
