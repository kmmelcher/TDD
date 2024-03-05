package main;

import java.math.BigDecimal;

public class Voo {

    private int id;
    private String origem;
    private String destino;
    private String dataHora;
    private BigDecimal preco;
    private Reserva[] assentos;

    public int getId() {
        return id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Reserva[] getAssentos() {
        return assentos;
    }

    public Voo(int id, String origem, String destino, String dataHora, BigDecimal preco, int capacidade) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
        this.preco = preco;
        this.assentos = new Reserva[capacidade];
    }
}
