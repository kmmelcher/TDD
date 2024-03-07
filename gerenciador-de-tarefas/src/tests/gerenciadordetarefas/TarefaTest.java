package gerenciadordetarefas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;

public class TarefaTest {

    @Test
    void criaTarefaTeste(){
        Tarefa tarefa = new Tarefa(
                0,
                "Tarefa 1",
                "Essa é a tarefa 1",
                LocalDate.now(),
                Prioridade.BAIXA
        );

        assertEquals(0, tarefa.getId());
        assertEquals("Tarefa 1", tarefa.getTitulo());
        assertEquals("Essa é a tarefa 1", tarefa.getDescricao());
        assertEquals(LocalDate.now(), tarefa.getVencimento());
        assertEquals(Prioridade.BAIXA, tarefa.getPrioridade());
    }

}
