package service;


import model.Paciente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteService {

    private List<Paciente> listaPacientes;

    public PacienteService() {
        this.listaPacientes = new ArrayList<>();
    }

    public void cadastrar(Paciente paciente) {

        listaPacientes.add(paciente);
        System.out.println("Paciente " + paciente.getNome() + " cadastrado com sucesso!");
    }


    public List<Paciente> listarTodos() {
        return listaPacientes;
    }

    public Paciente buscarPorEmail(String email) {
        return listaPacientes.stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public boolean remover(String cpf) {
        return listaPacientes.removeIf(p -> p.getCpf().equals(cpf));
    }
}
