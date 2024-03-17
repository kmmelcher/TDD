package tests.util;

import main.Reserva;
import main.ReservaDeVooService;
import main.Voo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Mocks {

    protected ReservaDeVooService reservaDeVooService;

    protected Date setTime(int day, int month, int year, int hour) {

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

    protected void incluiVoo100Vagas() {
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

    protected void incluiVooAntigo() {
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

    protected void incluiVooComReserva(int id) {
        incluiVoo100Vagas();
        fazReserva(id);
    }

    protected void fazReserva(int id) {
        reservaDeVooService.reservaVoo(id, "Vinícius", 1, "83");
    }

    protected void incluiVoos() {
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
    protected void incluiReservaEmVooAntigo() {
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
