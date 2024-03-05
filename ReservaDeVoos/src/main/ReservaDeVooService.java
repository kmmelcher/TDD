package main;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ReservaDeVooService {

    private List<Voo> voos;

    public ReservaDeVooService() {
        this.voos = new ArrayList<>();
    }

    public boolean adicionaVoo(Voo voo) {
        return voos.add(voo);
    }

    public void reservaVoo(int id, String nome, int numeroDePassageiros, String contato) {
        Voo voo = buscaVoo(id);
        int assento = buscaAssentoDisponivel(numeroDePassageiros, voo);

        Reserva reserva = new Reserva(nome, numeroDePassageiros, contato, assento);
        voo.getAssentos()[assento] = reserva;

    }

    private int buscaAssentoDisponivel(int numeroDePassageiros, Voo voo) {
        int contador = 0;
        for (int i = 0; i < voo.getAssentos().length; i++) {
            if (voo.getAssentos()[i] == null) {
                contador++;
                if (contador == numeroDePassageiros) {
                    return i + 1 - numeroDePassageiros;
                }
            } else {
                contador = 0;
            }
        }
        throw new IllegalArgumentException("Não há assentos suficientes para essa quantidade de passageiros neste vôo.");
    }

    private Voo buscaVoo(int id) {
        for (Voo vooCadastrado : voos) {
            if (vooCadastrado.getId() == id) {
                return vooCadastrado;
            }
        }
        throw new NoSuchElementException("Não há um Vôo de id " + id);
    }
    public String exibeVoo(int id) {
        Voo voo = buscaVoo(id);

        return "== EXIBIÇÃO DE VÔO DE ID " + id + " ==\n" +
                "Origem: " + voo.getOrigem() + "\n" +
                "Destino: " + voo.getDestino() + "\n" +
                "Preço R$" + voo.getPreco().toString() + "\n" +
                "Capacidade: " + voo.getAssentos().length + " passageiros";
    }
}
