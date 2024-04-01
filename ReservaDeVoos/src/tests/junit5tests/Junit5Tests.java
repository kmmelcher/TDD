package tests.junit5tests;

import main.ReservaDeVooService;
import main.Voo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.ValueSource;
import tests.util.Mocks;
import java.math.BigDecimal;
import java.util.Date;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Classe de teste do ReservaDeVooService")
public class Junit5Tests extends Mocks {



    @BeforeEach
    @DisplayName("Configura vôos para testes")
    void setFlightsUp() {
        reservaDeVooService = new ReservaDeVooService();
        reservaDeVooService.adicionaVoo(
                new Voo(
                        1,
                        "Aeroporto X de Brasília",
                        "Aeroporto Y de Salvador",
                        setTime(21, 12, 2024, 15),
                        new BigDecimal(1200),
                        200
                )
        );
    }

    @Test
    @DisplayName("Adiciona Vôo dupluicado")
    void testAdicionaVooDuplicado() {
        assertThrows(IllegalArgumentException.class, () ->
                reservaDeVooService.adicionaVoo(
                        new Voo(
                                1,
                                "Teste",
                                "Teste",
                                setTime(12, 12, 2024, 15),
                                new BigDecimal(25),
                                200
                        )
                )
        );
    }

    @Test
    @DisplayName("Teste de reserva de assento")
    void testReservarAssento() {
        reservaDeVooService.reservaVoo(
                1,
                "Vinícius Azevedo",
                3,
                "83997448122"
        );
    }

    @Test
    @DisplayName("Reserva de vôo inexistente")
    void testReservarVooInexisteste() {
        assertThrows(NoSuchElementException.class, () ->
                reservaDeVooService.reservaVoo(
                        2,
                        "Gustavo Alberto",
                        6,
                        "83994445112"
                )
        );
    }

