package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Ocorrencia;

public class ArquivoUtils {

    public static List<Ocorrencia> lerOcorrencias(String caminhoArquivo) {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean isFirstLine = true; // Variável para verificar a primeira linha
    
            while ((linha = br.readLine()) != null) {
                // Ignorar a linha de cabeçalho
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
    
                // Processar a linha como uma ocorrência
                String[] partes = linha.split(","); // Divide a linha pelo separador ","
                LocalDate data = LocalDate.parse(partes[0]);
                String placa = partes[1];
                String tipoInfracao = partes[2];
                String logradouro = partes[3];
    
                Ocorrencia ocorrencia = new Ocorrencia(data, placa, tipoInfracao, logradouro);
                ocorrencias.add(ocorrencia);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    
        return ocorrencias;
    }
    

    public static void salvarOcorrencias(String caminhoArquivo, List<Ocorrencia> ocorrencias) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Escrever o cabeçalho no arquivo
            bw.write("DATA,PLACA,TIPO_DE_INFRAÇÃO,LOGRADOURO");
            bw.newLine();

            // Escrever cada ocorrência
            for (Ocorrencia ocorrencia : ocorrencias) {
                String linha = ocorrencia.getData() + ","
                        + ocorrencia.getPlaca() + ","
                        + ocorrencia.getTipoInfracao() + ","
                        + ocorrencia.getLogradouro();
                bw.write(linha);
                bw.newLine();
            }

            System.out.println("Ocorrências salvas com sucesso no arquivo: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

}
