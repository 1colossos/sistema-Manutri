package service;


import model.Alimento;
import java.util.ArrayList;
import java.util.List;

public class BancoDeAlimentos {

    public static List<Alimento> getAlimentosPorObjetivo(String objetivo) {
        List<Alimento> lista = new ArrayList<>();

        // Carboidratos
        lista.add(new Alimento("Arroz Integral Cozido", 100, 2.6, 25.8, 1.0, 124));
        lista.add(new Alimento("Batata Doce Cozida", 100, 0.6, 18.4, 0.1, 77));
        lista.add(new Alimento("Aveia em Flocos", 30, 4.3, 17.0, 2.2, 104));
        lista.add(new Alimento("Pão Integral (2 fatias)", 50, 4.5, 22.0, 1.0, 120));

        // Proteínas
        lista.add(new Alimento("Peito de Frango Grelhado", 100, 32.0, 0.0, 2.5, 159));
        lista.add(new Alimento("Ovo Cozido (unidade)", 50, 6.0, 0.6, 5.0, 70));
        lista.add(new Alimento("Tilápia Assada", 100, 26.0, 0.0, 3.0, 128));
        lista.add(new Alimento("Carne Moída (Patinho)", 100, 35.0, 0.0, 7.0, 219));

        // Gorduras / Complementos
        lista.add(new Alimento("Abacate", 100, 1.2, 6.0, 8.4, 96));
        lista.add(new Alimento("Azeite de Oliva (1 colher)", 12, 0.0, 0.0, 12.0, 108));
        lista.add(new Alimento("Salada de Folhas", 100, 1.0, 2.0, 0.0, 15));

        if (objetivo.equalsIgnoreCase("PERDA")) {
            lista.removeIf(a -> a.getCalorias() > 200);
        }

        if (objetivo.equalsIgnoreCase("GANHO")) {
            lista.removeIf(a -> a.getCalorias() < 50);
        }

        return lista;
    }
}
