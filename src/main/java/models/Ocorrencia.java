package models;

import java.time.LocalDate;

public class Ocorrencia {
    private LocalDate data;
    private String placa;
    private String tipoInfracao;
    private String logradouro;

    public Ocorrencia(LocalDate data, String placa, String tipoInfracao, String logradouro) {
        this.data = data;
        this.placa = placa;
        this.tipoInfracao = tipoInfracao;
        this.logradouro = logradouro;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoInfracao() {
        return tipoInfracao;
    }

    public void setTipoInfracao(String tipoInfracao) {
        this.tipoInfracao = tipoInfracao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
}
