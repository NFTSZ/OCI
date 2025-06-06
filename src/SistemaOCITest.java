import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaOCITest {
    SistemaOCI sistema = new SistemaOCI();
    OCI oci = new OCI();

    @Test
    public void testCadastrarPaciente_Sucesso() {
        boolean resultado = sistema.cadastrarPaciente("João", "123.456.789-00", "1199999-9900", "Maria", "UBS Centro", "112222-2222", oci);
        assertTrue(resultado);
        assertEquals(1, sistema.getPacientes().size());
    }

    @Test
    public void testCadastrarPaciente_FalhaCamposObrigatorios() {

        // Testa sem nome
        assertFalse(sistema.cadastrarPaciente("", "123.456.789-00", "1199999-1111", "Luiza", "USB Leste", "112222-4444", new OCI()));

        // Testa sem CPF
        assertFalse(sistema.cadastrarPaciente("João", "", "7788996-1111", "Marcos", "UBS Sul", "662222-4444", new OCI()));

        // Testa sem OCI
        assertFalse(sistema.cadastrarPaciente("João", "123.456.789-00", "2244552-1111", "Lopes", "UBS Norte", "2223335-4444", null));
    }

        @Test
        public void testListarPacientes_DeveConterPacienteCadastrado() {
            String nomeTeste = "João Silva";
            String cpfTeste = "12345678901";

            sistema.cadastrarPaciente(nomeTeste, cpfTeste, "11999999999", "Agente X", "UBS Centro", "1122222222", new OCI());

            // Assert (Verificação):
            List<Paciente> lista = sistema.listarPacientes();
            boolean encontrou = false;

            // Busca
            for (Paciente paciente : lista) {
                if (paciente.getNome().equals(nomeTeste) && paciente.getCpf().equals(cpfTeste)) {
                    encontrou = true;
                    break;
                }
            }

            assertTrue(encontrou, "O paciente cadastrado deveria estar na lista!");
        }

    @Test
    public void testBuscarPaciente_DeveRetornarPacienteCorreto() {
        sistema.cadastrarPaciente("Maria Souza", "98765432100", "11988888888", "Agente Y", "UBS Bairro", "1133333333", new OCI());

        // Busca pelo ID.
        Paciente paciente = sistema.buscarPaciente(1);


        assertNotNull(paciente, "Paciente não deveria ser nulo!");
        assertEquals("Maria Souza", paciente.getNome());
        assertEquals("98765432100", paciente.getCpf());
    }
}