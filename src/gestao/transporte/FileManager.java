package gestao.transporte;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // Escrever lista de strings em um arquivo
    public static void salvar(String fileName, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo " + fileName + ": " + e.getMessage());
        }
    }

    // Ler lista de strings de um arquivo
    public static List<String> carregar(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo " + fileName + ": " + e.getMessage());
        }
        return lines;
    }
}
