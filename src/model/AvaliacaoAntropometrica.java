package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AvaliacaoAntropometrica {

    private double peso;
    private double altura;
    private LocalDate dataAvaliacao;

    public AvaliacaoAntropometrica(double peso, double altura) {
        this.peso = peso;
        this.altura = altura;
        this.dataAvaliacao = LocalDate.now();
    }

    public double calcularIMC() {
        if (altura > 0) {
            return peso / (altura * altura);
        }
        return 0.0;
    }

    public String getDiagnosticoIMC() {
        double imc = calcularIMC();

        if (imc < 18.5) return "Baixo Peso";
        if (imc < 24.9) return "Eutrofia (Peso Normal)";
        if (imc < 29.9) return "Sobrepeso";
        if (imc < 34.9) return "Obesidade Grau I";
        if (imc < 39.9) return "Obesidade Grau II";
        return "Obesidade Grau III (Mórbida)";
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Data: %s | Peso: %.2fkg | Altura: %.2fm | IMC: %.2f (%s)", dataAvaliacao.format(fmt), peso, altura, calcularIMC(), getDiagnosticoIMC());
    }
}