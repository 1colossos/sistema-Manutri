package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Paciente {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Genero genero;
    private String email;
    private String telefone;
    private Anamnese anamnese;

    private List<AvaliacaoAntropometrica> historicoAvaliacoes;

    public Paciente(String nome, String cpf, LocalDate dataNascimento, Genero genero, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.email = email;
        this.telefone = telefone;
        this.historicoAvaliacoes = new ArrayList<>();
    }

    public void registrarAvaliacao(AvaliacaoAntropometrica avaliacao) {
        this.historicoAvaliacoes.add(avaliacao);
    }

    public int getIdade() {
        if (dataNascimento == null) return 0;
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
    public List<AvaliacaoAntropometrica> getHistoricoAvaliacoes() {
        return historicoAvaliacoes;
    }

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }

    public Anamnese getAnamnese() {
        return anamnese;
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}