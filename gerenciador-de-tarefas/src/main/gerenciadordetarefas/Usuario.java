package gerenciadordetarefas;

import java.time.LocalDate;
import java.util.Collection;

public class Usuario {

    private String nome;

    private String email;

    private Gerenciador gerenciador;

    public Usuario(String nome, String email){
        this.nome = nome;
        this.email = email;
        this.gerenciador = new Gerenciador();
    }

    public String getNome(){
        return  nome;
    }

    public String getEmail(){
        return email;
    }

    public void adicionaTarefa(String titulo, String descricao, LocalDate vencimento, Prioridade prioridade) {
        this.gerenciador.adicionaTarefa(titulo, descricao, vencimento, prioridade);
    }

    public Collection<Tarefa> getTarefas() {
        return this.gerenciador.getTarefas();
    }

    public void atualizaTarefa(Tarefa tarefa) {
        this.gerenciador.atualizaTarefa(tarefa);
    }


    public Tarefa getTarefa(int tarefaId) {
        return this.gerenciador.getTarefa(tarefaId);
    }
}
