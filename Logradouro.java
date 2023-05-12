public class Logradouro {
  private String nomeCompleto;
  private String tipo;
  private String nome;
  private LinkedListOfSinalizacoes listaSinalizacoes;

  public Logradouro(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
    this.tipo = nomeCompleto.split(" ", 2)[0];
    this.nome = nomeCompleto.split(" ", 2)[1];
    this.listaSinalizacoes = new LinkedListOfSinalizacoes();
  }

  public String getNome() {
    return this.nome;
  }

  public String getNomeCompleto() {
    return this.nomeCompleto;
  }

  public void addSinalizacao(Sinalizacao sinalizacao) {
    listaSinalizacoes.add(sinalizacao);
  }

  public int quantidadeSinalizacoes() {
    return listaSinalizacoes.size();
  }

  public int[] getSinalizacoesMes() {
    return listaSinalizacoes.sinalizacoesNoMes();
  }

  public Sinalizacao getUltimaSinalizacao() {
    return listaSinalizacoes.getUltimaSinalizacao();
  }

  public Sinalizacao getPrimeiraSinalizacao() {
    return listaSinalizacoes.getPrimeiraSinalizacao();
  }

  @Override
  public String toString() {
    return "----- Logradouro -----" +
        "NomeCompleto: " + nomeCompleto + '\'' +
        ", tipo: " + tipo + '\'' +
        ", nome, " + nome + '\'' +
        "-------------------------";
  }
}
