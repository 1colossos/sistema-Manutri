package service;


import model.Genero;
import model.NivelAtividade;

public class CalculadoraNutricional {

    /**
     * Calcula a Taxa Metabólica Basal (TMB) usando a fórmula de Harris-Benedict.
     * TMB é a energia gasta em repouso.
     * * @param peso Peso do paciente em quilogramas (kg).
     * @param alturaMetros Altura do paciente em metros (m).
     * @param idade Idade do paciente em anos.
     * @param genero Gênero do paciente (MASCULINO ou FEMININO).
     * @return TMB em Kilocalorias (Kcal).
     */
    public static double calcularTMB(double peso, double alturaMetros, int idade, Genero genero) {

        // A fórmula de Harris-Benedict utiliza altura em centímetros (cm)
        double alturaCm = alturaMetros * 100;

        if (genero == Genero.MASCULINO) {
            // Fórmula Harris-Benedict para Homens:
            // TMB = 66,5 + (13,75 * P) + (5,0 * A) - (6,8 * I)
            return 66.5 + (13.75 * peso) + (5.0 * alturaCm) - (6.8 * idade);
        } else {
            // Fórmula Harris-Benedict para Mulheres:
            // TMB = 655,1 + (9,56 * P) + (1,8 * A) - (4,7 * I)
            return 655.1 + (9.56 * peso) + (1.8 * alturaCm) - (4.7 * idade);
        }
    }

    /**
     * Calcula o Gasto Energético Total (GET).
     * GET = TMB * Fator de Atividade.
     * * @param tmb Taxa Metabólica Basal calculada.
     * @param nivelAtividade Nível de atividade física do paciente (Enum).
     * @return GET em Kilocalorias (Kcal).
     */
    public static double calcularGET(double tmb, NivelAtividade nivelAtividade) {
        return tmb * nivelAtividade.getFator();
    }
}