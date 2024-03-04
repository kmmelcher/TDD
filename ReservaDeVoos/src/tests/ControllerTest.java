package tests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class ControllerTest {

    private List<Voo> voos;

    private ReservaDeVooService reservaDeVooService;


    @BeforeEach
    public void setFlightsUp() {
        voos = new ArrayList<>();
        voos.add(
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
        assertTrue(reservaDeVooService.reservarVoo(1, 12));
    }

}
