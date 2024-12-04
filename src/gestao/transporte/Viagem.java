package gestao.transporte;

import java.util.List;

public class Viagem {
    private String descricao;
    private String cidadeOrigem;
    private String cidadeDestino;
    private List<Produto> produtos;
    private Motorista motorista;
    private Cliente cliente;
    private String status;

    public Viagem(String descricao, String cidadeOrigem, String cidadeDestino, List<Produto> produtos,
                  Motorista motorista, Cliente cliente) {
        this.descricao = descricao;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.produtos = produtos;
        this.motorista = motorista;
        this.cliente = cliente;
        this.status = "Iniciada";
    }

    public void finalizarViagem() {
        this.status = "Finalizada";
    }

    @Override
    public String toString() {
        return "Viagem [Descrição=" + descricao + ", Cidade Origem=" + cidadeOrigem +
               ", Cidade Destino=" + cidadeDestino + ", Status=" + status + "]";
    }
}
