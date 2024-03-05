package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class ReservaDeVooService {

    private List<Voo> voos;

    public ReservaDeVooService() {
        this.voos = new ArrayList<>();
    }

    public void adicionaVoo(Voo voo) {
        if (isVooPresente(voo.getId())) {
            throw new IllegalArgumentException("Um vôo com este id já existe no sistema");
        }

        voos.add(voo);
    }

    private boolean isVooPresente(int id) {
        boolean isPresente = false;

        for (Voo vooCadastrado : voos) {
            if (vooCadastrado.getId() == id) {
                isPresente = true;
                break;
            }
        }
        return isPresente;
    }

    public void reservaVoo(int id, String nome, int numeroDePassageiros, String contato) {
        Voo voo = buscaVoo(id);
        int assento = buscaAssentoDisponivel(numeroDePassageiros, voo);

        Reserva reserva = new Reserva(nome, numeroDePassageiros, contato, assento);

        for (int i = assento-1; i < assento-1+numeroDePassageiros; i++) {
            voo.getAssentos()[i] = reserva;
        }
    }

    private int buscaAssentoDisponivel(int numeroDePassageiros, Voo voo) {
        int contador = 0;
        for (int i = 0; i < voo.getAssentos().length; i++) {
            if (voo.getAssentos()[i] == null) {
                contador++;
                if (contador == numeroDePassageiros) {
                    return (i + 1) - (numeroDePassageiros - 1);
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
                "Preço: R$" + voo.getPreco().toString() + "\n" +
                "Dia e hora: " + voo.getDataHora().toString() + "\n" +
                "Capacidade: " + voo.getAssentos().length + " passageiros\n" +
                "(" + getAssentosDisponiveis(voo) + " assentos disponíveis)\n";
    }

    private int getAssentosDisponiveis(Voo voo) {
        int assentosDisponiveis = 0;
        for (Reserva reserva : voo.getAssentos()) {
            if (reserva == null) {
                assentosDisponiveis++;
            }
        }
        return assentosDisponiveis;
    }

    public int getAssentosDisponiveis(int id) {
        return getAssentosDisponiveis(buscaVoo(id));
    }

    private List<Voo> getVoosDisponiveis() {
        List<Voo> voosDisponiveis = new ArrayList<>();
        for (Voo voo : voos) {
            if (isDisponivel(voo)) {
                voosDisponiveis.add(voo);
            }
        }
        return voosDisponiveis;
    }

    private String exibeVoos(List<Voo> voosExibidos) {
        StringBuilder retorno = new StringBuilder();
        for (Voo voo : voosExibidos) {
            retorno.append("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
            retorno.append(exibeVoo(voo.getId()));
        }
        return retorno.toString();
    }

    public String exibeVoosDisponiveis() {
        return exibeVoos(getVoosDisponiveis());
    }

    private boolean isDisponivel(Voo voo) {
        return getAssentosDisponiveis(voo) > 0 &&
                voo.getDataHora().after(new Date());
    }

    public String pesquisaPorOrigem(String origem) {
        List<Voo> voosDisponiveis = getVoosDisponiveis();
        List<Voo> matches = new ArrayList<>();
        for (Voo voo : voosDisponiveis) {
            if (voo.getOrigem().contains(origem)) {
                matches.add(voo);
            }
        }
        return exibeVoos(matches);
    }
}
