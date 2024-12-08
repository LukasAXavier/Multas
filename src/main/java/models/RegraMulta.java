package models;

public abstract class RegraMulta {
    protected String descricao; // Descrição da regra
    protected int nivel;        // Nível da multa (0 - Sem multa, 1 - Leve, 2 - Média, 3 - Grave)
    protected double valor;     // Valor da multa

    public RegraMulta(String descricao, int nivel, double valor) {
        this.descricao = descricao;
        this.nivel = nivel;
        this.valor = valor;
    }

    // Método abstrato para verificar o nível da multa
    public abstract int verificaNivelMulta(Ocorrencia ocorrencia);

    // Retorna a descrição da multa
    public String getDescricao() {
        return descricao;
    }

    // Retorna o valor da multa
    public double getValor() {
        return valor;
    }

    // Cria uma multa baseada na ocorrência e na regra
    public Multa calcularMulta(Ocorrencia ocorrencia) {
        int nivelMulta = verificaNivelMulta(ocorrencia);
        if (nivelMulta > 0) {
            return new Multa(descricao, valor, nivelMulta, ocorrencia.getPlaca());
        }
        return null; // Sem multa
    }
}

