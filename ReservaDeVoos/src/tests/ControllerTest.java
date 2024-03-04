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
    public void testAlugarAssento() {
        Assertions.assertTrue(reservaDeVooService.reservaVoo(1, 12));
    }

    @Test
    public void testAlugarVooInexisteste() {
        Assertions.assertFalse(reservaDeVooService.reservaVoo(2, 9));
    }

}
