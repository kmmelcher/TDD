package gerenciadordetarefas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Tarefa tarefaExemplo;

    private Usuario usuarioExemplo;


    private void adicionaTarefaAoUsuarioExemplo(){
        this.usuarioExemplo.adicionaTarefa(
                this.tarefaExemplo.getTitulo(),
                this.tarefaExemplo.getDescricao(),
                this.tarefaExemplo.getVencimento(),
                this.tarefaExemplo.getPrioridade()
        );
    }

    @BeforeEach
    void setUp(){
        this.usuarioExemplo = new Usuario("Kilian", "kilian.melcher@ccc.ufcg.edu.br");
        this.tarefaExemplo = new Tarefa(0, "Tarefa 1", "Essa é a tarefa 1", LocalDate.now(), Prioridade.BAIXA);
    }

    @Test
    void criaUsuarioTest(){
        Usuario usuario = new Usuario("Kilian", "kilian.melcher@ccc.ufcg.edu.br");

        assertEquals("Kilian", usuario.getNome());
        assertEquals("kilian.melcher@ccc.ufcg.edu.br", usuario.getEmail());
        assertEquals(0, usuario.getTarefas().size());
    }

    @Test
    void adicionaTarefaAoUsuarioTest(){
        Usuario usuario = new Usuario("Kilian", "kilian.melcher@ccc.ufcg.edu.br");

        usuario.adicionaTarefa(
                this.tarefaExemplo.getTitulo(),
                this.tarefaExemplo.getDescricao(),
                this.tarefaExemplo.getVencimento(),
                this.tarefaExemplo.getPrioridade()
        );

        assertEquals(1, usuario.getTarefas().size());
    }

    @Test
    void atualizaTarefaDoUsuarioTest(){
        adicionaTarefaAoUsuarioExemplo();

        this.tarefaExemplo.setTitulo("Novo titulo");
        this.usuarioExemplo.atualizaTarefa(this.tarefaExemplo);

        assertEquals("Novo titulo", usuarioExemplo.getTarefa(0).getTitulo());
    }

    @Test
    void excluiTarefaDoUsuarioTest(){
        adicionaTarefaAoUsuarioExemplo();

        this.usuarioExemplo.excluirTarefa(0);

        assertEquals(0, this.usuarioExemplo.getTarefas().size());
        assertNull(this.usuarioExemplo.getTarefa(0));
    }

}
