package gerenciadordetarefas;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorTest {

    @Test
    void criaGerenciadorTeste(){
        Gerenciador gerenciador = new Gerenciador();

        assertEquals(gerenciador.getTarefas().size(), 0);
    }

    @Test
    void adicionaTarefaTeste(){
        Gerenciador gerenciador = new Gerenciador();
        Tarefa tarefa = new Tarefa("Tarefa 1", "Essa é a tarefa 1", LocalDate.now(), Prioridade.BAIXA);

        gerenciador.adicionaTarefa(tarefa);

        assertEquals(gerenciador.getTarefas().size(), 1);
        assertEquals(gerenciador.getTarefas().getFirst(), tarefa);
    }

}
