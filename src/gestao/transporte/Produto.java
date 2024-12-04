package gestao.transporte;

public class Produto {
    private static int contadorID = 0;
    private int id;
    private String nome;
    private int quantidade;

    public Produto(String nome, int quantidade) {
        this.id = ++contadorID;
        this.nome = nome;
        this.quantidade = quantidade;
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
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        } else {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
    }

    @Override
    public String toString() {
        return "Produto [ID=" + id + ", Nome=" + nome + ", Quantidade=" + quantidade + "]";
    }
}
