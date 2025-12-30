package model;


import java.util.ArrayList;
import java.util.List;

public class Refeicao {

    private String nome;
    private List<Alimento> alimentos;

    public Refeicao(String nome) {
        this.nome = nome;
        this.alimentos = new ArrayList<>();
    }

    public void adicionarAlimento(Alimento alimento) {
        this.alimentos.add(alimento);
    }

    public double calcularTotalCalorias() {
        double total = 0;
        for (Alimento a : alimentos) total += a.getCalorias();
        return total;
    }

    public double calcularTotalProteina() {
        double total = 0;
        for (Alimento a : alimentos) total += a.getProteina();
        return total;
    }

    public double calcularTotalCarboidrato() {
        double total = 0;
        for (Alimento a : alimentos) total += a.getCarboidrato();
        return total;
    }

    public double calcularTotalGordura() {
        double total = 0;
        for (Alimento a : alimentos) total += a.getGordura();
        return total;
    }

    public String getNome() {
        return nome;
    }

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

}