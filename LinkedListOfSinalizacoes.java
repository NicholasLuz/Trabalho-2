public class LinkedListOfSinalizacoes {
  private class Node {
    public Sinalizacao sinalizacao;
    public Node next;

    public Node(Sinalizacao sinalizacao) {
      this.sinalizacao = sinalizacao;
      next = null;
    }
  }

  private Node head;
  private Node tail;
  private int count;

  public LinkedListOfSinalizacoes() {
    head = null;
    tail = null;
    count = 0;
  }

  public void add(Sinalizacao novaSinalizacao) {
    Node aux = new Node(novaSinalizacao);
    if (head == null) {
      head = aux;
    } else {
      tail.next = aux;
    }
    tail = aux;
    count++;
  }

  public int size() {
    return count;
  }

  public int getMes(int index) {
    if ((index < 0) || (index >= count)) {
      throw new IndexOutOfBoundsException();
    }
    if (index == count - 1)
      return tail.sinalizacao.getMesImplantacao();

    Node aux = head;
    int contador = 0;

    while (contador < index) {
      aux = aux.next;
      contador++;
    }

    return aux.sinalizacao.getMesImplantacao();
  }

  public int[] sinalizacoesNoMes() {
    Node aux = head;
    int[] meses = new int[12];

    while (aux != null) {
      switch (aux.sinalizacao.getMesImplantacao()) {
        case 1:
          meses[0]++;
          break;
        case 2:
          meses[1]++;
          break;
        case 3:
          meses[2]++;
          break;
        case 4:
          meses[3]++;
          break;
        case 5:
          meses[4]++;
          break;
        case 6:
          meses[5]++;
          break;
        case 7:
          meses[6]++;
          break;
        case 8:
          meses[7]++;
          break;
        case 9:
          meses[8]++;
          break;
        case 10:
          meses[9]++;
          break;
        case 11:
          meses[10]++;
          break;
        case 12:
          meses[11]++;
          break;
        default:
          break;
      }
      aux = aux.next;
    }
    return meses;
  }

  public Sinalizacao getUltimaSinalizacao() {
    Sinalizacao sinalizacao = head.sinalizacao;
    Node aux = head.next;

    while (aux != null) {
      if (aux.sinalizacao.getLocalDate().isAfter(sinalizacao.getLocalDate())) {
        sinalizacao = aux.sinalizacao;
      }
      aux = aux.next;
    }

    return sinalizacao;
  }

  public Sinalizacao getPrimeiraSinalizacao() {
    Sinalizacao sinalizacao = head.sinalizacao;
    Node aux = head.next;

    while (aux != null) {
      if (aux.sinalizacao.getLocalDate().isBefore(sinalizacao.getLocalDate())) {
        sinalizacao = aux.sinalizacao;
      }
      aux = aux.next;
    }

    return sinalizacao;
  }

}
