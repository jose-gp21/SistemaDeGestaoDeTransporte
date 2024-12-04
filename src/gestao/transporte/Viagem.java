package gestao.transporte;

import java.util.List;

public class Viagem {
    private static int contadorID = 0; // Para gerenciar IDs únicos
    private int id;
    private String descricao;
    private String cidadeOrigem;
    private String cidadeDestino;
    private List<Produto> produtos;
    private Motorista motorista;
    private Cliente cliente;
    private String status; // Status: "Iniciada" ou "Finalizada"

    // Construtor principal para novas viagens
    public Viagem(String descricao, String cidadeOrigem, String cidadeDestino, List<Produto> produtos,
                  Motorista motorista, Cliente cliente) {
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A viagem deve incluir ao menos um produto.");
        }
        if (motorista == null) {
            throw new IllegalArgumentException("A viagem deve ter um motorista.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("A viagem deve ter um cliente.");
        }

        this.id = ++contadorID;
        this.descricao = descricao;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.produtos = produtos;
        this.motorista = motorista;
        this.cliente = cliente;
        this.status = "Iniciada";
    }

    // Construtor para carregar viagens de um arquivo
    public Viagem(int id, String descricao, String cidadeOrigem, String cidadeDestino, List<Produto> produtos,
                  Motorista motorista, Cliente cliente, String status) {
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A viagem deve incluir ao menos um produto.");
        }
        if (motorista == null) {
            throw new IllegalArgumentException("A viagem deve ter um motorista.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("A viagem deve ter um cliente.");
        }

        this.id = id;
        this.descricao = descricao;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.produtos = produtos;
        this.motorista = motorista;
        this.cliente = cliente;
        this.status = status;

        // Atualiza o contador para evitar conflitos de IDs
        if (id > contadorID) {
            contadorID = id;
        }
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A viagem deve incluir ao menos um produto.");
        }
        this.produtos = produtos;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        if (motorista == null) {
            throw new IllegalArgumentException("A viagem deve ter um motorista.");
        }
        this.motorista = motorista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("A viagem deve ter um cliente.");
        }
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void finalizarViagem() {
        this.status = "Finalizada";
    }

    @Override
    public String toString() {
        return "Viagem [ID=" + id + ", Descrição=" + descricao + ", Origem=" + cidadeOrigem +
               ", Destino=" + cidadeDestino + ", Status=" + status + ", Motorista=" + motorista.getNome() +
               ", Cliente=" + cliente.getNome() + ", Produtos=" + produtos.size() + "]";
    }
}
