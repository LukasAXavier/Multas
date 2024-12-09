package models;

public class RegraRodizio extends RegraMulta {
    private int diaSemana; // 1 = Segunda, ..., 7 = Domingo
    private String[] logradouros;
    private int finalPlacaRestrito;

    public RegraRodizio(String descricao, int nivel, double valor, int diaSemana, String[] logradouros, int finalPlacaRestrito) {
        super(descricao, nivel, valor);
        this.diaSemana = diaSemana;
        this.logradouros = logradouros;
        this.finalPlacaRestrito = finalPlacaRestrito;
    }

    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        // Dia da semana baseado na data da ocorrência
        int diaOcorrencia = ocorrencia.getData().getDayOfWeek().getValue();
        if (diaOcorrencia == diaSemana) { // Verifica se é o dia de rodízio
            for (String logradouro : logradouros) {
                if (ocorrencia.getLogradouro().equals(logradouro) &&
                    ocorrencia.getPlaca().endsWith(String.valueOf(finalPlacaRestrito))) {
                    return nivel; // Retorna o nível da multa
                }
            }
        }
        return 0; // Sem multa
    }
}
