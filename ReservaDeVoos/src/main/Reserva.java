package main;

public class Reserva {

    private String nome;
    private int numeroDePessoas;
    private String contato;

    private int assentoInicial;

    private int codigo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroDePessoas() {
        return numeroDePessoas;
    }

    public void setNumeroDePessoas(int numeroDePessoas) {
        this.numeroDePessoas = numeroDePessoas;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public int getAssentoInicial() {
        return assentoInicial;
    }

    public void setAssentoInicial(int assentoInicial) {
        this.assentoInicial = assentoInicial;
    }

    public Reserva(String nome, int numeroDePessoas, String contato, int assentoInicial, int codigo) {
        this.nome = nome;
        this.numeroDePessoas = numeroDePessoas;
        this.contato = contato;
        this.assentoInicial = assentoInicial;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
