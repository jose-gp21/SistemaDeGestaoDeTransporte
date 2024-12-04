package gestao.transporte;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    // Instâncias dos DAOs
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static MotoristaDAO motoristaDAO = new MotoristaDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static ViagemDAO viagemDAO = new ViagemDAO();

    // Listas gerenciadas pelos DAOs
    private static List<Cliente> clientes = clienteDAO.carregarClientes();
    private static List<Motorista> motoristas = motoristaDAO.carregarMotoristas();
    private static List<Produto> produtos = produtoDAO.carregarProdutos();
    private static List<Viagem> viagens = viagemDAO.carregarViagens(produtos, motoristas, clientes);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Sistema de Gestão de Transporte de Carga ---");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Motoristas");
            System.out.println("3. Gerenciar Produtos");
            System.out.println("4. Gerenciar Viagens");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuMotoristas();
                case 3 -> menuProdutos();
                case 4 -> menuViagens();
                case 5 -> {
                    System.out.println("Encerrando o sistema...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // --- Métodos Utilitários para Leitura ---
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Retorna uma opção inválida em caso de erro
        }
    }

    private static String lerString(String mensagem) {
        System.out.print(mensagem + ": ");
        return scanner.nextLine();
    }

    private static int lerInt(String mensagem) {
        System.out.print(mensagem + ": ");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. " + mensagem + ": ");
            }
        }
    }

    // --- Métodos Utilitários para Operações em Listas ---
    private static <T> void listar(List<T> lista, String tipo) {
        System.out.println("\n--- Listar " + tipo + " ---");
        if (lista.isEmpty()) {
            System.out.println("Nenhum(a) " + tipo.toLowerCase() + " encontrado(a).");
        } else {
            for (T item : lista) {
                System.out.println(item);
            }
        }
    }

    private static <T> T buscarPorId(List<T> lista, String tipo) {
        System.out.print("Digite o ID do(a) " + tipo + ": ");
        int id = lerInt("ID");
        for (T item : lista) {
            if (item instanceof Cliente cliente && cliente.getId() == id) return item;
            if (item instanceof Motorista motorista && motorista.getId() == id) return item;
            if (item instanceof Produto produto && produto.getId() == id) return item;
            if (item instanceof Viagem viagem && viagem.getId() == id) return item;
        }
        System.out.println(tipo + " não encontrado(a).");
        return null;
    }

    private static <T> void remover(List<T> lista, String tipo, Object dao) {
        T item = buscarPorId(lista, tipo);
        if (item != null) {
            lista.remove(item);
            if (dao instanceof ClienteDAO clienteDAO) clienteDAO.salvarClientes(clientes);
            if (dao instanceof MotoristaDAO motoristaDAO) motoristaDAO.salvarMotoristas(motoristas);
            if (dao instanceof ProdutoDAO produtoDAO) produtoDAO.salvarProdutos(produtos);
            if (dao instanceof ViagemDAO viagemDAO) viagemDAO.salvarViagens(viagens);
            System.out.println(tipo + " removido(a) com sucesso!");
        }
    }

    // --- Menus para Clientes, Motoristas, Produtos e Viagens ---
    private static void menuClientes() {
        System.out.println("\n--- Gerenciar Clientes ---");
        System.out.println("1. Listar Clientes");
        System.out.println("2. Cadastrar Cliente");
        System.out.println("3. Editar Cliente");
        System.out.println("4. Remover Cliente");
        System.out.print("Escolha uma opção: ");

        switch (lerOpcao()) {
            case 1 -> listar(clientes, "Clientes");
            case 2 -> cadastrarCliente();
            case 3 -> editarCliente();
            case 4 -> remover(clientes, "Cliente", clienteDAO);
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarCliente() {
        Cliente cliente = new Cliente(lerString("Nome"), lerString("CPF"), lerString("Cidade"));
        clientes.add(cliente);
        clienteDAO.salvarClientes(clientes);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void editarCliente() {
        Cliente cliente = buscarPorId(clientes, "Cliente");
        if (cliente != null) {
            cliente.setNome(lerString("Novo Nome"));
            cliente.setCpf(lerString("Novo CPF"));
            cliente.setCidade(lerString("Nova Cidade"));
            clienteDAO.salvarClientes(clientes);
            System.out.println("Cliente atualizado com sucesso!");
        }
    }

    private static void menuMotoristas() {
        System.out.println("\n--- Gerenciar Motoristas ---");
        System.out.println("1. Listar Motoristas");
        System.out.println("2. Cadastrar Motorista");
        System.out.println("3. Editar Motorista");
        System.out.println("4. Remover Motorista");
        System.out.print("Escolha uma opção: ");

        switch (lerOpcao()) {
            case 1 -> listar(motoristas, "Motoristas");
            case 2 -> cadastrarMotorista();
            case 3 -> editarMotorista();
            case 4 -> remover(motoristas, "Motorista", motoristaDAO);
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarMotorista() {
        Motorista motorista = new Motorista(
            lerString("Nome"),
            lerString("CPF"),
            lerString("CNH"),
            lerString("Cidade")
        );
        motoristas.add(motorista);
        motoristaDAO.salvarMotoristas(motoristas);
        System.out.println("Motorista cadastrado com sucesso!");
    }

    private static void editarMotorista() {
        Motorista motorista = buscarPorId(motoristas, "Motorista");
        if (motorista != null) {
            motorista.setNome(lerString("Novo Nome"));
            motorista.setCpf(lerString("Novo CPF"));
            motorista.setCnh(lerString("Nova CNH"));
            motorista.setCidade(lerString("Nova Cidade"));
            motoristaDAO.salvarMotoristas(motoristas);
            System.out.println("Motorista atualizado com sucesso!");
        }
    }

    private static void menuProdutos() {
        System.out.println("\n--- Gerenciar Produtos ---");
        System.out.println("1. Listar Produtos");
        System.out.println("2. Cadastrar Produto");
        System.out.println("3. Editar Produto");
        System.out.println("4. Remover Produto");
        System.out.print("Escolha uma opção: ");

        switch (lerOpcao()) {
            case 1 -> listar(produtos, "Produtos");
            case 2 -> cadastrarProduto();
            case 3 -> editarProduto();
            case 4 -> remover(produtos, "Produto", produtoDAO);
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarProduto() {
        Produto produto = new Produto(lerString("Nome"), lerInt("Quantidade"));
        produtos.add(produto);
        produtoDAO.salvarProdutos(produtos);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void editarProduto() {
        Produto produto = buscarPorId(produtos, "Produto");
        if (produto != null) {
            produto.setNome(lerString("Novo Nome"));
            produto.setQuantidade(lerInt("Nova Quantidade"));
            produtoDAO.salvarProdutos(produtos);
            System.out.println("Produto atualizado com sucesso!");
        }
    }

    private static void menuViagens() {
        System.out.println("\n--- Gerenciar Viagens ---");
        System.out.println("1. Iniciar Viagem");
        System.out.println("2. Finalizar Viagem");
        System.out.print("Escolha uma opção: ");

        switch (lerOpcao()) {
            case 1 -> iniciarViagem();
            case 2 -> finalizarViagem();
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void iniciarViagem() {
        Viagem viagem = new Viagem(
            lerString("Descrição"),
            lerString("Cidade de Origem"),
            lerString("Cidade de Destino"),
            selecionarProdutos(),
            selecionarMotorista(),
            selecionarCliente()
        );
        viagens.add(viagem);
        viagemDAO.salvarViagens(viagens);
        System.out.println("Viagem iniciada com sucesso!");
    }

    private static void finalizarViagem() {
        Viagem viagem = buscarPorId(viagens, "Viagem");
        if (viagem != null) {
            viagem.finalizarViagem();
            viagemDAO.salvarViagens(viagens);
            System.out.println("Viagem finalizada com sucesso!");
        }
    }

    private static List<Produto> selecionarProdutos() {
        System.out.println("\nSelecione os Produtos (IDs separados por vírgula):");
        listar(produtos, "Produtos");
        List<Produto> produtosSelecionados = new ArrayList<>();
        String[] ids = scanner.nextLine().split(",");
        for (String idStr : ids) {
            try {
                int id = Integer.parseInt(idStr.trim());
                Produto produto = produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
                if (produto != null) produtosSelecionados.add(produto);
            } catch (NumberFormatException ignored) {
            }
        }
        return produtosSelecionados;
    }

    private static Motorista selecionarMotorista() {
        listar(motoristas, "Motoristas");
        return buscarPorId(motoristas, "Motorista");
    }

    private static Cliente selecionarCliente() {
        listar(clientes, "Clientes");
        return buscarPorId(clientes, "Cliente");
    }
}
