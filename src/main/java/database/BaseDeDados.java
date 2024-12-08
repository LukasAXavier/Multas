package database;

import java.util.ArrayList;
import java.util.List;
import models.Multa;
import models.Ocorrencia;
import models.RegraCorredorOnibus;
import models.RegraMulta;
import models.RegraRodizio;
import models.RegraVelocidade;

public class BaseDeDados {
    private List<Ocorrencia> ocorrenciasNaoProcessadas;
    private List<Ocorrencia> ocorrenciasProcessadas;
    private List<Multa> multas;
    private List<RegraMulta> regras;

    public BaseDeDados() {
        this.ocorrenciasNaoProcessadas = new ArrayList<>();
        this.ocorrenciasProcessadas = new ArrayList<>();
        this.multas = new ArrayList<>();
        this.regras = new ArrayList<>();
    }

    // Adicionar uma ocorrência à lista de não processadas
    public void adicionarOcorrencia(Ocorrencia ocorrencia) {
        ocorrenciasNaoProcessadas.add(ocorrencia);
    }

    // Processar todas as ocorrências não processadas
    public void processarOcorrencias() {
        for (Ocorrencia ocorrencia : ocorrenciasNaoProcessadas) {
            for (RegraMulta regra : regras) {
                Multa multa = regra.calcularMulta(ocorrencia);
                if (multa != null) {
                    multas.add(multa);
                }
            }
            ocorrenciasProcessadas.add(ocorrencia);
        }
        ocorrenciasNaoProcessadas.clear();
    }

    // Buscar multas por data
    public List<Multa> buscarMultasPorData(String data) {
        List<Multa> resultado = new ArrayList<>();
        for (Multa multa : multas) {
            if (multa.getDescricao().contains(data)) {
                resultado.add(multa);
            }
        }
        return resultado;
    }

    // Buscar multas por placa
    public List<Multa> buscarMultasPorPlaca(String placa) {
        List<Multa> resultado = new ArrayList<>();
        for (Multa multa : multas) {
            if (multa.getPlaca().equalsIgnoreCase(placa)) {
                resultado.add(multa);
            }
        }
        return resultado;
    }

    // Inicializar regras programaticamente
    public void inicializaRegras() {
        // Regras de velocidade
        regras.add(new RegraVelocidade(
            "Excesso de Velocidade na Avenida Paulista", 2, 130.50, 60, "Avenida Paulista"));
        regras.add(new RegraVelocidade(
            "Excesso de Velocidade na Avenida Rebouças", 2, 150.00, 70, "Avenida Rebouças"));
        regras.add(new RegraVelocidade(
            "Excesso de Velocidade na Avenida Nações Unidas", 3, 200.00, 50, "Avenida Nações Unidas"));
        regras.add(new RegraVelocidade(
            "Excesso de Velocidade na Rua Augusta", 2, 120.00, 40, "Rua Augusta"));
        regras.add(new RegraVelocidade(
            "Excesso de Velocidade na Avenida Ibirapuera", 2, 110.00, 60, "Avenida Ibirapuera"));
    
        // Regras de rodízio
        regras.add(new RegraRodizio(
            "Rodízio na Avenida 23 de Maio", 3, 200.00, 1, new String[]{"Avenida 23 de Maio"}, 1));
        regras.add(new RegraRodizio(
            "Rodízio na Avenida Bandeirantes", 3, 180.00, 2, new String[]{"Avenida Bandeirantes"}, 2));
        regras.add(new RegraRodizio(
            "Rodízio na Avenida Consolação", 2, 150.00, 3, new String[]{"Avenida Consolação"}, 3));
        regras.add(new RegraRodizio(
            "Rodízio na Avenida Paulista", 2, 150.00, 4, new String[]{"Avenida Paulista"}, 4));
        regras.add(new RegraRodizio(
            "Rodízio na Avenida Ibirapuera", 2, 150.00, 5, new String[]{"Avenida Ibirapuera"}, 5));
    
        // Regras de corredor de ônibus
        regras.add(new RegraCorredorOnibus(
            "Trânsito ilegal no corredor de ônibus da Avenida Santo Amaro", 3, 250.00, 6, 23, "Avenida Santo Amaro"));
        regras.add(new RegraCorredorOnibus(
            "Trânsito ilegal no corredor de ônibus da Avenida Vereador José Diniz", 3, 200.00, 7, 22, "Avenida Vereador José Diniz"));
        regras.add(new RegraCorredorOnibus(
            "Trânsito ilegal no corredor de ônibus da Avenida Brigadeiro Luís Antônio", 2, 180.00, 6, 20, "Avenida Brigadeiro Luís Antônio"));
        regras.add(new RegraCorredorOnibus(
            "Trânsito ilegal no corredor de ônibus da Avenida Rebouças", 3, 220.00, 6, 21, "Avenida Rebouças"));
        regras.add(new RegraCorredorOnibus(
            "Trânsito ilegal no corredor de ônibus da Rua da Consolação", 2, 170.00, 7, 22, "Rua da Consolação"));
    
        // Mais regras diversas para completar
        regras.add(new RegraVelocidade(
            "Excesso de Velocidade na Rua Haddock Lobo", 2, 140.00, 40, "Rua Haddock Lobo"));
        regras.add(new RegraRodizio(
            "Rodízio na Avenida Brasil", 3, 190.00, 6, new String[]{"Avenida Brasil"}, 6));
        regras.add(new RegraCorredorOnibus(
            "Trânsito ilegal no corredor de ônibus da Avenida Faria Lima", 3, 240.00, 7, 21, "Avenida Faria Lima"));
    }
    


    // Getter para a lista de ocorrências processadas
    public List<Ocorrencia> getOcorrenciasProcessadas() {
        return ocorrenciasProcessadas;
    }

    // Getter para a lista de multas
    public List<Multa> getMultas() {
        return multas;
    }
}
