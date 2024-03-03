package gerenciadordetarefas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Gerenciador {

    private HashMap<Integer, Tarefa> tarefas;

    private int proximoId;

    public Gerenciador(){
        this.tarefas = new HashMap<>();
        this.proximoId = 0;
    }

    public void adicionaTarefa(String titulo, String descricao, LocalDate vencimento, Prioridade prioridade) {
        Tarefa tarefa = new Tarefa(proximoId, titulo, descricao, vencimento, prioridade);

        this.tarefas.put(tarefa.getId(), tarefa);
        proximoId++;
    }

    public Tarefa getTarefa(int id) {
        return this.tarefas.get(id);
    }

    public Collection<Tarefa> getTarefas(){
        return this.tarefas.values();
    }

    public void atualizaTarefa(Tarefa tarefa)  {
        this.tarefas.put(tarefa.getId(), tarefa);
    }

    public void excluirTarefa(int id) {
        this.tarefas.remove(id);
    }

    public int size(){
        return this.tarefas.size();
    }

    public ArrayList<Tarefa> getTarefasOrdenadas() {
        ArrayList<Tarefa> tarefas = new ArrayList<>(this.getTarefas());
        tarefas.sort(new TarefaComparator());
        return tarefas;
    }
}
