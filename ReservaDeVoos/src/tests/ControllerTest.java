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
        Assertions.assertTrue(reservaDeVooService.reservaVoo(1, 12));
    }

    @Test
    public void testReservarVooInexisteste() {
        Assertions.assertFalse(reservaDeVooService.reservaVoo(2, 9));
    }

    @Test
    public void testReservarVooAssentoOcupado() {
        reservaDeVooService.adicionaVoo(
                new Voo(
                        2,
                        "Aeroporto X de João Pessoa",
                        "Aeroporto Y de Porto Alegre",
                        "3/09/2026 12h",
                        new BigDecimal(1600),
                        1
                )
        );
        reservaDeVooService.reservaVoo(2, 1);
        Assertions.assertFalse(reservaDeVooService.reservaVoo(2, 1));
    }

}
