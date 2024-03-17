package gerenciadordetarefas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionalTest {

    private Usuario usuarioExemplo;

    private void adicionaTarefaAoUsuarioExemplo(String titulo, LocalDate vencimento){
        this.usuarioExemplo.adicionaTarefa(
                titulo,
                "Essa é a tarefa " + titulo,
                vencimento,
                Prioridade.BAIXA
        );
    }

    private void adicionaTarefaAoUsuarioExemplo(String titulo, Prioridade prioridade){
        this.usuarioExemplo.adicionaTarefa(
                titulo,
                "Essa é a tarefa " + titulo,
                LocalDate.now(),
                prioridade
        );
    }

    private void adicionaTarefaAoUsuarioExemplo(String titulo){
        this.usuarioExemplo.adicionaTarefa(
                titulo,
                "Essa é a tarefa " + titulo,
                LocalDate.now(),
                Prioridade.BAIXA
        );
    }

    private void atualizaTarefaAoUsuarioExemplo(int tarefaId, String titulo){
        Tarefa tarefaAtualizada = new Tarefa(
                tarefaId,
                titulo,
                "Essa é a tarefa " + titulo,
                LocalDate.now(),
                Prioridade.BAIXA
        );

        this.usuarioExemplo.atualizaTarefa(tarefaAtualizada);
    }

    private String[] getDatasDeVencimento() {
        ArrayList<Tarefa> tarefas = this.usuarioExemplo.exibeTarefas();
        String[] vencimentos = new String[tarefas.size()];

        for (int i = 0; i < tarefas.size(); i++) {
            vencimentos[i] = tarefas.get(i).getVencimento().toString();
        }

        return vencimentos;
    }

    private Prioridade[] getPrioridades() {
        ArrayList<Tarefa> tarefas = this.usuarioExemplo.exibeTarefas();
        Prioridade[] prioridades = new Prioridade[tarefas.size()];

        for (int i = 0; i < tarefas.size(); i++) {
            prioridades[i] = tarefas.get(i).getPrioridade();
        }

        return prioridades;
    }

    @BeforeEach
    void setUp(){
        this.usuarioExemplo = new Usuario("Kilian", "kilian.melcher@ccc.ufcg.edu.br");
    }

    /* Ordenação de tarefas por data de vencimento */

    @Test
    void ordenaTarefasPorDataDeVencimentoTeste1(){
        assertEquals(0, this.usuarioExemplo.exibeTarefas().size());
    }

    @Test
    void ordenaTarefasPorDataDeVencimentoTeste2(){
        adicionaTarefaAoUsuarioExemplo("Tarefa 1", LocalDate.of(2024, 3, 16));

        String[] vencimentos = getDatasDeVencimento();
        assertEquals(1, vencimentos.length);
        assertArrayEquals(new String[]{"2024-03-16"}, vencimentos);
    }

    @Test
    void ordenaTarefasPorDataDeVencimentoTeste3(){
        adicionaTarefaAoUsuarioExemplo("Tarefa 1", LocalDate.of(2024, 3, 17));
        adicionaTarefaAoUsuarioExemplo("Tarefa 2", LocalDate.of(2024, 3, 16));

        String[] vencimentos = getDatasDeVencimento();
        assertEquals(2, vencimentos.length);
        assertArrayEquals(new String[]{"2024-03-16", "2024-03-17"}, vencimentos);
    }

    @Test
    void ordenaTarefasPorDataDeVencimentoTeste4(){
        adicionaTarefaAoUsuarioExemplo("Tarefa 1", LocalDate.of(2024, 3, 17));
        adicionaTarefaAoUsuarioExemplo("Tarefa 2", LocalDate.of(2024, 3, 16));
        adicionaTarefaAoUsuarioExemplo("Tarefa 3", LocalDate.of(2024, 3, 1));
        adicionaTarefaAoUsuarioExemplo("Tarefa 4", LocalDate.of(2026, 3, 12));
        adicionaTarefaAoUsuarioExemplo("Tarefa 5", LocalDate.of(2020, 3, 2));

        String[] vencimentos = getDatasDeVencimento();
        assertEquals(5, vencimentos.length);
        assertArrayEquals(new String[]{"2020-03-02", "2024-03-01","2024-03-16", "2024-03-17", "2026-03-12"}, vencimentos);
    }

    /* Ordenação de tarefas por prioridade */

    @Test
    void ordenaTarefasPorPrioridadeTeste1(){
        assertEquals(0, this.usuarioExemplo.exibeTarefas().size());
    }

    @Test
    void ordenaTarefasPorPrioridadeTeste2(){
        adicionaTarefaAoUsuarioExemplo("Tarefa 1", Prioridade.BAIXA);

        Prioridade[] prioridades = getPrioridades();
        assertEquals(1, prioridades.length);
        assertArrayEquals(new Prioridade[]{Prioridade.BAIXA}, prioridades);
    }

    @Test
    void ordenaTarefasPorPrioridadeTeste3(){
        adicionaTarefaAoUsuarioExemplo("Tarefa 1", Prioridade.BAIXA);
        adicionaTarefaAoUsuarioExemplo("Tarefa 2", Prioridade.MEDIA);
        adicionaTarefaAoUsuarioExemplo("Tarefa 3", Prioridade.ALTA);

        Prioridade[] prioridades = getPrioridades();
        assertEquals(3, prioridades.length);
        assertArrayEquals(new Prioridade[]{Prioridade.ALTA, Prioridade.MEDIA, Prioridade.BAIXA}, prioridades);
    }

    /* Exibição de tarefas */

    @Test
    void exibeTarefaTeste1(){
        adicionaTarefaAoUsuarioExemplo("Tarefa 1");
        atualizaTarefaAoUsuarioExemplo(0, "Tarefa 1 - Atualizada");

        assertEquals("Tarefa 1 - Atualizada", this.usuarioExemplo.exibeTarefas().getFirst().getTitulo());
    }

    @Test
    void exibeTarefaTeste2(){
        // Teste falhou!
        assertThrows(Exception.class, () -> atualizaTarefaAoUsuarioExemplo(0, "Tarefa 1 - Atualizada"));
    }

    @Test
    void exibeTarefaTeste3(){
        // Teste falhou!
        assertThrows(Exception.class, () -> this.usuarioExemplo.excluirTarefa(0));
    }

    @Test
    void exibeTarefaTeste4(){
        adicionaTarefaAoUsuarioExemplo("Tarefa 1");

        assertEquals("Tarefa 1", this.usuarioExemplo.exibeTarefas().getFirst().getTitulo());
    }

    @Test
    void exibeTarefaTeste5(){
        adicionaTarefaAoUsuarioExemplo("Tarefa 1");
        this.usuarioExemplo.excluirTarefa(0);

        assertEquals(0, this.usuarioExemplo.exibeTarefas().size());
    }

}
