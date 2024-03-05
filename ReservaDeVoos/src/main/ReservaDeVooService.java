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

    public boolean reservaVoo(int id, String nome, int numeroDePassageiros, String contato) {
        Voo voo = buscaVoo(id);

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
