package com.dio.getPath;
import javax.xml.stream.FactoryConfigurationError;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetPath {

    private String tabela;

    public GetPath(String tabela) {
      this.tabela =tabela;
   }

    public GetPath() {
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String tabelaExiste(){

        Path path = Paths.get(this.tabela);
        Path directoryName = Paths.get("");
        String diretorio =  directoryName.toAbsolutePath().toString();

//        System.out.println(diretorio);
//        System.out.println(path);

        if (path.toFile().isFile()){
            diretorio += "/" + this.tabela;
            return diretorio;
        }else{
            File arquivo = new File(diretorio, this.tabela);
            try {
                arquivo.createNewFile();
                diretorio += "/" + this.tabela;
                return diretorio;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
