package models;

public class Multa {
    private String descricao;
    private double valor;
    private int nivel;
    private String placa;

    public Multa(String descricao, double valor, int nivel, String placa) {
        this.descricao = descricao;
        this.valor = valor;
        this.nivel = nivel;
        this.placa = placa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
