import com.dio.uiScanner.userInput;
import static com.dio.options.cadastrarAgencia.cadastrarAgencia;
import static com.dio.options.cadastrarBanco.cadastrarBanco;
import static com.dio.options.cadastrarCliente.cadastrarCliente;
import static com.dio.options.criarConta.criarConta;
import static com.dio.options.imprimirExtrato.imprimirExtratoCliente;
import static com.dio.options.realizarOperacao.realizarOperacao;

public class menuOperates {
    public static void main(String[] args) {

        Integer opcao;
        userInput ui = new userInput();
        int cont = 9;

        do {

            System.out.println("Escolha uma opcao:");
            System.out.println("1 - Cadastrar um Novo Banco.");
            System.out.println("2 - Cadastrar uma Agencia.");
            System.out.println("3 - Cadastrar um Cliente");
            System.out.println("4 - Cadastrar uma Conta");
            System.out.println("5 - Realizar um deposito");
            System.out.println("6 - Realizar um saque");
            System.out.println("7 - Realizar uma transferencia");
            System.out.println("8 - Imprimir extrato");
            System.out.println("9 - Sair");

            try {
                do {
                    opcao = ui.getInt("Informe sua opcao: ");
                    if (opcao > cont | opcao < 1) {
                        System.out.println("Opcao Invalida!");
                    }
                } while (opcao > cont | opcao < 1);
            } catch (Exception e) {
                System.out.println("Opcao Invalida!");
                break;
            }

            switch (opcao) {

                case 1:
                    cadastrarBanco();
                    break;
                case 2:
                    cadastrarAgencia();
                    break;
                case 3:
                    cadastrarCliente();
                    break;

                case 4:
                    criarConta();
                    break;

                case 5:
                    realizarOperacao("d");
                    break;

                case 6:
                    realizarOperacao("s");
                    break;
                case 7:
                    realizarOperacao("t");
                    break;
                case 8:
                    imprimirExtratoCliente();
                    break;
                default:
                    System.out.println("Ate a proxima");
                    break;
            }
        } while (opcao < cont);

    }
}