package gerenciadordetarefas;

import java.util.Comparator;

public class TarefaComparator implements Comparator<Tarefa> {
    @Override
    public int compare(Tarefa t1, Tarefa t2) {
        if (t1.getPrioridade().getValor() > t2.getPrioridade().getValor()){
            return -1;
        }

        if (t1.getPrioridade().getValor() < t2.getPrioridade().getValor()){
            return 1;
        }

        return t1.getVencimento().compareTo(t2.getVencimento());
    }
}
