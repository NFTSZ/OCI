import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaOCI sistema = new SistemaOCI();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Sistema OCI ===");
            System.out.println("1. Cadastrar paciente");
            System.out.println("2. Listar pacientes");
            System.out.println("3. Detalhes do paciente");
            System.out.println("4. Editar paciente (v2.0)");
            System.out.println("5. Atualizar procedimentos (v2.0)");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    cadastrarPaciente(sistema, scanner);
                    break;
                case 2:
                    listarPacientes(sistema);
                    break;
                case 3:
                    detalhesPaciente(sistema, scanner);
                    break;
                case 4:
                    sistema.editarPaciente(0); // Placeholder
                    break;
                case 5:
                    sistema.atualizarProcedimentos(0); // Placeholder
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarPaciente(SistemaOCI sistema, Scanner scanner) {
        System.out.println("\nNovo Cadastro");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Agente de Saúde: ");
        String agente = scanner.nextLine();
        
        System.out.print("Nome da UBS: ");
        String ubs = scanner.nextLine();
        
        // OCI simplificada
        OCI oci = new OCI("09.01.01", "Avaliação Geral", "Geral", 
                         List.of("Consulta", "Exame básico"));
        
        System.out.print("Data de início (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        oci.setDataInicio(data);
        
        sistema.cadastrarPaciente(nome, cpf, telefone, agente, ubs, oci);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    private static void listarPacientes(SistemaOCI sistema) {
        List<Paciente> pacientes = sistema.listarPacientes();
        
        if (pacientes.isEmpty()) {
            System.out.println("\nNenhum paciente cadastrado.");
            return;
        }
        
        System.out.println("\nLista de Pacientes:");
        for (Paciente p : pacientes) {
            System.out.println(p.getId() + " - " + p.getNome() + " (" + p.getOci() + ")");
        }
    }

    private static void detalhesPaciente(SistemaOCI sistema, Scanner scanner) {
        System.out.print("\nDigite o ID do paciente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Paciente paciente = sistema.buscarPaciente(id);
        
        if (paciente != null) {
            paciente.mostrarDados();
        } else {
            System.out.println("Paciente não encontrado!");
        }
    }
}
