package model;

public class Alimento {

    private String nome;
    private double porcaoGramas;
    private double proteina;
    private double carboidrato;
    private double gordura;
    private double calorias;

    public Alimento(String nome, double porcaoGramas, double proteina, double carboidrato, double gordura, double calorias) {
        this.nome = nome;
        this.porcaoGramas = porcaoGramas;
        this.proteina = proteina;
        this.carboidrato = carboidrato;
        this.gordura = gordura;
        this.calorias = calorias;
    }


    public String getNome() { return nome; }
    public double getPorcaoGramas() {
        return porcaoGramas;
    }

    public double getProteina() {
        return proteina;
    }

    public double getCarboidrato() {
        return carboidrato;
    }

    public double getGordura() {
        return gordura;
    }

    public double getCalorias() {
        return calorias;
    }

    @Override
    public String toString() {
        return String.format("%s (%.0fg) | Cal: %.0f | P: %.1f | C: %.1f | G: %.1f",
                nome, porcaoGramas, calorias, proteina, carboidrato, gordura);
    }
}

