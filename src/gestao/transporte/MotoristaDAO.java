package gestao.transporte;

import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO {
    private static final String FILE_NAME = "motoristas.txt";

    // Salvar todos os motoristas no arquivo
    public void salvarMotoristas(List<Motorista> motoristas) {
        List<String> lines = new ArrayList<>();
        for (Motorista motorista : motoristas) {
            lines.add(motorista.getId() + ";" + motorista.getNome() + ";" + motorista.getCpf() + ";" +
                      motorista.getCnh() + ";" + motorista.getCidade());
        }
        FileManager.salvar(FILE_NAME, lines);
    }

    // Carregar todos os motoristas do arquivo
    public List<Motorista> carregarMotoristas() {
        List<Motorista> motoristas = new ArrayList<>();
        List<String> lines = FileManager.carregar(FILE_NAME);

        for (String line : lines) {
            try {
                String[] data = line.split(";");
                if (data.length == 5) { // Verifica se a linha tem os 5 campos necessários
                    Motorista motorista = new Motorista(
                        Integer.parseInt(data[0]), // ID
                        data[1],                  // Nome
                        data[2],                  // CPF
                        data[3],                  // CNH
                        data[4]                   // Cidade
                    );
                    motoristas.add(motorista);
                } else {
                    System.out.println("Linha mal formatada ignorada: " + line);
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar a linha: " + line + " - " + e.getMessage());
            }
        }
        return motoristas;
    }

    // Adicionar um novo motorista e salvar no arquivo
    public void adicionarMotorista(Motorista motorista) {
        List<Motorista> motoristas = carregarMotoristas();
        motoristas.add(motorista);
        salvarMotoristas(motoristas);
    }

    // Atualizar um motorista existente no arquivo
    public boolean editarMotorista(int id, Motorista motoristaAtualizado) {
        List<Motorista> motoristas = carregarMotoristas();
        for (Motorista motorista : motoristas) {
            if (motorista.getId() == id) {
                motorista.setNome(motoristaAtualizado.getNome());
                motorista.setCpf(motoristaAtualizado.getCpf());
                motorista.setCnh(motoristaAtualizado.getCnh());
                motorista.setCidade(motoristaAtualizado.getCidade());
                salvarMotoristas(motoristas);
                return true;
            }
        }
        return false;
    }

    // Remover um motorista pelo ID e salvar no arquivo
    public boolean removerMotorista(int id) {
        List<Motorista> motoristas = carregarMotoristas();
        for (Motorista motorista : motoristas) {
            if (motorista.getId() == id) {
                motoristas.remove(motorista);
                salvarMotoristas(motoristas);
                return true;
            }
        }
        return false;
    }

    // Buscar um motorista pelo ID
    public Motorista buscarMotoristaPorId(int id) {
        List<Motorista> motoristas = carregarMotoristas();
        for (Motorista motorista : motoristas) {
            if (motorista.getId() == id) {
                return motorista;
            }
        }
        return null;
    }

    // Listar todos os motoristas
    public List<Motorista> listarMotoristas() {
        return carregarMotoristas();
    }
}
