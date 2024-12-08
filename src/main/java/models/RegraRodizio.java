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
        int diaAtual = java.time.LocalDate.now().getDayOfWeek().getValue(); // Dia atual da semana
        if (diaAtual == diaSemana) {
            for (String logradouro : logradouros) {
                if (ocorrencia.getLogradouro().equals(logradouro) &&
                    ocorrencia.getPlaca().endsWith(String.valueOf(finalPlacaRestrito))) {
                    return nivel;
                }
            }
        }
        return 0; // Sem multa
    }
}
