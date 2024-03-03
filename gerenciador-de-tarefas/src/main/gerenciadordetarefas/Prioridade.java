package gerenciadordetarefas;

public enum Prioridade {
    BAIXA(0),
    MEDIA(1),
    ALTA(2);

    private int valor;

    Prioridade(int valor) {
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

}
