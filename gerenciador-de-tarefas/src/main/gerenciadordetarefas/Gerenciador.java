package gerenciadordetarefas;

import java.util.ArrayList;

public class Gerenciador {

    private ArrayList<Tarefa> tarefas;

    public Gerenciador(){
        this.tarefas = new ArrayList<>();
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    public void adicionaTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }
}