    @Test
    @DisplayName("Reserva de voo lotado")
    void testReservarVooLotado() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        2,
                        "Aeroporto X de João Pessoa",
                        "Aeroporto Y de Porto Alegre",
                        setTime(3, 9, 2026, 14),
                        new BigDecimal(1600),
                        3
                )
        );
        reservaDeVooService.reservaVoo(
                2,
                "Anabelle Sousa",
                3,
                "83557746889"
        );
        assertThrows(IllegalArgumentException.class, () ->
                reservaDeVooService.reservaVoo(
                        2,
                        "Vinícius Azevedo",
                        1,
                        "83998786544"
                )
        );
    }

    @Test
    @DisplayName("Reserva de voo quase lotado")
    void testReservarVooQuaseLotado() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        2,
                        "Aeroporto X de João Pessoa",
                        "Aeroporto Y de Porto Alegre",
                        setTime(3, 9, 2026, 12),
                        new BigDecimal(1600),
                        3
                )
        );
        reservaDeVooService.reservaVoo(
                2,
                "Anabelle Sousa",
                2,
                "83557746889"
        );

        reservaDeVooService.reservaVoo(
                2,
                "Vinícius Azevedo",
                1,
                "83999586897"
        );
    }


    @Test
    @DisplayName("teste de exibição de informações de um voo")
    void testExibeInfosVoo() {
        Assertions.assertEquals(
                "== EXIBIÇÃO DE VÔO DE ID 1 ==\n" +
                        "Origem: Aeroporto X de Brasília\n" +
                        "Destino: Aeroporto Y de Salvador\n" +
                        "Preço: R$1200\n" +
                        "Dia e hora: Sat Dec 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 200 passageiros\n" +
                        "(200 assentos disponíveis)\n",
                reservaDeVooService.exibeVoo(1));
    }

    @Test
    @DisplayName("exibição de voo lotado")
    void testExibeInfosVooLotado() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        2,
                        "Origem genérica",
                        "Destino genérico",
                        setTime(3, 3, 2024, 14),
                        new BigDecimal(500),
                        2
                )
        );
        reservaDeVooService.reservaVoo(
                2,
                "Alberto",
                2,
                "83"
        );

        Assertions.assertEquals(
                "== EXIBIÇÃO DE VÔO DE ID 2 ==\n" +
                        "Origem: Origem genérica\n" +
                        "Destino: Destino genérico\n" +
                        "Preço: R$500\n" +
                        "Dia e hora: Sun Mar 03 14:00:00 BRT 2024\n" +
                        "Capacidade: 2 passageiros\n" +
                        "(0 assentos disponíveis)\n",
                reservaDeVooService.exibeVoo(2));
    }

    @DisplayName("exibição de voo inexistente")
    @ValueSource(ints = {0, 2, 3, 5})
    void testExibeVooInexistente(int ints) {
        assertThrows(NoSuchElementException.class, () ->
                reservaDeVooService.exibeVoo(ints)
        );
    }

    @Test
    @DisplayName("teste de obtenção de assentos disponíveis")
    void testGetAssentosDisponiveis() {
        Assertions.assertEquals(
                200,
                reservaDeVooService.getAssentosDisponiveis(1)
        );
    }

    @Test
    @DisplayName("obtenção de assentos (nenhum disponível)")
    void testGetAssentosDisponiveisNenhumDisponivel() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        2,
                        "A",
                        "B",
                        new Date(),
                        new BigDecimal(9),
                        0
                )
        );
        Assertions.assertEquals(
                0,
                reservaDeVooService.getAssentosDisponiveis(2)
        );
    }

    @Test
    @DisplayName("obtem voos disponiveis")
    void getVoosDisponiveis() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        2,
                        "X",
                        "Y",
                        setTime(21, 12, 2024, 15),
                        new BigDecimal(20),
                        0
                )
        );
        reservaDeVooService.adicionaVoo(
                new Voo(
                        3,
                        "A",
                        "B",
                        setTime(21, 12, 2024, 15),
                        new BigDecimal(9),
                        1
                )
        );
        Assertions.assertEquals(
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 1 ==\n" +
                        "Origem: Aeroporto X de Brasília\n" +
                        "Destino: Aeroporto Y de Salvador\n" +
                        "Preço: R$1200\n" +
                        "Dia e hora: Sat Dec 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 200 passageiros\n" +
                        "(200 assentos disponíveis)\n" +
                        "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 3 ==\n" +
                        "Origem: A\n" +
                        "Destino: B\n" +
                        "Preço: R$9\n" +
                        "Dia e hora: Sat Dec 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 1 passageiros\n" +
                        "(1 assentos disponíveis)\n",
                reservaDeVooService.exibeVoosDisponiveis()
        );
    }

    @Test
    @DisplayName("obter voos passados disponiveis (invalido)")
    void testGetVoosDisponiveisPassados() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        2,
                        "X",
                        "Y",
                        setTime(21, 12, 2023, 15),
                        new BigDecimal(20),
                        20
                )
        );
        Assertions.assertEquals(
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 1 ==\n" +
                        "Origem: Aeroporto X de Brasília\n" +
                        "Destino: Aeroporto Y de Salvador\n" +
                        "Preço: R$1200\n" +
                        "Dia e hora: Sat Dec 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 200 passageiros\n" +
                        "(200 assentos disponíveis)\n",
                reservaDeVooService.exibeVoosDisponiveis()
        );

    }

    @Test
    @DisplayName("teste de pesquisa por origem")
    void testPesquisaPorOrigem() {
        incluiVoos();

        Assertions.assertEquals(
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 1 ==\n" +
                        "Origem: Aeroporto X de Brasília\n" +
                        "Destino: Aeroporto Y de Salvador\n" +
                        "Preço: R$1200\n" +
                        "Dia e hora: Sat Dec 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 200 passageiros\n" +
                        "(200 assentos disponíveis)\n" +
                        "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 3 ==\n" +
                        "Origem: Aeroporto A de Brasília\n" +
                        "Destino: Aeroporto B de Cuiabá\n" +
                        "Preço: R$20\n" +
                        "Dia e hora: Fri Jun 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 20 passageiros\n" +
                        "(20 assentos disponíveis)\n",
                reservaDeVooService.pesquisaPorOrigem("Brasília")
        );
    }

    @Test
    @DisplayName("teste de pesquisa por origem (voo inexistente)")
    void testPesquisaPorOrigemInexistente() {
        incluiVoos();
        Assertions.assertEquals("", reservaDeVooService.pesquisaPorOrigem("João Pessoa"));
    }

    @Test
    @DisplayName("teste de pesquisa por destino")
    void testPesquisaPorDestino() {
        incluiVoos();

        Assertions.assertEquals(
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 2 ==\n" +
                        "Origem: Aeroporto A de Belo Horizonte\n" +
                        "Destino: Aeroporto B de Cuiabá\n" +
                        "Preço: R$20\n" +
                        "Dia e hora: Sat Dec 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 20 passageiros\n" +
                        "(20 assentos disponíveis)\n" +
                        "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 3 ==\n" +
                        "Origem: Aeroporto A de Brasília\n" +
                        "Destino: Aeroporto B de Cuiabá\n" +
                        "Preço: R$20\n" +
                        "Dia e hora: Fri Jun 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 20 passageiros\n" +
                        "(20 assentos disponíveis)\n",
                reservaDeVooService.pesquisaPorDestino("Cuiabá")
        );
    }

    @Test
    @DisplayName("teste de pesquisa por destino (resultado inexistente)")
    void testPesquisaPorDestinoInexistente() {
        incluiVoos();
        Assertions.assertEquals("", reservaDeVooService.pesquisaPorDestino("João Pessoa"));
    }

    @Test
    @DisplayName("Teste de pesquisa por data")
    void testPesquisaPorData() {
        incluiVoos();

        Assertions.assertEquals(
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 1 ==\n" +
                        "Origem: Aeroporto X de Brasília\n" +
                        "Destino: Aeroporto Y de Salvador\n" +
                        "Preço: R$1200\n" +
                        "Dia e hora: Sat Dec 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 200 passageiros\n" +
                        "(200 assentos disponíveis)\n" +
                        "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 2 ==\n" +
                        "Origem: Aeroporto A de Belo Horizonte\n" +
                        "Destino: Aeroporto B de Cuiabá\n" +
                        "Preço: R$20\n" +
                        "Dia e hora: Sat Dec 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 20 passageiros\n" +
                        "(20 assentos disponíveis)\n",
                reservaDeVooService.pesquisaPorData(21, 12, 2024)
        );
    }

    @Test
    @DisplayName("pesquisa por data passada")
    void testPesquisaPorDataPassada() {
        incluiVoos();
        Assertions.assertEquals("", reservaDeVooService.pesquisaPorData(21, 6, 2020));
    }

    @Test
    @DisplayName("pesquisa por numero de passageiros")
    void testPesquisaPorNumPassageirosZerado() {
        incluiVoos();
        reservaDeVooService.reservaVoo(3, "Vinícius", 8, "839");

        Assertions.assertEquals(
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 1 ==\n" +
                        "Origem: Aeroporto X de Brasília\n" +
                        "Destino: Aeroporto Y de Salvador\n" +
                        "Preço: R$1200\n" +
                        "Dia e hora: Sat Dec 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 200 passageiros\n" +
                        "(200 assentos disponíveis)\n" +
                        "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" +
                        "== EXIBIÇÃO DE VÔO DE ID 2 ==\n" +
                        "Origem: Aeroporto A de Belo Horizonte\n" +
                        "Destino: Aeroporto B de Cuiabá\n" +
                        "Preço: R$20\n" +
                        "Dia e hora: Sat Dec 21 15:00:00 BRT 2024\n" +
                        "Capacidade: 20 passageiros\n" +
                        "(20 assentos disponíveis)\n",
                reservaDeVooService.pesquisaPorQtdPassageiros(0)
        );
    }

    @DisplayName("pesquisa por numero de passageiros negativo")
    @ValueSource(ints = {-1, -3, -7, -10})
    void testPesquisaPorNumPassageirosNegativo(int ints) {
        incluiVoos();

        Assertions.assertEquals("", reservaDeVooService.pesquisaPorQtdPassageiros(ints));
    }

    @Test
    @DisplayName("pesquisa por numero de passageiros positivo")
    void testPesquisaPorNumPassageirosPositivo() {
        incluiVoos();

        Assertions.assertEquals("", reservaDeVooService.pesquisaPorQtdPassageiros(6));
    }

    @Test
    @DisplayName("cancelar voo por codigo de reserva")
    void testCancelarVooPorCodigoDeReserva() {
        incluiVoos();

        reservaDeVooService.reservaVoo(1, "Vinícius", 200, "8");

        reservaDeVooService.cancelarVoo(1, 1);

        reservaDeVooService.reservaVoo(1, "Maria", 150, "77665544");
    }

    @Test
    @DisplayName("cancelar voo por codigo de reserva (inválido)")
    void testCancelarVooInvalidoPorCodigoDeReserva() {
        incluiVooAntigo();

        assertThrows(IllegalArgumentException.class, () ->
            this.reservaDeVooService.cancelarVoo(3, 1)
        );
    }

    @Test
    @DisplayName("cancelar voo com código inexistente")
    void testCancelarVooCodigoInexistente() {
        incluiVoos();

        reservaDeVooService.reservaVoo(1, "Vinícius", 14, "8");
        reservaDeVooService.reservaVoo(1, "Isabela", 20, "8");
        reservaDeVooService.reservaVoo(1, "Ulisses", 5, "8");

        assertThrows(IllegalArgumentException.class, () ->
                reservaDeVooService.cancelarVoo(1, 7)
        );
    }

    @DisplayName("cancelar voo por identificação pessoal")
    @RepeatedTest(10)
    void testCancelarVooPorIdentificacaoPessoal() {
        incluiVoos();

        reservaDeVooService.reservaVoo(1, "Vinícius", 200, "8");

        reservaDeVooService.cancelarVoo(1, "Vinícius");

        reservaDeVooService.reservaVoo(1, "Maria", 150, "77665544");
    }

    @Test
    @DisplayName("cancelar voo por identificação pessoal (inexistente)")
    void testCancelarVooIdentificacaoPessoalInexistente() {
        incluiVoos();

        reservaDeVooService.reservaVoo(1, "Vinícius", 14, "8");
        reservaDeVooService.reservaVoo(1, "Isabela", 20, "8");
        reservaDeVooService.reservaVoo(1, "Ulisses", 5, "8");

        assertThrows(IllegalArgumentException.class, () ->
                reservaDeVooService.cancelarVoo(1, "Carlos")
        );
    }

    @Test
    @DisplayName("cancelar voo por identificação pessoal (inválido)")
    void testCancelarVooInvalidoPorIdentificacaoPessoal() {
        incluiVooAntigo();

        assertThrows(IllegalArgumentException.class, () ->
                this.reservaDeVooService.cancelarVoo(3, "Vinícius")
        );
    }



}
