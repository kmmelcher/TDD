package tests.functionalTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.util.Mocks;

public class FunctionalTests extends Mocks {

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

}
