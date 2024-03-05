package tests;

import main.ReservaDeVooService;
import main.Voo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import static org.junit.Assert.fail;

public class ControllerTest {

    private ReservaDeVooService reservaDeVooService;


    @BeforeEach
    void setFlightsUp() {
        reservaDeVooService = new ReservaDeVooService();
        reservaDeVooService.adicionaVoo(
                new Voo(
                        1,
                        "Aeroporto X de Brasília",
                        "Aeroporto Y de Salvador",
                        "21/12/2024 | 15h",
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
                            "12/12/24 14h",
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
                        "3/09/2026 12h",
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
                        "3/09/2026 12h",
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
                        "Preço R$1200\n" +
                        "Capacidade: 200 passageiros\n" +
                        "(200 assentos disponíveis)",
                reservaDeVooService.exibeVoo(1));
    }

    @Test
    void testExibeInfosVooLotado() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        2,
                        "Origem genérica",
                        "Destino genérico",
                        "03/03/2024 14h",
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
                        "Preço R$500\n" +
                        "Capacidade: 2 passageiros\n" +
                        "(0 assentos disponíveis)",
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
                        "C",
                        new BigDecimal(9),
                        0
                )
        );
        Assertions.assertEquals(
                0,
                reservaDeVooService.getAssentosDisponiveis(2)
        );
    }

    /*
        TODO:
            4- get assentos disponíveis (nenhum disponível)
            5- get Voos disponíveis
            6- get voos disponíveis (com vôos passados E/OU vôos esgotados presentes)
            7- Pesquisa por origem
            8- Pesquisa por origem (nenhum disponível)
            9- Pesquisa por origem (com destinos sendo a origem)
            10- Pesquisa por destino
            11- Pesquisa por destino (nenhum disponível)
            12- Pesquisa por destino (com origens sendo o destino)
            13- Pesquisa por data
            14- Pesquisa por data passada
            15- Pesquisa por número de passageiros
            16- Pesquisa por número de passageiros (valor negativo)
            17- Pesquisa por número de passageiros (valor zero)
            18- Cancelar Voo (por código de reserva)
            19- Cancelar Voo (por identificação pessoal)
            20- Cancelar Voo (codigo inexistente)
            21- Cancelar Voo (Identificação pessoal inexistente)
     */
}
