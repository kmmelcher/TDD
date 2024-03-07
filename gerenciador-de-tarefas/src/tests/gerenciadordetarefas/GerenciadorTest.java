package gerenciadordetarefas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorTest {

    private Gerenciador gerenciador;

    private Tarefa tarefaExemplo;

    @BeforeEach
    void setUp(){
        this.gerenciador = new Gerenciador();
        this.tarefaExemplo = new Tarefa(0, "Tarefa 1", "Essa é a tarefa 1", LocalDate.now(), Prioridade.BAIXA);
        this.gerenciador.adicionaTarefa(
                tarefaExemplo.getTitulo(),
                tarefaExemplo.getDescricao(),
                tarefaExemplo.getVencimento(),
                tarefaExemplo.getPrioridade()
        );
    }

    @Test
    void criaGerenciadorTeste(){
        Gerenciador gerenciadorVazio = new Gerenciador();

        assertEquals(0, gerenciadorVazio.size());
    }

    @Test
    void adicionaTarefaTeste(){

        assertEquals(this.gerenciador.size(), 1);
        Tarefa primeiraTarefa = this.gerenciador.getTarefa(0);
        assertEquals(0, primeiraTarefa.getId());
        assertEquals("Tarefa 1", primeiraTarefa.getTitulo());
        assertEquals("Essa é a tarefa 1", primeiraTarefa.getDescricao());
        assertEquals( LocalDate.now(), primeiraTarefa.getVencimento());
        assertEquals(Prioridade.BAIXA, primeiraTarefa.getPrioridade());
    }

    @Test
    void atualizaTituloTarefaTeste(){
        Tarefa tarefaAtualizada = tarefaExemplo;
        tarefaAtualizada.setTitulo("Tarefa 2");
        gerenciador.atualizaTarefa(tarefaAtualizada);

        assertEquals("Tarefa 2", gerenciador.getTarefa(0).getTitulo());
    }

    @Test
    void atualizaDescricaoTarefaTeste(){
        Tarefa tarefaAtualizada = tarefaExemplo;
        tarefaAtualizada.setDescricao("Essa é a tarefa 2");
        gerenciador.atualizaTarefa(tarefaAtualizada);

        assertEquals("Essa é a tarefa 2", gerenciador.getTarefa(0).getDescricao());
    }

    @Test
    void atualizaVencimentoTarefaTeste() {
        Tarefa tarefaAtualizada = tarefaExemplo;
        LocalDate amanha =  LocalDate.now().plusDays(1);
        tarefaAtualizada.setVencimento(amanha);
        gerenciador.atualizaTarefa(tarefaAtualizada);

        assertEquals(amanha, gerenciador.getTarefa(0).getVencimento());
    }

    @Test
    void atualizaPrioridadeTarefaTeste() {
        Tarefa tarefaAtualizada = tarefaExemplo;
        tarefaAtualizada.setPrioridade(Prioridade.MEDIA);
        gerenciador.atualizaTarefa(tarefaAtualizada);

        assertEquals(Prioridade.MEDIA, gerenciador.getTarefa(0).getPrioridade());
    }

    @Test
    void excluiTarefaTeste(){
        assertEquals(1, gerenciador.size());

        gerenciador.excluirTarefa(0);

        assertEquals(0, gerenciador.size());
    }

    @Test
    void excluiPrimeiraTarefaTeste(){
        Tarefa segundaTarefa = this.tarefaExemplo;
        segundaTarefa.setTitulo("Tarefa 2");
        this.gerenciador.adicionaTarefa(
                segundaTarefa.getTitulo(),
                segundaTarefa.getDescricao(),
                segundaTarefa.getVencimento(),
                segundaTarefa.getPrioridade()
        );

        assertEquals(2, gerenciador.size());

        gerenciador.excluirTarefa(0);

        assertEquals(1, gerenciador.size());
        assertNull(this.gerenciador.getTarefa(0));
    }

}
