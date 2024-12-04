package gestao.transporte;

import java.util.ArrayList;
import java.util.List;

public class ViagemDAO {
    private static final String FILE_NAME = "viagens.txt";

    // Salvar todas as viagens no arquivo
    public void salvarViagens(List<Viagem> viagens) {
        List<String> lines = new ArrayList<>();
        for (Viagem viagem : viagens) {
            StringBuilder produtosBuilder = new StringBuilder();
            for (Produto produto : viagem.getProdutos()) {
                produtosBuilder.append(produto.getId()).append(","); // Salva IDs dos produtos separados por vírgula
            }

            // Remove a última vírgula, se existir
            if (produtosBuilder.length() > 0) {
                produtosBuilder.deleteCharAt(produtosBuilder.length() - 1);
            }

            lines.add(viagem.getId() + ";" +
                      viagem.getDescricao() + ";" +
                      viagem.getCidadeOrigem() + ";" +
                      viagem.getCidadeDestino() + ";" +
                      produtosBuilder + ";" +
                      viagem.getMotorista().getId() + ";" +
                      viagem.getCliente().getId() + ";" +
                      viagem.getStatus());
        }
        FileManager.salvar(FILE_NAME, lines);
    }

    // Carregar todas as viagens do arquivo
    public List<Viagem> carregarViagens(List<Produto> produtos, List<Motorista> motoristas, List<Cliente> clientes) {
        List<Viagem> viagens = new ArrayList<>();
        List<String> lines = FileManager.carregar(FILE_NAME);

        for (String line : lines) {
            try {
                String[] data = line.split(";");
                if (data.length == 8) { // Verifica se a linha tem os 8 campos necessários
                    int id = Integer.parseInt(data[0]);
                    String descricao = data[1];
                    String cidadeOrigem = data[2];
                    String cidadeDestino = data[3];

                    // Reconstruir lista de produtos
                    List<Produto> produtosViagem = new ArrayList<>();
                    for (String produtoId : data[4].split(",")) {
                        Produto produto = produtos.stream()
                                .filter(p -> p.getId() == Integer.parseInt(produtoId))
                                .findFirst()
                                .orElse(null);
                        if (produto != null) {
                            produtosViagem.add(produto);
                        }
                    }

                    // Buscar motorista e cliente
                    Motorista motorista = motoristas.stream()
                            .filter(m -> m.getId() == Integer.parseInt(data[5]))
                            .findFirst()
                            .orElse(null);

                    Cliente cliente = clientes.stream()
                            .filter(c -> c.getId() == Integer.parseInt(data[6]))
                            .findFirst()
                            .orElse(null);

                    String status = data[7];

                    // Criar viagem
                    Viagem viagem = new Viagem(id, descricao, cidadeOrigem, cidadeDestino, produtosViagem, motorista, cliente, status);
                    viagens.add(viagem);
                } else {
                    System.out.println("Linha mal formatada ignorada: " + line);
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar a linha: " + line + " - " + e.getMessage());
            }
        }
        return viagens;
    }

    // Adicionar uma nova viagem e salvar no arquivo
    public void adicionarViagem(Viagem viagem, List<Produto> produtos, List<Motorista> motoristas, List<Cliente> clientes) {
        List<Viagem> viagens = carregarViagens(produtos, motoristas, clientes);
        viagens.add(viagem);
        salvarViagens(viagens);
    }

    // Listar todas as viagens
    public List<Viagem> listarViagens(List<Produto> produtos, List<Motorista> motoristas, List<Cliente> clientes) {
        return carregarViagens(produtos, motoristas, clientes);
    }

    // Buscar uma viagem pelo ID
    public Viagem buscarViagemPorId(int id, List<Produto> produtos, List<Motorista> motoristas, List<Cliente> clientes) {
        List<Viagem> viagens = carregarViagens(produtos, motoristas, clientes);
        for (Viagem viagem : viagens) {
            if (viagem.getId() == id) {
                return viagem;
            }
        }
        return null;
    }

    // Finalizar uma viagem
    public boolean finalizarViagem(int id, List<Produto> produtos, List<Motorista> motoristas, List<Cliente> clientes) {
        List<Viagem> viagens = carregarViagens(produtos, motoristas, clientes);
        for (Viagem viagem : viagens) {
            if (viagem.getId() == id) {
                viagem.finalizarViagem();
                salvarViagens(viagens);
                return true;
            }
        }
        return false;
    }
}
