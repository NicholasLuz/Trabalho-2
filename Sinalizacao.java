import java.time.LocalDate;

public class Sinalizacao {
	//Atributos
	private String descricao;
    private LocalDate implantacao;
    private double numInicial;
    private double numFinal;
    private String lado;
    private String localDeInstalacao;
	// Metodos
    private LocalDate implementacao;
	
    public Sinalizacao(String descricao, int diaImplantacao, int mesImplantacao, int anoImplçantacao, double num_inicial, double num_final, String lado, String local_de_instalacao){
        this.descricao = descricao;
        this.implantacao = LocalDate.of(anoImplçantacao, mesImplantacao, diaImplantacao);
        this.numInicial = num_inicial;
        this.numFinal = num_final;
    }

    /* Métodos get e toString (para data de implantação, imprimir dd/mm/aa) */

    public String getDescricao(){
        return descricao;
    }

    public int getAnoImplantacao() {
        return implementacao.getYear();
    }

    public int getMesImplantacao() {
        return implementacao.getMonthValue();
    }

    public int getDiaImplantacao() {
        return implementacao.getDayOfMonth();
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
}

