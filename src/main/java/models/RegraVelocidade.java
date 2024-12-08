package models;

public class RegraVelocidade extends RegraMulta {
    private int velocidadeMaxima;
    private String logradouro;

    public RegraVelocidade(String descricao, int nivel, double valor, int velocidadeMaxima, String logradouro) {
        super(descricao, nivel, valor);
        this.velocidadeMaxima = velocidadeMaxima;
        this.logradouro = logradouro;
    }

    @Override
    public int verificaNivelMulta(Ocorrencia ocorrencia) {
        // Supondo que o tipo de infração inclua a velocidade atual em um formato como "Velocidade:80"
        if (ocorrencia.getLogradouro().equals(logradouro) && ocorrencia.getTipoInfracao().startsWith("Velocidade:")) {
            int velocidadeAtual = Integer.parseInt(ocorrencia.getTipoInfracao().split(":")[1]);
            if (velocidadeAtual > velocidadeMaxima) {
                return nivel; // Nível da multa configurado
            }
        }
        return 0; // Sem multa
    }
}
