package gerenciadordetarefas;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gerenciador {

    private ArrayList<Tarefa> tarefas;

    public Gerenciador(){
        this.tarefas = new ArrayList<>();
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    public void adicionaTarefa(String titulo, String descricao, LocalDate vencimento, Prioridade prioridade) {
        Tarefa tarefa = new Tarefa(this.tarefas.size(), titulo, descricao, vencimento, prioridade);

        this.tarefas.add(tarefa);
    }

    public void atualizaTituloTarefa(int id, String titulo) {
        this.tarefas.get(id).setTitulo(titulo);
    }


    public void atualizaDescricaoTarefa(int id, String descricao) {
        this.tarefas.get(id).setDescricao(descricao);
    }

    public void atualizaVencimentoTarefa(int id, LocalDate vencimento) {
        this.tarefas.get(id).setVencimento(vencimento);
    }

    public void atualizaPrioridadeTarefa(int id, Prioridade prioridade) {
        this.tarefas.get(id).setPrioridade(prioridade);
    }
}
