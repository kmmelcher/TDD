package gerenciadordetarefas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;

public class TarefaTest {

    @Test
    void criaTarefaTeste(){
        Tarefa tarefa = new Tarefa("Tarefa 1", "Essa é a tarefa 1", LocalDate.now(), Prioridade.BAIXA);

        assertEquals(tarefa.getTitulo(), "Tarefa 1");
        assertEquals(tarefa.getDescricao(), "Essa é a tarefa 1");
        assertEquals(tarefa.getVencimento(), LocalDate.now());
        assertEquals(tarefa.getPrioridade(), Prioridade.BAIXA);
    }

}
