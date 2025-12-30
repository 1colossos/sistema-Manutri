package model;

import java.util.ArrayList;
import java.util.List;

public class PlanoAlimentar {

    private String nome;
    private Paciente paciente;
    private List<Refeicao> refeicoes;

    public PlanoAlimentar(String nome, Paciente paciente) {
        this.nome = nome;
        this.paciente = paciente;
        this.refeicoes = new ArrayList<>();
    }

    public void adicionarRefeicao(Refeicao refeicao) {
        this.refeicoes.add(refeicao);
    }

    public double calcularCaloriasDiarias() {
        double total = 0;
        for (Refeicao r : refeicoes) {
            total += r.calcularTotalCalorias();
        }
        return total;
    }

    public String getNome() {
        return nome;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    @Override
    public String toString() {
        return "Plano: " + nome + " | Paciente: " + paciente.getNome() +
                " | Total Dia: " + calcularCaloriasDiarias() + " Kcal";
    }
}
