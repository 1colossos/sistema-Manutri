package model;

public class Anamnese {

    private String queixaPrincipal;
    private String historicoPatologico;
    private String historicoFamiliar;
    private boolean fuma;
    private boolean consomeAlcool;
    private boolean praticaExercicios;

    private String observacoesRotina;

    public Anamnese(String queixaPrincipal, String historicoPatologico,
                    boolean fuma, boolean consomeAlcool, boolean praticaExercicios,
                    String observacoesRotina) {
        this.queixaPrincipal = queixaPrincipal;
        this.historicoPatologico = historicoPatologico;
        this.fuma = fuma;
        this.consomeAlcool = consomeAlcool;
        this.praticaExercicios = praticaExercicios;
        this.observacoesRotina = observacoesRotina;
    }

    public String getQueixaPrincipal() { return queixaPrincipal; }
    public void setQueixaPrincipal(String queixaPrincipal) { this.queixaPrincipal = queixaPrincipal; }

    public String getHistoricoPatologico() { return historicoPatologico; }
    public void setHistoricoPatologico(String historicoPatologico) { this.historicoPatologico = historicoPatologico; }

    public String getHistoricoFamiliar() { return historicoFamiliar; }
    public void setHistoricoFamiliar(String historicoFamiliar) { this.historicoFamiliar = historicoFamiliar; }

    public boolean isFuma() { return fuma; }
    public void setFuma(boolean fuma) { this.fuma = fuma; }

    public boolean isConsomeAlcool() { return consomeAlcool; }
    public void setConsomeAlcool(boolean consomeAlcool) { this.consomeAlcool = consomeAlcool; }

    public boolean isPraticaExercicios() { return praticaExercicios; }
    public void setPraticaExercicios(boolean praticaExercicios) { this.praticaExercicios = praticaExercicios; }

    public String getObservacoesRotina() { return observacoesRotina; }
    public void setObservacoesRotina(String observacoesRotina) { this.observacoesRotina = observacoesRotina; }

    @Override
    public String toString() {
        return "--- FICHA DE ANAMNESE ---\n" +
                "Queixa Principal: " + queixaPrincipal + "\n" +
                "Histórico Patológico: " + historicoPatologico + "\n" +
                "Histórico Familiar: " + (historicoFamiliar != null ? historicoFamiliar : "Não informado") + "\n" +
                "--- ESTILO DE VIDA ---\n" +
                "Fumante: " + (fuma ? "Sim" : "Não") + " | Álcool: " + (consomeAlcool ? "Sim" : "Não") + "\n" +
                "Exercícios: " + (praticaExercicios ? "Sim" : "Não") + "\n" +
                "Rotina: " + observacoesRotina;
    }
}