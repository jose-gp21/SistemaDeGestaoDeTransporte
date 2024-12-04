package gestao.transporte;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaGestao {
    private static Scanner scanner = new Scanner(System.in);

    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Motorista> motoristas = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();
    private static List<Viagem> viagens = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nSistema de Gestão de Transporte de Carga");
            System.out.println("1. Gerenciar Cliente");
            System.out.println("2. Gerenciar Motorista");
            System.out.println("3. Gerenciar Produto");
            System.out.println("4. Gerenciar Viagens");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuMotoristas();
                    break;
                case 3:
                    menuProdutos();
                    break;
                case 4:
                    menuViagens();
                    break;
                case 5:
                    System.out.println("Encerrando o sistema...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuClientes() {
        System.out.println("\n--- Gerenciar Cliente ---");
        System.out.println("1. Listar Clientes");
        System.out.println("2. Cadastrar Cliente");
        System.out.println("3. Editar Cliente");
        System.out.println("4. Remover Cliente");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                listarClientes();
                break;
            case 2:
                cadastrarCliente();
                break;
            case 3:
                editarCliente();
                break;
            case 4:
                removerCliente();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private static void cadastrarCliente() {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a cidade do cliente: ");
        String cidade = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf, cidade);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso.");
    }

    private static void editarCliente() {
        System.out.print("Digite o ID do cliente que deseja editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        Cliente cliente = buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do cliente (atual: " + cliente.getNome() + "): ");
        cliente.setNome(scanner.nextLine());
        System.out.print("Digite o novo CPF do cliente (atual: " + cliente.getCpf() + "): ");
        cliente.setCpf(scanner.nextLine());
        System.out.print("Digite a nova cidade do cliente (atual: " + cliente.getCidade() + "): ");
        cliente.setCidade(scanner.nextLine());

        System.out.println("Cliente atualizado com sucesso.");
    }

    private static void removerCliente() {
        System.out.print("Digite o ID do cliente que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        clientes.remove(cliente);
        System.out.println("Cliente removido com sucesso.");
    }

    private static Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    private static void menuMotoristas() {
        System.out.println("\n--- Gerenciar Motorista ---");
        System.out.println("1. Listar Motoristas");
        System.out.println("2. Cadastrar Motorista");
        System.out.println("3. Editar Motorista");
        System.out.println("4. Remover Motorista");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                listarMotoristas();
                break;
            case 2:
                cadastrarMotorista();
                break;
            case 3:
                editarMotorista();
                break;
            case 4:
                removerMotorista();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void listarMotoristas() {
        if (motoristas.isEmpty()) {
            System.out.println("Nenhum motorista cadastrado.");
        } else {
            for (Motorista motorista : motoristas) {
                System.out.println(motorista);
            }
        }
    }

    private static void cadastrarMotorista() {
        System.out.print("Digite o nome do motorista: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do motorista: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a CNH do motorista: ");
        String cnh = scanner.nextLine();
        System.out.print("Digite a cidade do motorista: ");
        String cidade = scanner.nextLine();

        Motorista motorista = new Motorista(nome, cpf, cnh, cidade);
        motoristas.add(motorista);
        System.out.println("Motorista cadastrado com sucesso.");
    }

    private static void editarMotorista() {
        System.out.print("Digite o ID do motorista que deseja editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Motorista motorista = buscarMotoristaPorId(id);
        if (motorista == null) {
            System.out.println("Motorista não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do motorista (atual: " + motorista.getNome() + "): ");
        motorista.setNome(scanner.nextLine());
        System.out.print("Digite o novo CPF do motorista (atual: " + motorista.getCpf() + "): ");
        motorista.setCpf(scanner.nextLine());
        System.out.print("Digite a nova CNH do motorista (atual: " + motorista.getCnh() + "): ");
        motorista.setCnh(scanner.nextLine());
        System.out.print("Digite a nova cidade do motorista (atual: " + motorista.getCidade() + "): ");
        motorista.setCidade(scanner.nextLine());

        System.out.println("Motorista atualizado com sucesso.");
    }

    private static void removerMotorista() {
        System.out.print("Digite o ID do motorista que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Motorista motorista = buscarMotoristaPorId(id);
        if (motorista == null) {
            System.out.println("Motorista não encontrado.");
            return;
        }

        motoristas.remove(motorista);
        System.out.println("Motorista removido com sucesso.");
    }

    private static Motorista buscarMotoristaPorId(int id) {
        for (Motorista motorista : motoristas) {
            if (motorista.getId() == id) {
                return motorista;
            }
        }
        return null;
    }

    private static void menuProdutos() {
        System.out.println("\n--- Gerenciar Produto ---");
        System.out.println("1. Listar Produtos");
        System.out.println("2. Cadastrar Produto");
        System.out.println("3. Editar Produto");
        System.out.println("4. Remover Produto");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                listarProdutos();
                break;
            case 2:
                cadastrarProduto();
                break;
            case 3:
                editarProduto();
                break;
            case 4:
                removerProduto();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    private static void cadastrarProduto() {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a quantidade do produto: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Produto produto = new Produto(nome, quantidade);
        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso.");
    }

    private static void editarProduto() {
        System.out.print("Digite o ID do produto que deseja editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto produto = buscarProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do produto (atual: " + produto.getNome() + "): ");
        produto.setNome(scanner.nextLine());
        System.out.print("Digite a nova quantidade do produto (atual: " + produto.getQuantidade() + "): ");
        produto.setQuantidade(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Produto atualizado com sucesso.");
    }

    private static void removerProduto() {
        System.out.print("Digite o ID do produto que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto produto = buscarProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        produtos.remove(produto);
        System.out.println("Produto removido com sucesso.");
    }

    private static Produto buscarProdutoPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    private static void menuViagens() {
        System.out.println("\n--- Gerenciar Viagens ---");
        System.out.println("1. Iniciar Viagem");
        System.out.println("2. Finalizar Viagem");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                iniciarViagem();
                break;
            case 2:
                finalizarViagem();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void iniciarViagem() {
        System.out.print("Digite a descrição da viagem: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a cidade de origem: ");
        String cidadeOrigem = scanner.nextLine();
        System.out.print("Digite a cidade de destino: ");
        String cidadeDestino = scanner.nextLine();

        System.out.print("Digite o ID do motorista: ");
        int idMotorista = scanner.nextInt();
        scanner.nextLine();
        Motorista motorista = buscarMotoristaPorId(idMotorista);
        if (motorista == null) {
            System.out.println("Motorista não encontrado.");
            return;
        }

        System.out.print("Digite o ID do cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        List<Produto> produtosSelecionados = new ArrayList<>();
        while (true) {
            System.out.print("Digite o ID do produto (ou -1 para terminar): ");
            int idProduto = scanner.nextInt();
            scanner.nextLine();
            if (idProduto == -1) {
                break;
            }

            Produto produto = buscarProdutoPorId(idProduto);
            if (produto != null) {
                produtosSelecionados.add(produto);
            } else {
                System.out.println("Produto não encontrado.");
            }
        }

        if (produtosSelecionados.isEmpty()) {
            System.out.println("É necessário selecionar pelo menos um produto.");
            return;
        }

        Viagem viagem = new Viagem(descricao, cidadeOrigem, cidadeDestino, produtosSelecionados, motorista, cliente);
        viagens.add(viagem);
        System.out.println("Viagem iniciada com sucesso.");
    }

    private static void finalizarViagem() {
        System.out.print("Digite o ID da viagem que deseja finalizar: ");
        int idViagem = scanner.nextInt();
        scanner.nextLine();

        for (Viagem viagem : viagens) {
            if (viagem.toString().contains("ID=" + idViagem)) {
                viagem.finalizarViagem();
                System.out.println("Viagem finalizada com sucesso.");
                return;
            }
        }

        System.out.println("Viagem não encontrada.");
    }
}
