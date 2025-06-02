import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaOCI {
    private List<Paciente> pacientes = new ArrayList<>();

    public void cadastrarPaciente(String nome, String cpf, String telefone, 
                                String agenteSaude, String nomeUBS, 
                                OCI oci) {
        Paciente paciente = new Paciente(nome, cpf, telefone, agenteSaude, nomeUBS, oci);
        pacientes.add(paciente);
    }

    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes);
    }

    public Paciente buscarPaciente(int id) {
        for (Paciente p : pacientes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Métodos que serão implementados futuramente
    public void editarPaciente(int id) {
        System.out.println("Funcionalidade de edição será implementada na versão 2.0");
    }

    public void atualizarProcedimentos(int id) {
        System.out.println("Funcionalidade de procedimentos será implementada na versão 2.0");
    }
}
