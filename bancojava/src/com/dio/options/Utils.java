package com.dio.options;
import com.dio.operations.Accounts;
import com.dio.operations.Agency;
import com.dio.operations.Bank;
import com.dio.operations.Client;

public class Utils {
    Bank countBank = new Bank();
    Agency agency = new Agency();
    Client client = new Client();
    Accounts conta = new Accounts();
    //Verificar se tem banco Cadastrado
    public boolean bancoCadastrado(){
        if (!countBank.countBank()){
            System.out.println("Nenhum banco cadastrado. Cadastre ao menos um!");
            System.out.println();
            return true;
        }else{
            return false;
        }
    }
    //Verificar se tem agência Cadastrada
    public boolean agenciaCadastrada(){
        if(!agency.countAgency()) {
            System.out.println("Nenhuma agencia cadastrada. Cadastre ao menos uma!");
            System.out.println();
            return true;
        }else{
            return false;
        }
    }
    public  boolean clienteCadastrado(){
        if(!client.countCliente()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre ao menos um!");
            System.out.println();
            return true;
        }else{
            return false;
        }
    }
    public boolean contasCadastradas(String transferencia){
        if (conta.countAccounts() < 2 ) {
            System.out.println("Necessario ao menos duas contas para essa operacao!");
            System.out.println();
                return true;
            }else{
                return false;
            }
    }
    public boolean contasCadastradas(){
        if (conta.countAccounts() < 1 ) {
            System.out.println("Necessario ao menos uma conta para essa operacao!");
            System.out.println();
            return true;
        }else{
            return false;
        }
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }


}
