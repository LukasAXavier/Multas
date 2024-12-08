package models;

public class RegraCorredorOnibus extends RegraMulta {
    private int horaInicio;
    private int horaFim;
    private String logradouro;

    public RegraCorredorOnibus(String descricao, int nivel, double valor, int horaInicio, int horaFim, String logradouro) {
        super(descricao, nivel, valor);
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.logradouro = logradouro;
    }

    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        int horaAtual = java.time.LocalTime.now().getHour(); // Hora atual
        if (horaAtual >= horaInicio && horaAtual <= horaFim && ocorrencia.getLogradouro().equals(logradouro)) {
            return nivel;
        }
        return 0; // Sem multa
    }
}
