package models;

import java.time.LocalDate;

public class Multa {

    private String descricao;
    private double valor;
    private int nivel;
    private String placa;
    private LocalDate data; // Novo campo para armazenar a data

    // Construtor atualizado
    public Multa(String descricao, double valor, int nivel, String placa, LocalDate data) {
        this.descricao = descricao;
        this.valor = valor;
        this.nivel = nivel;
        this.placa = placa;
        this.data = data; // Inicializa o campo data
    }

    // Getters e Setters
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    // Sobrescrevendo toString para exibir detalhes da multa
    @Override
    public String toString() {
        return "Multa Detalhes: \n" +
               "  - Descrição: " + descricao + "\n" +
               "  - Valor: R$" + valor + "\n" +
               "  - Nível: " + nivel + "\n" +
               "  - Placa: " + placa + "\n" +
               "  - Data: " + data;
    }
}
