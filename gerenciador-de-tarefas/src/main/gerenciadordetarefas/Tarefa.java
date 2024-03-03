package gerenciadordetarefas;

import java.time.LocalDate;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate vencimento;
    private Prioridade prioridade;

    public Tarefa(String titulo, String descricao, LocalDate vencimento, Prioridade prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.vencimento = vencimento;
        this.prioridade = prioridade;
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
}
