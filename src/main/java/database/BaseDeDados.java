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
    regras.add(new RegraVelocidade(
        "Excesso de Velocidade na Avenida Paulista", 2, 130.50, 60, "Avenida Paulista"
    ));
    regras.add(new RegraRodizio(
        "Rodízio na Avenida 23 de Maio", 3, 200.00, 1, new String[]{"Avenida 23 de Maio"}, 1
    ));
    regras.add(new RegraCorredorOnibus(
        "Trânsito ilegal no corredor de ônibus da Avenida Santo Amaro", 3, 250.00, 6, 23, "Avenida Santo Amaro"
    ));
    // Adicione mais regras aqui
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
