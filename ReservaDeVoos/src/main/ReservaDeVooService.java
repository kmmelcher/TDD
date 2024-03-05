package main;

import java.util.ArrayList;
import java.util.List;

public class ReservaDeVooService {

    private List<Voo> voos;

    public ReservaDeVooService() {
        this.voos = new ArrayList<>();
    }

    public boolean adicionaVoo(Voo voo) {
        return voos.add(voo);
    }

    public boolean reservaVoo(int id, int assento) {
        for (Voo vooCadastrado : voos) {
            if (vooCadastrado.getId() == id && !vooCadastrado.getAssentos()[assento-1]) {
                vooCadastrado.getAssentos()[assento-1] = true;
                return true;
            }
        }
        return false;
    }

    private Voo buscaVoo(int id) {
        for (Voo vooCadastrado : voos) {
            if (vooCadastrado.getId() == id) {
                return vooCadastrado;
            }
        }
        return null;
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
