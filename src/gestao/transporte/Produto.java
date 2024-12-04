package gestao.transporte;

public class Produto {
    private static int contadorID = 0; 
    private int id;
    private String nome;
    private int quantidade;

    public Produto(String nome, int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
        this.id = ++contadorID;
        this.nome = nome;
        this.quantidade = quantidade;
    }


    public Produto(int id, String nome, int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;


        if (id > contadorID) {
            contadorID = id;
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto [ID=" + id + ", Nome=" + nome + ", Quantidade=" + quantidade + "]";
    }
}
