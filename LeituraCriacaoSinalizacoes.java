import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LeituraCriacaoSinalizacoes {
    private DoubleLinkedListOfLogradouros listaLogradouros;
    private Scanner in;

    public LeituraCriacaoSinalizacoes() {
        this.in = new Scanner(System.in);
        this.listaLogradouros = new DoubleLinkedListOfLogradouros();
    }

    public void lerArquivos() throws ParseException {

        String linhas[] = new String[110000];
        int numLinhas = 0;

        Path filePath = Paths.get("dataEditado.csv");

        // Ler o arquivo
        try (BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8"))) {
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                linhas[numLinhas] = line;
                numLinhas++;
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.err.format("Erro na leitura do arquivo: ", e.getMessage());
        }

        // Mude numLinhas para algum numero pequeno para executar testes mais
        // rapidamente.
        // Ex:
        for (int i = 0; i < 50; i++) {
            // for (int i = 0; i < numLinhas; i++) {
            String[] campos = linhas[i].split(";");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

            String descricao = campos[1];

            int anoImplantacao = 0;
            int mesImplantacao = 0;
            int diaImplantacao = 0;
            if (!campos[4].equals("")) {
                if (campos[4].contains("-"))
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                else
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(campos[4], formatter);
                anoImplantacao = date.getYear();
                mesImplantacao = date.getMonthValue();
                diaImplantacao = date.getDayOfMonth();
            }

            String nomeLogradouro = campos[5];

            double numInicial;
            if (campos[6].equals(""))
                numInicial = 0;
            else
                numInicial = Double.parseDouble(campos[6]);

            double numFinal;
            if (campos[7].equals(""))
                numFinal = 0;
            else
                numFinal = Double.parseDouble(campos[7]);

            String lado = campos[10];
            String localInstalacao = "";
            if (campos.length >= 13)
                localInstalacao = campos[12];

            Sinalizacao novaSinalizacao = new Sinalizacao(descricao, diaImplantacao, mesImplantacao, anoImplantacao,
                    numInicial, numFinal, lado, localInstalacao);

            listaLogradouros.orderedAdd(nomeLogradouro, novaSinalizacao);
        }
    }

    public String getRuaMaisSinalizada() {
        return listaLogradouros.getLogradouroMaisSinalizado().getNomeCompleto();
    }

    public String getMesMaisSinalizacoes() {
        return listaLogradouros.getMesMaisSinalizacoes();
    }

    public void modoNavegacao() {
        listaLogradouros.reset();

        int opcao;
        do {
            System.out.println("Selecione uma opção da lista: ");
            System.out.println("1 - Avançar para o próximo logradouro.");
            System.out
                    .println("2 - Voltar para o último logradouro.");
            System.out.println("3 - Mostrar logradouro atual.");
            System.out.println("0 - Sair do modo navegação.");
            opcao = in.nextInt();
            in.nextLine();

            switch (opcao) {
                case 1:
                    Logradouro proxLogradouro = listaLogradouros.next();
                    if (proxLogradouro == null) {
                        System.out.println("Este é o último logradouro.");
                    } else {
                        System.out.println("Indo para o próximo logradouro.");
                    }
                    break;
                case 2:
                    Logradouro ultimoLogradouro = listaLogradouros.prev();
                    if (ultimoLogradouro == null) {
                        System.out.println("Este é o primeiro logradouro.");
                    } else {
                        System.out.println("Indo para o logradouro anterior.");
                    }
                    break;
                case 3:
                    System.out.println(listaLogradouros.atual().toString());
                    System.out.println(listaLogradouros.atual().quantidadeSinalizacoes() + " sinalizações.");
                    try {
                        System.out.println("Primeira sinalização: "
                                + listaLogradouros.atual().getPrimeiraSinalizacao().toString());
                        System.out.println(
                                "Última sinalização: " + listaLogradouros.atual().getUltimaSinalizacao().toString());
                    } catch (NullPointerException e) {
                        System.out.println("Não há sinalizações registradas nesse logradouro.");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do modo navegação.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
    }
}
