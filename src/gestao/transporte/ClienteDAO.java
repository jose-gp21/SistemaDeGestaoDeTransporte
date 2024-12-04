package gestao.transporte;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static final String FILE_NAME = "clientes.txt";

    // Salvar todos os clientes no arquivo
    public void salvarClientes(List<Cliente> clientes) {
        List<String> lines = new ArrayList<>();
        for (Cliente cliente : clientes) {
            lines.add(cliente.getId() + ";" + cliente.getNome() + ";" + cliente.getCpf() + ";" + cliente.getCidade());
        }
        FileManager.salvar(FILE_NAME, lines);
    }

    // Carregar todos os clientes do arquivo
    public List<Cliente> carregarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        List<String> lines = FileManager.carregar(FILE_NAME);

        for (String line : lines) {
            String[] data = line.split(";");
            Cliente cliente = new Cliente(data[1], data[2], data[3]); // Nome, CPF, Cidade
            cliente.setId(Integer.parseInt(data[0])); // Configurar ID
            clientes.add(cliente);
        }
        return clientes;
    }

    // Adicionar um novo cliente e salvar no arquivo
    public void adicionarCliente(Cliente cliente) {
        List<Cliente> clientes = carregarClientes();
        clientes.add(cliente);
        salvarClientes(clientes);
    }

    // Atualizar um cliente existente no arquivo
    public boolean editarCliente(int id, Cliente clienteAtualizado) {
        List<Cliente> clientes = carregarClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                cliente.setNome(clienteAtualizado.getNome());
                cliente.setCpf(clienteAtualizado.getCpf());
                cliente.setCidade(clienteAtualizado.getCidade());
                salvarClientes(clientes);
                return true;
            }
        }
        return false;
    }

    // Remover um cliente pelo ID e salvar no arquivo
    public boolean removerCliente(int id) {
        List<Cliente> clientes = carregarClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                clientes.remove(cliente);
                salvarClientes(clientes);
                return true;
            }
        }
        return false;
    }

    // Buscar um cliente pelo ID
    public Cliente buscarClientePorId(int id) {
        List<Cliente> clientes = carregarClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return carregarClientes();
    }
}
