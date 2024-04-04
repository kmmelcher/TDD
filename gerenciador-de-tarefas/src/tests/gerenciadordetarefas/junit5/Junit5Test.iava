package junit5tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


import static org.junit.jupiter.api.Assertions.*;

public class Junit5Test {

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

  private void adicionaTarefaAoUsuarioExemplo(String titulo, LocalDate vencimento){
      this.usuarioExemplo.adicionaTarefa(
              titulo,
              this.tarefaExemplo.getDescricao(),
              vencimento,
              this.tarefaExemplo.getPrioridade()
      );
  }

  private void adicionaTarefaAoUsuarioExemplo(String titulo, Prioridade prioridade){
      this.usuarioExemplo.adicionaTarefa(
              titulo,
              this.tarefaExemplo.getDescricao(),
              this.tarefaExemplo.getVencimento(),
              prioridade
      );
  }

  private LocalDate gerarDataAleatoria() {
    Random random = new Random();
    int minDay = (int) LocalDate.of(2024, 1, 1).toEpochDay();
    int maxDay = (int) LocalDate.of(2024, 12, 31).toEpochDay();
    long randomDay = minDay + random.nextInt(maxDay - minDay);
    return LocalDate.ofEpochDay(randomDay);
  }

  @BeforeEach
  void setUp(){
      this.usuarioExemplo = new Usuario("Kilian", "kilian.melcher@ccc.ufcg.edu.br");
      this.tarefaExemplo = new Tarefa(0, "Tarefa 1", "Essa é a tarefa 1", LocalDate.now(), Prioridade.BAIXA);
  }

  @Test
  @DisplayName("Teste para criar usuario")
  void criaUsuarioTest(){
      Usuario usuario = new Usuario("Kilian", "kilian.melcher@ccc.ufcg.edu.br");

      assertEquals("Kilian", usuario.getNome());
      assertEquals("kilian.melcher@ccc.ufcg.edu.br", usuario.getEmail());
      assertEquals(0, usuario.getTarefas().size());
  }

  @Test
  @DisplayName("Teste para adicionar tarefa ao usuario com vencimento aleatorio")
  @RepeatedTest(10)
  void adicionaTarefaAoUsuarioTest(){
      Usuario usuario = new Usuario("Kilian", "kilian.melcher@ccc.ufcg.edu.br");

      LocalDate vencimentoAleatorio = gerarDataAleatoria();

      usuario.adicionaTarefa(
              this.tarefaExemplo.getTitulo(),
              this.tarefaExemplo.getDescricao(),
              vencimentoAleatorio,
              this.tarefaExemplo.getPrioridade()
      );

      assertEquals(1, usuario.getTarefas().size());
  }

  @Test
  @DisplayName("Teste para adicionar tarefa ao usuario com prioridade aleatoria")
  @RepeatedTest(10)
  void adicionaTarefaAoUsuarioTest(){
      Usuario usuario = new Usuario("Kilian", "kilian.melcher@ccc.ufcg.edu.br");

      LocalDate vencimentoAleatorio = gerarDataAleatoria();
      int prioridadeAleatoria = random.nextInt(3) + 1;

      usuario.adicionaTarefa(
              this.tarefaExemplo.getTitulo(),
              this.tarefaExemplo.getDescricao(),
              this.tarefaExemplo.getVencimento()
              prioridadeAleatoria
      );

      assertEquals(1, usuario.getTarefas().size());
  }


}