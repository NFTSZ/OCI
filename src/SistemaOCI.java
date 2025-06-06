import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaOCI {
    // Lista em que os pacientes cadastrados serao adicionados
    private List<Paciente> pacientes = new ArrayList<>();

    public boolean cadastrarPaciente(String nome, String cpf, String telefone, String agenteSaude, String nomeUBS, String telefoneUBS, OCI oci) {
        // Validação básica dos campos obrigatórios
        if (nome == null || nome.trim().isEmpty() || cpf == null || cpf.trim().isEmpty() || oci == null || telefone == null || telefone.trim().isEmpty() || agenteSaude == null || agenteSaude.trim().isEmpty() || telefoneUBS.trim().isEmpty()) {

            return false;
        }

        // Cria e adiciona o paciente
        Paciente paciente = new Paciente(nome, cpf, telefone, agenteSaude, nomeUBS, telefoneUBS, oci);
        return pacientes.add(paciente);
    }

    // Listagem de todos os paciente cadastrados
    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes);
    }

    // busca o paciente de acordo com o id
    public Paciente buscarPaciente(int id) {
        for (Paciente p : pacientes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Métodos que serão implementados futuramente

    // edita os dados do paciente
    public void editarPaciente(int id) {
        System.out.println("Funcionalidade ainda nao implementada");
    }

    // Atualiza os exames requeridos pela OCI que ja foram feitos
    public void atualizarProcedimentos(int id) {
        System.out.println("Funcionalidade ainda nao implementada");
    }

    // adicionar uma nova OCI ao isstema, caso o governo "lance"
    public void adicionarOCI(int id) {
        System.out.println("Funcionalidade ainda nao implementada");
    }

    // listar todas as oci's disponiveis (nao necessario agora pois so havera 1 OCI de exemplo)
    public void listarOCI(int id) {
        System.out.println("Funcionalidade ainda nao implementada");
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
}
