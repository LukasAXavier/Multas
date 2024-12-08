import database.BaseDeDados;
import java.time.LocalDate;
import java.util.Scanner;
import models.Ocorrencia;

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
                System.out.println("\nSelecione uma opção:");
                System.out.println("1. Registrar nova ocorrência");
                System.out.println("2. Processar ocorrências");
                System.out.println("3. Listar multas");
                System.out.println("4. Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha

                switch (opcao) {
                    case 1:
                        System.out.print("Digite a data da ocorrência (AAAA-MM-DD): ");
                        String dataStr = scanner.nextLine();
                        LocalDate data = LocalDate.parse(dataStr);

                        System.out.print("Digite a placa do veículo: ");
                        String placa = scanner.nextLine();

                        System.out.print("Digite o tipo de infração (e.g., Velocidade:80): ");
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

                    case 4:
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
