package gestao.transporte;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private static final String FILE_NAME = "produtos.txt";

    // Salvar todos os produtos no arquivo
    public void salvarProdutos(List<Produto> produtos) {
        List<String> lines = new ArrayList<>();
        for (Produto produto : produtos) {
            lines.add(produto.getId() + ";" + produto.getNome() + ";" + produto.getQuantidade());
        }
        FileManager.salvar(FILE_NAME, lines);
    }

    // Carregar todos os produtos do arquivo
    public List<Produto> carregarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        List<String> lines = FileManager.carregar(FILE_NAME);

        for (String line : lines) {
            try {
                String[] data = line.split(";");
                if (data.length == 3) { // Verifica se a linha tem os 3 campos necessários
                    Produto produto = new Produto(
                        Integer.parseInt(data[0]), // ID
                        data[1],                  // Nome
                        Integer.parseInt(data[2]) // Quantidade
                    );
                    produtos.add(produto);
                } else {
                    System.out.println("Linha mal formatada ignorada: " + line);
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar a linha: " + line + " - " + e.getMessage());
            }
        }
        return produtos;
    }

    // Adicionar um novo produto e salvar no arquivo
    public void adicionarProduto(Produto produto) {
        List<Produto> produtos = carregarProdutos();
        produtos.add(produto);
        salvarProdutos(produtos);
    }

    // Atualizar um produto existente no arquivo
    public boolean editarProduto(int id, Produto produtoAtualizado) {
        List<Produto> produtos = carregarProdutos();
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produto.setNome(produtoAtualizado.getNome());
                produto.setQuantidade(produtoAtualizado.getQuantidade());
                salvarProdutos(produtos);
                return true;
            }
        }
        return false;
    }

    // Remover um produto pelo ID e salvar no arquivo
    public boolean removerProduto(int id) {
        List<Produto> produtos = carregarProdutos();
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produtos.remove(produto);
                salvarProdutos(produtos);
                return true;
            }
        }
        return false;
    }

    // Buscar um produto pelo ID
    public Produto buscarProdutoPorId(int id) {
        List<Produto> produtos = carregarProdutos();
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    // Listar todos os produtos
    public List<Produto> listarProdutos() {
        return carregarProdutos();
    }
}
