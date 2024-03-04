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

}
