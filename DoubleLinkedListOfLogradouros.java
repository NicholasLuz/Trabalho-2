
public class DoubleLinkedListOfLogradouros {
    // Classe interna Node
    private class Node {
        public Logradouro logradouro;
        public Node next;
        public Node prev;

        public Node(Logradouro logradouro) {
            this.logradouro = logradouro;
            next = null;
        }
    }

    // Referência para o primeiro elemento da lista encadeada.
    private Node header;
    // Referência para o último elemento da lista encadeada.
    private Node trailer;
    // Referencia para a posicao corrente.
    private Node current;
    // Contador para a quantidade de elementos que a lista contem.
    private int count;

    /**
     * Construtor da lista.
     */
    public DoubleLinkedListOfLogradouros() {
        header = new Node(null);
        trailer = new Node(null);
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    /**
     * Retorna true se a lista nao contem elementos.
     * 
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Retorna o numero de elementos da lista.
     * 
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        header.next = trailer;
        trailer.prev = header;
        count = 0;
    }

    /**
     * Adiciona um elemento ao final da lista.
     * 
     * @param element elemento a ser adicionado ao final da lista
     */
    public void orderedAdd(String nomeLogradouro, Sinalizacao sinalizacao) {
        Node aux = containsElement(nomeLogradouro); // verifica se ja contem nome da Rua para não inserir duplicado
        if (aux != null) {
            aux.logradouro.addSinalizacao(sinalizacao);
        } else { // se nao contem rua, insere
            Logradouro novoLogradouro = new Logradouro(nomeLogradouro);
            Node n = new Node(novoLogradouro);
            n.logradouro.addSinalizacao(sinalizacao);

            if (header.next == trailer) {
                // se a lista está vazia
                n.prev = header;
                n.next = trailer;
                trailer.prev = n;
                header.next = n;

            } else if (novoLogradouro.getNome().compareTo(header.next.logradouro.getNome()) < 0) {
                // se for menor que o primeiro, insere no inicio
                n.next = header.next;
                n.prev = header;
                header.next = n;
                n.next.prev = n;
            } else if (novoLogradouro.getNome().compareTo(trailer.prev.logradouro.getNome()) > 0) {
                // se for maior que o ultimo, insere no final
                n.next = trailer;
                n.prev = trailer.prev;
                trailer.prev.next = n;
                trailer.prev = n;
            } else {
                // senao procura a posicao correta para insercao
                aux = header.next;
                boolean inseriu = false;
                while (aux != trailer && !inseriu) {
                    if (novoLogradouro.getNome().compareTo(aux.logradouro.getNome()) < 0) {
                        inseriu = true;
                        n.next = aux;
                        n.prev = aux.prev;
                        aux.prev.next = n;
                        aux.prev = n;
                    }
                    aux = aux.next;
                }
            }
            count++;
        }
    }

    private Node containsElement(String nomeLogradouro) {
        Node aux = header.next;

        while (aux != trailer) {
            if (aux.logradouro.getNomeCompleto().equals(nomeLogradouro)) {
                return aux;
            }
            aux = aux.next;
        }

        return null;
    }

    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * 
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public Logradouro get(int index) {
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException();
        }
        Node aux = getNodeIndex(index);
        return aux.logradouro;
    }

    // Metodo que tem como objetivo retornar uma referencia
    // para o nodo da posicao "index" recebida como parametro.
    // Por exemplo, se index for 2, ele retorna a referencia
    // para o nodo da posicao 2.
    private Node getNodeIndex(int index) {
        Node aux = null;
        if (index < count / 2) { // caminha do inicio para o meio
            aux = header.next;
            for (int i = 0; i < index; i++)
                aux = aux.next;
        } else { // caminha do fim para o meio
            aux = trailer.prev;
            for (int i = count - 1; i > index; i--)
                aux = aux.prev;
        }
        return aux;
    }

    /**
     * Inicializa o current na primeira posicao (para percorrer do inicio para o
     * fim).
     */
    public void reset() {
        current = header.next;
    }

    /**
     * Retorna o elemento da posicao corrente e faz current apontar para o proximo
     * elemento da lista.
     * 
     * @return elemento da posicao corrente
     */
    public Logradouro next() {
        if (current != trailer.prev) {
            Logradouro logradouro = current.logradouro;
            current = current.next;
            return logradouro;
        }
        return null;
    }

    public Logradouro prev() {
        if (current != header.next) {
            Logradouro logradouro = current.logradouro;
            current = current.prev;
            return logradouro;
        }
        return null;
    }

    public Logradouro atual() {
        return current.logradouro;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node aux = header.next;
        for (int i = 0; i < count; i++) {
            s.append(aux.logradouro);
            s.append("\n");
            aux = aux.next;
        }
        return s.toString();
    }

    public Logradouro getLogradouroMaisSinalizado() {
        Logradouro logradouro = header.next.logradouro;
        Node aux = header.next;
        while (aux != trailer) {
            if (aux.logradouro.quantidadeSinalizacoes() > logradouro.quantidadeSinalizacoes()) {
                logradouro = aux.logradouro;
            }
            aux = aux.next;
        }

        return logradouro;
    }

    public String getMesMaisSinalizacoes() {
        Node aux = header.next;
        int[] meses = new int[12];

        while (aux != trailer) {
            int[] mesesLogradouro = aux.logradouro.getSinalizacoesMes();

            for (int j = 0; j < meses.length; j++) {
                meses[j] += mesesLogradouro[j];
            }

            aux = aux.next;
        }

        int maior = meses[0];
        int mes = 0;

        for (int i = 1; i < 12; i++) {
            if (meses[i] > maior) {
                maior = meses[i];
                mes = i;
            }
        }
        String nome;
        switch (mes) {
            case 0:
                nome = "Janeiro";
                break;
            case 1:
                nome = "Fevereiro";
                break;
            case 2:
                nome = "Março";
                break;
            case 3:
                nome = "Abril";
                break;
            case 4:
                nome = "Maio";
                break;
            case 5:
                nome = "Junho";
                break;
            case 6:
                nome = "Julho";
                break;
            case 7:
                nome = "Agosto";
                break;
            case 8:
                nome = "Setembro";
                break;
            case 9:
                nome = "Outubro";
                break;
            case 10:
                nome = "Novembro";
                break;
            case 11:
                nome = "Dezembro";
                break;
            default:
                nome = "";
                break;
        }

        return "O mês de " + nome + " foi o mês com maior quantidade de sinalizações, com " + maior + " sinalizações.";
    }
}
