import java.util.Scanner;
import java.text.ParseException;

public class AppInsercaoOrdenada {
    public static void main(String[] args) throws ParseException {
        LeituraCriacaoSinalizacoes leitura = new LeituraCriacaoSinalizacoes();
        Scanner in = new Scanner(System.in);
        leitura.lerArquivos();

        int opcao;
        do {
            System.out.println("Selecione uma opção da lista: ");
            System.out.println("1 - Apresentar o nome do logradouro que tem mais sinalizações registradas");
            System.out
                    .println("2 - Apresentar o mês em que mais foram implantadas mais sinalizaçõoes em um logradouro.");
            System.out.println("3 - Iniciar modo navegação entre logradouros.");
            System.out.println("0 - Sair do sistema.");
            opcao = in.nextInt();
            in.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println(leitura.getRuaMaisSinalizada());
                    break;
                case 2:
                    System.out.println(leitura.getMesMaisSinalizacoes());
                    break;
                case 3:
                    leitura.modoNavegacao();
                    break;
                case 0:
                    in.close();
                    System.out.println("Encerrando programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }
}
