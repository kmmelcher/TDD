package tests;

import main.ReservaDeVooService;
import main.Voo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class ControllerTest {

    private ReservaDeVooService reservaDeVooService;


    @BeforeEach
    public void setFlightsUp() {
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
    public void testReservarAssento() {
        Assertions.assertTrue(reservaDeVooService.reservaVoo(
                1,
                "Vinícius Azevedo",
                3,
                "83997448122"
        ));
    }

    @Test
    public void testReservarVooInexisteste() {
        Assertions.assertFalse(reservaDeVooService.reservaVoo(
                2,
                "Gustavo Alberto",
                6,
                "83994445112"
        ));
    }

    @Test
    public void testReservarVooLotado() {
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
        Assertions.assertFalse(reservaDeVooService.reservaVoo(
                2,
                "Vinícius Azevedo",
                1,
                "83998786544"
        ));
    }
    

    @Test
    public void testExibeInfosVoo() {
        Assertions.assertEquals(
                "== EXIBIÇÃO DE VÔO DE ID 1 ==\n" +
                        "Origem: Aeroporto X de Brasília\n" +
                        "Destino: Aeroporto Y de Salvador\n" +
                        "Preço R$1200\n" +
                        "Capacidade: 200 passageiros", // FIXME incluir assentos disponíveis (x disponíveis)
                reservaDeVooService.exibeVoo(1));
    }

    /*
        TODO:
            1- Adição duplicada de vôo
            2- exibição de vôo inexistente
            3- get assentos disponíveis
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
