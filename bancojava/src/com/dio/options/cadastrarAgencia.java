package com.dio.options;
import com.dio.operations.Agency;
import com.dio.uiScanner.userInput;
public class cadastrarAgencia {
    public static void cadastrarAgencia() {

        try {
            userInput ui = new userInput();
            int op;
            String numeroAgencia;
            String nomeAgencia;

            Agency agency = new Agency();
            Utils utils = new Utils();

            if( utils.bancoCadastrado()) return;

            do {
                do {

                   System.out.println();
                   System.out.println("Informe o numero da Agencia: ");
                   numeroAgencia =ui.getString("");
                   do {
                       if (!utils.isNumeric(numeroAgencia))
                        System.out.println("Informe numero valido!");
                   } while(!utils.isNumeric(numeroAgencia));

                   System.out.println();

                   System.out.println("Informe o nome da Agencia: ");
                   nomeAgencia =ui.getString("");
                   System.out.println();

                    if (numeroAgencia.equalsIgnoreCase("") || nomeAgencia.equalsIgnoreCase("")){
                        System.out.println("Informe o Numero/Nome da Agencia!");
                        System.out.println();
                    }

                } while (numeroAgencia.equalsIgnoreCase("") || nomeAgencia.equalsIgnoreCase(""));

                agency.setNumberAgency(numeroAgencia);
                agency.setNameAgency(nomeAgencia);

                if (agency.agencyExists()){
                    System.out.println("Agencia Cadastrada. Informe outro numero!");
                    op =0;
                }else{
                    op=1;
                }
            }while(op == 0);

            if (agency.registerAgency()) {
                System.out.println("Cadastrado com sucesso!");
                op = ui.getInt("Deseja cadastrar nova Agencia? 1- Sim 2 - Nao");
                if (op == 1) {
                    cadastrarAgencia();
                }
            } else {
                System.out.println("Erro no cadastro!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
