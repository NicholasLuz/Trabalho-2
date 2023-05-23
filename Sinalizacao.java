import java.time.LocalDate;

public class Sinalizacao {
    // Atributos
    private String descricao;
    private LocalDate implantacao;
    private double numInicial;
    private double numFinal;
    private String lado;
    private String localDeInstalacao;
    // Metodos

    public Sinalizacao(String descricao, int diaImplantacao, int mesImplantacao, int anoImplantacao, double num_inicial,
            double num_final, String lado, String local_de_instalacao) {
        this.descricao = descricao;
        this.implantacao = LocalDate.of(anoImplantacao, mesImplantacao, diaImplantacao);
        this.numInicial = num_inicial;
        this.numFinal = num_final;
        this.lado = lado;
        this.localDeInstalacao = local_de_instalacao;
    }

    /* Métodos get e toString (para data de implantação, imprimir dd/mm/aa) */

    public String getDescricao() {
        return descricao;
    }

    public int getAnoImplantacao() {
        return implantacao.getYear();
    }

    public int getMesImplantacao() {
        return implantacao.getMonthValue();
    }

    public int getDiaImplantacao() {
        return implantacao.getDayOfMonth();
    }

    public LocalDate getLocalDate() {
        return implantacao;
    }

    public double getNumInicial() {
        return numInicial;
    }

    public double getNumFinal() {
        return numFinal;
    }

    public String getLado() {
        return lado;
    }

    public String getLocalDeInstalacao() {
        return localDeInstalacao;
    }

    public String toString() {
        return ("------ Sinalização ------" + '\n' +
                "descricao: " + descricao +
                ", data implantacao: " + implantacao +
                ", numero Inicial: " + numInicial +
                ", numero final: " + numFinal +
                ", lado: " + lado +
                ", local de instalacao: " + localDeInstalacao + '\n' +
                "-------------------------");
    }
}
