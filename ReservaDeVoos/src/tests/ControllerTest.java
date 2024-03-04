package tests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class ControllerTest {

    private ReservaDeVooService reservaDeVooService;


    @BeforeEach
    public void setFlightsUp() {
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
        assertTrue(reservaDeVooService.reservaVoo(1, 12));
    }

}
