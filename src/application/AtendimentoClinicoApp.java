package application;

import model.*;
import service.BancoDeAlimentos;
import service.CalculadoraNutricional;
import service.PacienteService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AtendimentoClinicoApp {

    private static Scanner scanner = new Scanner(System.in);
    private static PacienteService service = new PacienteService();
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao = 0;
        System.out.println("=== SISTEMA MANUTRI: GESTÃO COMPLETA ===");

        do {
            exibirMenu();
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) continue;
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    cadastrarPaciente();
                    break;

                case 2:
                    listarPacientesCompleto();
                    break;

                case 3:
                    novaConsultaMetabolica();
                    break;

                case 4:
                    sugerirAlimentos();
                    break;

                case 5:
                    montarRefeicaoInterativa();
                    break;

                case 6:
                    excluirPaciente();
                    break;

                case 7:
                    gerenciarAnamnese();
                    break;

                case 0:
                    System.out.println("Encerrando Sistema...");
                    break;

                default:
                    System.out.println("❌ Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1 - Cadastrar Paciente");
        System.out.println("2 - Listar Pacientes");
        System.out.println("3 - Realizar Consulta");
        System.out.println("4 - Sugestão de Alimentos");
        System.out.println("5 - Calculadora de Refeição");
        System.out.println("6 - Excluir Paciente");
        System.out.println("7 - Anamnese (Registrar/Ver)");
        System.out.println("0 - Sair");
        System.out.print("Digite a opção: ");
    }

    private static void cadastrarPaciente() {
        System.out.println("\n>> NOVO CADASTRO");

        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        LocalDate dataNascimento;
        try {
            dataNascimento = LocalDate.parse(scanner.nextLine(), fmt);
        } catch (Exception e) {
            System.out.println("❌ Data inválida! Cadastro cancelado.");
            return;
        }

        System.out.print("Gênero (M - Masculino / F - Feminino): ");
        String generoStr = scanner.nextLine().toUpperCase();
        Genero genero = generoStr.startsWith("M") ? Genero.MASCULINO : Genero.FEMININO;

        Paciente novoPaciente = new Paciente(nome, cpf, dataNascimento, genero, email, telefone);
        service.cadastrar(novoPaciente);

        System.out.println("✅ Paciente cadastrado com sucesso!");
    }

    private static void listarPacientesCompleto() {
        System.out.println("\n>> RELATÓRIO GERAL DE PACIENTES");
        var lista = service.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        for (Paciente p : lista) {
            System.out.println("==================================================");
            System.out.println("FICHA: " + p.getNome().toUpperCase());
            System.out.println("--------------------------------------------------");
            System.out.println("CPF: " + p.getCpf());
            System.out.println("Idade: " + p.getIdade() + " anos | Gênero: " + p.getGenero());
            System.out.println("Contato: " + p.getTelefone() + " | " + p.getEmail());

            if (p.getHistoricoAvaliacoes().isEmpty()) {
                System.out.println("\n>> Histórico: Nenhuma consulta registrada.");
            } else {
                System.out.println("\n>> Histórico de Consultas (" + p.getHistoricoAvaliacoes().size() + "):");
                for (AvaliacaoAntropometrica av : p.getHistoricoAvaliacoes()) {
                    System.out.println("   - " + av.toString());
                }
            }
            System.out.println("==================================================\n");
        }
    }

    private static void novaConsultaMetabolica() {
        System.out.println("\n>> CONSULTA METABÓLICA");
        Paciente paciente = buscarPaciente();
        if (paciente == null) return;

        System.out.println("Paciente selecionado: " + paciente.getNome());

        System.out.print("Peso atual (kg): ");
        double peso = Double.parseDouble(scanner.nextLine());

        System.out.print("Altura (m): ");
        double altura = Double.parseDouble(scanner.nextLine());

        System.out.println("\nSelecione o Nível de Atividade Física:");
        System.out.println("1 - Sedentário (Pouco exercício)");
        System.out.println("2 - Leve (1-3 dias/semana)");
        System.out.println("3 - Moderado (3-5 dias/semana)");
        System.out.println("4 - Intenso (6-7 dias/semana)");
        System.out.print("Opção: ");

        int opAtv = Integer.parseInt(scanner.nextLine());
        NivelAtividade nivelAtividade;
        switch (opAtv) {
            case 1:
                nivelAtividade = NivelAtividade.SEDENTARIO;
                break;
            case 2:
                nivelAtividade = NivelAtividade.LEVE;
                break;
            case 4:
                nivelAtividade = NivelAtividade.INTENSO;
                break;
            default:
                nivelAtividade = NivelAtividade.MODERADO;
                break;
        }

        AvaliacaoAntropometrica av = new AvaliacaoAntropometrica(peso, altura);
        paciente.registrarAvaliacao(av);

        double tmb = CalculadoraNutricional.calcularTMB(peso, altura, paciente.getIdade(), paciente.getGenero());
        double get = CalculadoraNutricional.calcularGET(tmb, nivelAtividade);

        System.out.println("\n--------------------------------------------------");
        System.out.println("RESULTADO CLÍNICO");
        System.out.println("--------------------------------------------------");
        System.out.println("IMC: " + String.format("%.2f", av.calcularIMC()) + " (" + av.getDiagnosticoIMC() + ")");
        System.out.println("TMB (Basal): " + String.format("%.0f", tmb) + " Kcal (Gasto em repouso)");
        System.out.println("GET (Total): " + String.format("%.0f", get) + " Kcal (Meta para manutenção)");
        System.out.println("--------------------------------------------------");
        System.out.println("✅ Consulta salva no histórico!");
        pausa();
    }

    private static void sugerirAlimentos() {
        System.out.println("\n>> SUGESTÃO INTELIGENTE DE ALIMENTOS");
        Paciente paciente = buscarPaciente();
        if (paciente == null) return;

        List<AvaliacaoAntropometrica> historico = paciente.getHistoricoAvaliacoes();
        if (historico.isEmpty()) {
            System.out.println("⚠️ Necessário realizar consulta (Opção 3) antes.");
            return;
        }

        AvaliacaoAntropometrica ultimaAv = historico.get(historico.size() - 1);
        double imc = ultimaAv.calcularIMC();
        String sugestaoSistema;

        if (imc >= 25.0) sugestaoSistema = "PERDA";
        else if (imc < 18.5) sugestaoSistema = "GANHO";
        else sugestaoSistema = "MANUTENCAO";

        System.out.println("-------------------------------------------------------");
        System.out.println("Paciente: " + paciente.getNome());
        System.out.println("IMC Atual: " + String.format("%.2f", imc));
        System.out.println("Sugestão do Sistema: " + sugestaoSistema + " (Baseado no IMC)");
        System.out.println("-------------------------------------------------------");

        System.out.println("Qual o objetivo ATUAL do paciente?");
        System.out.println("1 - PERDA de Peso (Definição/Emagrecimento)");
        System.out.println("2 - GANHO de Massa (Hipertrofia)");
        System.out.println("3 - MANUTENCAO (Reeducação Alimentar)");
        System.out.print("Escolha (ou Pressione ENTER para aceitar a sugestão '" + sugestaoSistema + "'): ");

        String entrada = scanner.nextLine();
        String objetivoFinal = sugestaoSistema;

        if (entrada.equals("1")) objetivoFinal = "PERDA";
        else if (entrada.equals("2")) objetivoFinal = "GANHO";
        else if (entrada.equals("3")) objetivoFinal = "MANUTENCAO";

        System.out.println("\n>> Gerando cardápio com foco em: " + objetivoFinal);

        List<Alimento> sugestoes = BancoDeAlimentos.getAlimentosPorObjetivo(objetivoFinal);

        System.out.println("\n--- LISTA DE ALIMENTOS SUGERIDA ---");
        System.out.println(String.format("%-25s | %-6s | %-8s | %-5s | %-5s | %-5s",
                "ALIMENTO", "PORÇÃO", "KCAL", "PROT", "CARB", "GORD"));
        System.out.println("-----------------------------------------------------------------------");

        for (Alimento a : sugestoes) {
            System.out.println(String.format("%-25s | %-5.0fg | %-5.0fCal | %-5.1f | %-5.1f | %-5.1f",
                    a.getNome(),
                    a.getPorcaoGramas(),
                    a.getCalorias(),
                    a.getProteina(),
                    a.getCarboidrato(),
                    a.getGordura()
            ));
        }
        System.out.println("-----------------------------------------------------------------------");
        pausa();
    }

    private static void montarRefeicaoInterativa() {
        System.out.println("\n>> CALCULADORA DE REFEIÇÃO (MACROS COMPLETOS)");
        System.out.print("Nome do Prato (ex: Pós-Treino): ");
        String nomePrato = scanner.nextLine();

        Refeicao refeicao = new Refeicao(nomePrato);
        int op = 0;

        do {
            System.out.println("\n-- Editando: " + nomePrato + " --");
            System.out.println("1 - Escolher do Banco de Dados");
            System.out.println("2 - Adicionar Manualmente");
            System.out.println("0 - Finalizar e Ver Totais");
            System.out.print("Opção: ");

            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                op = -1;
            }

            if (op == 1) {
                List<Alimento> lista = BancoDeAlimentos.getAlimentosPorObjetivo("MANUTENCAO");
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println((i + 1) + ". " + lista.get(i).getNome());
                }
                System.out.print("Número do alimento: ");
                try {
                    int escolha = Integer.parseInt(scanner.nextLine());
                    if (escolha > 0 && escolha <= lista.size()) {
                        refeicao.adicionarAlimento(lista.get(escolha - 1));
                        System.out.println("✅ Adicionado!");
                    }
                } catch (Exception e) {
                    System.out.println("❌ Inválido.");
                }

            } else if (op == 2) {
                System.out.print("Nome: ");
                String n = scanner.nextLine();
                System.out.print("Calorias: ");
                double c = Double.parseDouble(scanner.nextLine());
                System.out.print("Proteínas: ");
                double p = Double.parseDouble(scanner.nextLine());
                System.out.print("Carboidratos: ");
                double carb = Double.parseDouble(scanner.nextLine());
                System.out.print("Gorduras: ");
                double g = Double.parseDouble(scanner.nextLine());

                refeicao.adicionarAlimento(new Alimento(n, 100, p, carb, g, c));
                System.out.println("✅ Adicionado!");
            }

        } while (op != 0);

        System.out.println("\n=======================================================================");
        System.out.println("RESUMO: " + refeicao.getNome().toUpperCase());
        System.out.println("=======================================================================");
        System.out.println(String.format("%-25s | %-8s | %-5s | %-5s | %-5s", "ITEM", "KCAL", "PROT", "CARB", "GORD"));
        System.out.println("-----------------------------------------------------------------------");

        for (Alimento a : refeicao.getAlimentos()) {
            System.out.println(String.format("%-25s | %-5.0fCal  | %-5.1f | %-5.1f | %-5.1f",
                    a.getNome(), a.getCalorias(), a.getProteina(), a.getCarboidrato(), a.getGordura()));
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("TOTAL CALORIAS:     " + String.format("%.0f", refeicao.calcularTotalCalorias()) + " Kcal");
        System.out.println("TOTAL MACROS:       " +
                "P: " + String.format("%.1f", refeicao.calcularTotalProteina()) + "g | " +
                "C: " + String.format("%.1f", refeicao.calcularTotalCarboidrato()) + "g | " +
                "G: " + String.format("%.1f", refeicao.calcularTotalGordura()) + "g");
        System.out.println("=======================================================================");
        pausa();
    }

    private static Paciente buscarPaciente() {
        System.out.print("Digite o CPF ou Email do paciente: ");
        String termo = scanner.nextLine();
        for (Paciente p : service.listarTodos()) {
            if (p.getCpf().equals(termo) || p.getEmail().equalsIgnoreCase(termo)) {
                return p;
            }
        }
        System.out.println("❌ Paciente não encontrado!");
        return null;
    }

    private static void pausa() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void excluirPaciente() {
        System.out.println("\n>> EXCLUSÃO DE REGISTRO");
        System.out.print("Digite o CPF do paciente que deseja remover: ");
        String cpf = scanner.nextLine();

        Paciente p = null;
        for (Paciente paciente : service.listarTodos()) {
            if (paciente.getCpf().equals(cpf)) {
                p = paciente;
                break;
            }
        }

        if (p == null) {
            System.out.println("❌ Paciente não encontrado com este CPF.");
            return;
        }

        System.out.println("⚠️ ATENÇÃO: Você está prestes a excluir: " + p.getNome());
        System.out.println("Todo o histórico de consultas será perdido.");
        System.out.print("Tem certeza? Digite 'SIM' para confirmar: ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("SIM")) {
            boolean removeu = service.remover(cpf);
            if (removeu) {
                System.out.println("✅ Paciente removido com sucesso!");
            } else {
                System.out.println("❌ Erro ao remover.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
        pausa();
    }

    private static void gerenciarAnamnese() {
        System.out.println("\n>> GESTÃO DE ANAMNESE");

        Paciente paciente = buscarPaciente();

        if (paciente == null) return;

        System.out.println("Paciente: " + paciente.getNome());

        if (paciente.getAnamnese() != null) {
            System.out.println("\n✅ Este paciente JÁ possui anamnese registrada:");
            System.out.println(paciente.getAnamnese().toString());
            System.out.print("\nDeseja SOBRESCREVER (refazer) a anamnese? (S/N): ");
            String resp = scanner.nextLine();
            if (!resp.equalsIgnoreCase("S")) {
                return;
            }
        }

        System.out.println("\n--- Iniciando Entrevista ---");

        System.out.print("1. Qual a Queixa Principal (motivo da consulta)? ");
        String queixa = scanner.nextLine();

        System.out.print("2. Histórico Patológico (Doenças, cirurgias, alergias): ");
        String histPatologico = scanner.nextLine();

        System.out.print("3. Histórico Familiar (Pai/Mãe com diabetes, hipertensão?): ");
        String histFamiliar = scanner.nextLine();

        System.out.print("4. Fuma? (S/N): ");
        boolean fuma = scanner.nextLine().trim().toUpperCase().startsWith("S");

        System.out.print("5. Consome bebida alcoólica? (S/N): ");
        boolean alcool = scanner.nextLine().equalsIgnoreCase("S");

        System.out.print("6. Pratica exercícios físicos regularmente? (S/N): ");
        boolean exercicio = scanner.nextLine().equalsIgnoreCase("S");

        System.out.print("7. Resumo da Rotina Diária (Acorda, trabalha, dorme...): ");
        String rotina = scanner.nextLine();

        Anamnese novaAnamnese = new Anamnese(queixa, histPatologico, fuma, alcool, exercicio, rotina);
        novaAnamnese.setHistoricoFamiliar(histFamiliar);

        paciente.setAnamnese(novaAnamnese);

        System.out.println("\n✅ Anamnese registrada com sucesso!");
        pausa();
    }
}
