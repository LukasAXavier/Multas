
import database.BaseDeDados;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import models.Multa;
import models.Ocorrencia;
import utils.ArquivoUtils;

public class Main {

    public static void main(String[] args) {
        System.out.println("Sistema de Multas de Trânsito iniciado.");

        BaseDeDados baseDeDados = new BaseDeDados();
        baseDeDados.inicializaRegras(); // Inicializa regras de exemplo

        // Adicionar ocorrências para teste rápido
        baseDeDados.adicionarOcorrencia(new Ocorrencia(LocalDate.now(), "ABC1234", "Velocidade:80", "Avenida Paulista"));
        baseDeDados.adicionarOcorrencia(new Ocorrencia(LocalDate.now(), "XYZ5678", "Rodízio", "Avenida 23 de Maio"));
        baseDeDados.adicionarOcorrencia(new Ocorrencia(LocalDate.now(), "LMN8910", "Corredor de ônibus", "Avenida Santo Amaro"));

        try (Scanner scanner = new Scanner(System.in)) { // try-with-resources para Scanner
            boolean executando = true;

            while (executando) {
                // Interface de seleção do usuário

                System.out.println("\n=======================================");
                System.out.println("    Sistema de Multas de Trânsito    ");
                System.out.println("=======================================");
                System.out.println("  [1] Registrar nova ocorrência       ");
                System.out.println("  [2] Processar ocorrências           ");
                System.out.println("  [3] Listar multas                   ");
                System.out.println("  [4] Carregar ocorrências de arquivo ");
                System.out.println("  [5] Salvar ocorrências em arquivo   ");
                System.out.println("  [6] Buscar multas por placa         ");
                System.out.println("  [7] Buscar multas por data          ");
                System.out.println("---------------------------------------");
                System.out.println("  [8] Limpar todas as ocorrências     ");
                System.out.println("  [9] Sair                            ");
                System.out.println("=======================================");
                System.out.print("Digite sua opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha

                switch (opcao) {
                    case 1:
                        System.out.print("Digite a data da ocorrência (AAAA-MM-DD): ");
                        String dataStr = scanner.nextLine();
                        LocalDate data = LocalDate.parse(dataStr);

                        System.out.print("Digite a placa do veículo: ");
                        String placa = scanner.nextLine();

                        System.out.print("Digite o tipo de infração: ");
                        String tipoInfracao = scanner.nextLine();

                        System.out.print("Digite o logradouro: ");
                        String logradouro = scanner.nextLine();

                        Ocorrencia ocorrencia = new Ocorrencia(data, placa, tipoInfracao, logradouro);
                        baseDeDados.adicionarOcorrencia(ocorrencia);
                        System.out.println("Ocorrência registrada com sucesso!");
                        break;

                    case 2:
                        baseDeDados.processarOcorrencias();
                        System.out.println("Ocorrências processadas com sucesso!");
                        break;

                    case 3:
                        if (baseDeDados.getMultas().isEmpty()) {
                            System.out.println("Nenhuma multa registrada até o momento.");
                        } else {
                            System.out.println("Multas registradas:");
                            baseDeDados.getMultas().forEach(multa -> {
                                System.out.println("- Descrição: " + multa.getDescricao());
                                System.out.println("  Placa: " + multa.getPlaca());
                                System.out.println("  Valor: R$" + multa.getValor());
                                System.out.println("  Nível: " + multa.getNivel());
                                System.out.println();
                            });
                        }
                        break;

                    case 4: // Carregar ocorrências
                        System.out.print("Digite o caminho do arquivo de ocorrências: ");
                        String caminhoArquivo = scanner.nextLine();
                        List<Ocorrencia> novasOcorrencias = ArquivoUtils.lerOcorrencias(caminhoArquivo);

                        if (novasOcorrencias.isEmpty()) {
                            System.out.println("Nenhuma ocorrência foi carregada.");
                        } else {
                            novasOcorrencias.forEach(baseDeDados::adicionarOcorrencia);
                            System.out.println(novasOcorrencias.size() + " ocorrências carregadas com sucesso!");
                        }
                        break;

                    case 5:
                        System.out.print("Digite o caminho para salvar o arquivo: ");
                        String caminhoSalvar = scanner.nextLine();
                        ArquivoUtils.salvarOcorrencias(caminhoSalvar, baseDeDados.getOcorrenciasProcessadas());
                        break;

                    case 6: // Buscar multas por placa
                        System.out.print("Digite a placa do veículo: ");
                        String placaBusca = scanner.nextLine();
                        List<Multa> multasEncontradas = baseDeDados.buscarMultasPorPlaca(placaBusca);

                        if (multasEncontradas.isEmpty()) {
                            System.out.println("Nenhuma multa encontrada para a placa: " + placaBusca);
                        } else {
                            System.out.println("Multas encontradas para a placa " + placaBusca + ":");
                            multasEncontradas.forEach(multa -> {
                                System.out.println("- Descrição: " + multa.getDescricao());
                                System.out.println("  Valor: R$" + multa.getValor());
                                System.out.println("  Nível: " + multa.getNivel());
                                System.out.println();
                            });
                        }
                        break;

                    case 7: // Buscar multas por data
                        System.out.print("Digite a data (AAAA-MM-DD): ");
                        String dataBusca = scanner.nextLine();
                        List<Multa> multasPorData = baseDeDados.buscarMultasPorData(dataBusca);

                        if (multasPorData.isEmpty()) {
                            System.out.println("Nenhuma multa encontrada para a data: " + dataBusca);
                        } else {
                            System.out.println("Multas encontradas para a data " + dataBusca + ":");
                            multasPorData.forEach(multa -> {
                                System.out.println("- Descrição: " + multa.getDescricao());
                                System.out.println("  Placa: " + multa.getPlaca());
                                System.out.println("  Valor: R$" + multa.getValor());
                                System.out.println("  Nível: " + multa.getNivel());
                                System.out.println("  Data: " + multa.getData());
                                System.out.println();
                            });
                        }
                        break;

                    case 8: // Limpar todas as ocorrências
                        System.out.print("Tem certeza que deseja limpar todas as ocorrências? (S/N): ");
                        String confirmacao = scanner.nextLine().toLowerCase();
                        if (confirmacao.equals("s")) {
                            baseDeDados.limparOcorrencias();
                        } else {
                            System.out.println("Operação de limpeza cancelada.");
                        }
                        break;

                    case 9:
                        executando = false;
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;

                }
            }
        }
    }
}
