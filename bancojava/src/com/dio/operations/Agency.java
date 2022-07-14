package com.dio.operations;
import com.dio.getPath.GetPath;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Agency {
    private String numberAgency;
    private String nameAgency;
    private String directory;

    public Agency(String numberAgency, String nameAgency, String directory ) {
        this.numberAgency = numberAgency;
        this.nameAgency = nameAgency;
        this.directory = directory;
    }

    public Agency() {
        GetPath arquivo = new GetPath();
        arquivo.setTabela("agencia.txt");
        this.setDirectory(arquivo.tabelaExiste());
    }

    public String getNumberAgency() {
        return numberAgency;
    }

    public void setNumberAgency(String numberAgency) {
        this.numberAgency = numberAgency;
    }

    public String getNameAgency() {
        return nameAgency;
    }

    public void setNameAgency(String nameAgency) {
        this.nameAgency = nameAgency;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
    public boolean registerAgency() {

        try {
            FileWriter fw = new FileWriter(getDirectory(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write( this.getNumberAgency() + "-" + this.getNameAgency());
            bw.newLine();
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public List<String> listAgencies() {

        try {

            File file = new File(getDirectory());
            FileReader readerFile = new FileReader(file);
            BufferedReader item = new BufferedReader(readerFile);

            List<String> nameAgencies = new ArrayList<>();

            System.out.println("--------------------------------------------------------");
            System.out.println("Agencia(s) Cadastrada(s):");
            System.out.println("--------------------------------------------------------");

            String text = item.readLine();
            while (text != null) {
                nameAgencies.add(text);
                System.out.println(text);
                text = item.readLine();
            }
            System.out.println("--------------------------------------------------------");
            item.close();
            return nameAgencies;
        } catch (IOException e) {
            //System.out.println("Arquivo não encontrado");
            return null;
        }
    }
    public boolean agencyExists() {

        try {

            String[] dataAgencies;
            File file = new File(getDirectory());
            FileReader readerFile = new FileReader(file);
            BufferedReader item = new BufferedReader(readerFile);
            String text = item.readLine();

            while (text != null) {
                dataAgencies = text.split("-");
                if (dataAgencies[0].equalsIgnoreCase(getNumberAgency())){
                    return true;
                }
                text = item.readLine();
            }
            item.close();
            return false;
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
            return false;
        }
    }

    public boolean countAgency() {

        try {

            String[] dataAgencies;
            File file = new File(getDirectory());
            FileReader readerFile = new FileReader(file);
            BufferedReader item = new BufferedReader(readerFile);
            String text = item.readLine();
            if  (text != null) {
                return true;
            }
            item.close();
            return false;
        } catch (IOException e) {
            System.out.println("Arquivo nao encontrado");
            return false;
        }
    }
}
