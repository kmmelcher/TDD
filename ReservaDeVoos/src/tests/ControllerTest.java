package tests;

import main.Reserva;
import main.ReservaDeVooService;
import main.Voo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

public class ControllerTest {

    private ReservaDeVooService reservaDeVooService;

    private Date setTime(int day, int month, int year, int hour) {

        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    private void incluiVoo100Vagas() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        2,
                        "Aeroporto A de Belo Horizonte",
                        "Aeroporto B de Cuiabá",
                        setTime(21, 12, 2024, 15),
                        new BigDecimal(20),
                        100
                )
        );
    }

    private void incluiVooAntigo() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        3,
                        "Aeroporto A de Belo Horizonte",
                        "Aeroporto B de Cuiabá",
                        setTime(21, 12, 2020, 15),
                        new BigDecimal(20),
                        100
                )
        );
    }

    private void incluiVooComReserva(int id) {
        incluiVoo100Vagas();
        fazReserva(id);
    }

    private void fazReserva(int id) {
        reservaDeVooService.reservaVoo(id, "Vinícius", 1, "83");
    }

    private void incluiVoos() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        2,
                        "Aeroporto A de Belo Horizonte",
                        "Aeroporto B de Cuiabá",
                        setTime(21, 12, 2024, 15),
                        new BigDecimal(20),
                        20
                )
        );
        reservaDeVooService.adicionaVoo(
                new Voo(
                        3,
                        "Aeroporto A de Brasília",
                        "Aeroporto B de Cuiabá",
                        setTime(21, 6, 2024, 15),
                        new BigDecimal(20),
                        20
                )
        );
        reservaDeVooService.adicionaVoo(
                new Voo(
                        4,
                        "Aeroporto R de Brasília",
                        "Aeroporto Y de Cuiabá",
                        setTime(21, 6, 2020, 15),
                        new BigDecimal(20),
                        20
                )
        );
    }

    @BeforeEach
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
    void testAdicionaVooDuplicado() {
        try {
            reservaDeVooService.adicionaVoo(
                    new Voo(
                            1,
                            "Teste",
                            "Teste",
                            setTime(12, 12, 2024, 15),
                            new BigDecimal(25),
                            200
                    )
            );
            Assertions.fail("Vôo duplicado inserido");
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    void testReservarAssento() {
        reservaDeVooService.reservaVoo(
                1,
                "Vinícius Azevedo",
                3,
                "83997448122"
        );
    }

    @Test
    void testReservarVooInexisteste() {
        try {
            reservaDeVooService.reservaVoo(
                    2,
                    "Gustavo Alberto",
                    6,
                    "83994445112"
            );
            Assertions.fail("Era esperado um NoSuchElementException");
        } catch (NoSuchElementException ignored) {

        }
    }

    @Test
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
        try {
            reservaDeVooService.reservaVoo(
                    2,
                    "Vinícius Azevedo",
                    1,
                    "83998786544"
            );
            Assertions.fail("Reserva concluída em vôo lotado");
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
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

    @Test
    void testExibeVooInexistente() {
        try {
            reservaDeVooService.exibeVoo(5);
            Assertions.fail("Vôo inexistente exibido");
        } catch (NoSuchElementException ignored) {}
    }

    @Test
    void testGetAssentosDisponiveis() {
        Assertions.assertEquals(
                200,
                reservaDeVooService.getAssentosDisponiveis(1)
        );
    }

    @Test
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
    void testPesquisaPorOrigemInexistente() {
        incluiVoos();
        Assertions.assertEquals("", reservaDeVooService.pesquisaPorOrigem("João Pessoa"));
    }

    @Test
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
    void testPesquisaPorDestinoInexistente() {
        incluiVoos();
        Assertions.assertEquals("", reservaDeVooService.pesquisaPorDestino("João Pessoa"));
    }

    @Test
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
    void testPesquisaPorDataPassada() {
        incluiVoos();
        Assertions.assertEquals("", reservaDeVooService.pesquisaPorData(21, 6, 2020));
    }

    @Test
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

    @Test
    void testPesquisaPorNumPassageirosNegativo() {
        incluiVoos();

        Assertions.assertEquals("", reservaDeVooService.pesquisaPorQtdPassageiros(-1));
    }

    @Test
    void testPesquisaPorNumPassageirosPositivo() {
        incluiVoos();

        Assertions.assertEquals("", reservaDeVooService.pesquisaPorQtdPassageiros(6));
    }

    @Test
    void testCancelarVooPorCodigoDeReserva() {
        incluiVoos();

        reservaDeVooService.reservaVoo(1, "Vinícius", 200, "8");

        reservaDeVooService.cancelarVoo(1, 1);

        reservaDeVooService.reservaVoo(1, "Maria", 150, "77665544");
    }

    @Test
    void testCancelarVooCodigoInexistente() {
        incluiVoos();

        reservaDeVooService.reservaVoo(1, "Vinícius", 14, "8");
        reservaDeVooService.reservaVoo(1, "Isabela", 20, "8");
        reservaDeVooService.reservaVoo(1, "Ulisses", 5, "8");

        try {
            reservaDeVooService.cancelarVoo(1, 7);
            Assertions.fail("Vôo de reserva inexistente cancelado");
        } catch (IllegalArgumentException ignored) {}

    }

    @Test
    void testCancelarVooPorIdentificacaoPessoal() {
        incluiVoos();

        reservaDeVooService.reservaVoo(1, "Vinícius", 200, "8");

        reservaDeVooService.cancelarVoo(1, "Vinícius");

        reservaDeVooService.reservaVoo(1, "Maria", 150, "77665544");
    }

    @Test
    void testCancelarVooIdentificacaoPessoalInexistente() {
        incluiVoos();

        reservaDeVooService.reservaVoo(1, "Vinícius", 14, "8");
        reservaDeVooService.reservaVoo(1, "Isabela", 20, "8");
        reservaDeVooService.reservaVoo(1, "Ulisses", 5, "8");

        try {
            reservaDeVooService.cancelarVoo(1, "Carlos");
            Assertions.fail("Cancelado um vôo sem reserva");
        } catch (IllegalArgumentException ignored) {}

    }

    @Test
    void testValoresLimite() {
        incluiVoo100Vagas();
        reservaDeVooService.reservaVoo(2, "Vinícius", 1, "83"); // CT1
        reservaDeVooService.reservaVoo(2, "Tony", 1, "80"); // CT2

        reservaDeVooService.reservaVoo(2, "Stark", 46, "83");

        reservaDeVooService.reservaVoo(2, "Maya", 1, "83"); // CT3

        reservaDeVooService.reservaVoo(2, "Peter", 50, "83");

        reservaDeVooService.reservaVoo(2, "Luke", 1, "83"); // CT4

        try {
            reservaDeVooService.reservaVoo(2, "Jarvis", 1, "83"); // CT5
            Assertions.fail("foi feita uma reserva num vôo já lotado");
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    void testAgendamentoComQuantidadesVariadasDePessoas() {
        incluiVoo100Vagas();

        try {
            reservaDeVooService.reservaVoo(2, "Jarvis", 0, "83"); // CT1
            Assertions.fail("foi feita uma reserva sem pessoas");
        } catch (IllegalArgumentException ignored) {}

        reservaDeVooService.reservaVoo(2, "Jarvis", 5, "83"); // CT2

        try {
            reservaDeVooService.reservaVoo(2, "Jarvis", 200, "83"); // CT3
            Assertions.fail("foi feita uma reserva sem uma quantidade de pessoas suportada pelo sistema.");
        } catch (IllegalArgumentException ignored) {}

    }

    @Test
    void testReservaEmDatasDistintas() {
        incluiVooComReserva(2);
        Assertions.assertFalse(reservaDeVooService.isAssentoDisponivel(2, 1));

        reservaDeVooService.cancelarVoo(2, "Vinícius");
        Assertions.assertTrue(reservaDeVooService.isAssentoDisponivel(2, 1)); // CT01

        fazReserva(2);
        reservaDeVooService.cancelarVoo(2, 1);
        Assertions.assertTrue(reservaDeVooService.isAssentoDisponivel(2, 1)); // CT02

        incluiVooAntigo();
        fazReserva(3);
        incluiReservaEmVooAntigo();
        try {
            reservaDeVooService.cancelarVoo(3, "Vinícius");
            Assertions.fail("Cancelado um vôo antigo"); // CT03
        } catch (IllegalArgumentException ignored) {}

        try {
            reservaDeVooService.cancelarVoo(3, 1);
            Assertions.fail("Cancelado um vôo antigo"); // CT04
        } catch (IllegalArgumentException ignored) {}

        try {
            reservaDeVooService.cancelarVoo(2, "Beatriz");
            Assertions.fail("Cancelado um vôo sem reserva"); // CT05
        } catch (IllegalArgumentException ignored) {}

        try {
            reservaDeVooService.cancelarVoo(2, 6);
            Assertions.fail("Cancelado um vôo sem reserva"); // CT06
        } catch (IllegalArgumentException ignored) {}

        try {
            reservaDeVooService.cancelarVoo(3, "Beatriz");
            Assertions.fail("Cancelado um vôo antigo e sem reserva"); // CT07
        } catch (IllegalArgumentException ignored) {}

        try {
            reservaDeVooService.cancelarVoo(3, 6);
            Assertions.fail("Cancelado um vôo antigo e sem reserva"); // CT08
        } catch (IllegalArgumentException ignored) {}
    }

    private void incluiReservaEmVooAntigo() {
        reservaDeVooService
                .buscaVoo(3)
                .getAssentos()[0] = new Reserva(
                        "Vinícius",
                        1,
                        "839",
                        1,
                        1
                );
    }
}
