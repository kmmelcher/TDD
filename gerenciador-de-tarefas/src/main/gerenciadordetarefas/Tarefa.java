package gerenciadordetarefas;

import java.time.LocalDate;

public class Tarefa {

    private int id;
    private String titulo;
    private String descricao;
    private LocalDate vencimento;
    private Prioridade prioridade;

    public Tarefa(int id, String titulo, String descricao, LocalDate vencimento, Prioridade prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.vencimento = vencimento;
        this.prioridade = prioridade;
    }

    public int getId(){
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public LocalDate getVencimento() {
        return this.vencimento;
    }

    public Prioridade getPrioridade() {
        return this.prioridade;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

}
