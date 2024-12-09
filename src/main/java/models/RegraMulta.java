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

    // Retorna uma multa com base na ocorrência, se aplicável
    public Multa calcularMulta(Ocorrencia ocorrencia) {
        int nivelMulta = verificaNivelMulta(ocorrencia);
        if (nivelMulta > 0) { // Se houver multa
            return new Multa(descricao, valor, nivelMulta, ocorrencia.getPlaca(), ocorrencia.getData());
        }
        return null; // Sem multa
    }
}
