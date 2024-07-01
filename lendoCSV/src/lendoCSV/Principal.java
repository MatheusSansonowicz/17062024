package lendoCSV;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Principal {
    public static void main(String args[]) {
        ArrayList<String> cidades = new ArrayList<>();
        ArrayList<String[]> conexoes = new ArrayList<>();
        
        try {
            File file = new File("cidades.csv");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] partes = line.split("@");
                if (!cidades.contains(partes[0])) {
                    cidades.add(partes[0]);
                }
                if (!cidades.contains(partes[1])) {
                    cidades.add(partes[1]);
                }
                conexoes.add(partes);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.printStackTrace();
            return;
        }

        cidades.sort(null);
        
        Grafo grafo_rs = new Grafo(cidades);
        for (String[] conexao : conexoes) {
            grafo_rs.inserirArestaSimetrica(conexao[0], conexao[1]);
        }

        grafo_rs.show();

        String cidade = "Itaara";
        System.out.println("Grau da cidade de " + cidade + ": " + grafo_rs.mostrarGrau(cidade));
    }
}
