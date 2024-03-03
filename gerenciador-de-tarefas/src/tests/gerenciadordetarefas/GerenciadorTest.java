package gerenciadordetarefas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorTest {

    private Gerenciador gerenciador;

    @BeforeEach
    void setUp(){
        this.gerenciador = new Gerenciador();

    }

    void adicionaTarefaExemplo() {
        this.gerenciador.adicionaTarefa("Tarefa 1", "Essa é a tarefa 1", LocalDate.now(), Prioridade.BAIXA);
    }

    @Test
    void criaGerenciadorTeste(){
        assertEquals(0, this.gerenciador.getTarefas().size());
    }

    @Test
    void adicionaTarefaTeste(){
        this.gerenciador.adicionaTarefa("Tarefa 1",
                "Essa é a tarefa 1",
                LocalDate.now(),
                Prioridade.BAIXA
        );

        assertEquals(this.gerenciador.getTarefas().size(), 1);

        Tarefa primeiraTarefa = this.gerenciador.getTarefas().getFirst();
        assertEquals(0, primeiraTarefa.getId());
        assertEquals("Tarefa 1", primeiraTarefa.getTitulo());
        assertEquals("Essa é a tarefa 1", primeiraTarefa.getDescricao());
        assertEquals( LocalDate.now(), primeiraTarefa.getVencimento());
        assertEquals(Prioridade.BAIXA, primeiraTarefa.getPrioridade());
    }

    @Test
    void atualizaTituloTarefaTeste(){
        adicionaTarefaExemplo();

        gerenciador.atualizaTituloTarefa(0, "Tarefa 2");

        assertEquals("Tarefa 2", gerenciador.getTarefas().getFirst().getTitulo());
    }

    @Test
    void atualizaDescricaoTarefaTeste(){
        adicionaTarefaExemplo();

        gerenciador.atualizaDescricaoTarefa(0, "Essa é a tarefa 2");

        assertEquals("Essa é a tarefa 2", gerenciador.getTarefas().getFirst().getDescricao());
    }

    @Test
    void atualizaVencimentoTarefaTeste(){
        adicionaTarefaExemplo();

        LocalDate amanha =  LocalDate.now().plusDays(1);
        gerenciador.atualizaVencimentoTarefa(0, amanha);

        assertEquals(amanha, gerenciador.getTarefas().getFirst().getVencimento());
    }

    @Test
    void atualizaPrioridadeTarefaTeste(){
        adicionaTarefaExemplo();

        gerenciador.atualizaPrioridadeTarefa(0, Prioridade.MEDIA);

        assertEquals(Prioridade.MEDIA, gerenciador.getTarefas().getFirst().getPrioridade());
    }

}
