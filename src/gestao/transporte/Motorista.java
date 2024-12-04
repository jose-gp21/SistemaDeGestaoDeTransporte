package gestao.transporte;

public class Motorista {
    private static int contadorID = 0; // Para gerenciar IDs únicos
    private int id;
    private String nome;
    private String cpf;
    private String cnh;
    private String cidade;

    // Construtor principal para novos motoristas
    public Motorista(String nome, String cpf, String cnh, String cidade) {
        this.id = ++contadorID; // Incrementa o ID automaticamente
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.cidade = cidade;
    }

    // Construtor para carregar motoristas do arquivo
    public Motorista(int id, String nome, String cpf, String cnh, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.cidade = cidade;

        // Atualiza o contador para evitar conflito de IDs
        if (id > contadorID) {
            contadorID = id;
        }
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Motorista [ID=" + id + ", Nome=" + nome + ", CPF=" + cpf + ", CNH=" + cnh + ", Cidade=" + cidade + "]";
    }
}
