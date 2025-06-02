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
            System.out.println("4. Editar paciente (Nao implementada)");
            System.out.println("5. Atualizar procedimentos (Nao implementado)");
            System.out.println("6. Cadastrar nova OCI (Nao implementado)");
            System.out.println("7. Listar todas as OCIs disponíveis (Nao implementado)");
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
                case 6:
                    sistema.adicionarOCI(0); // Placeholder
                    break;
                case 7:
                    sistema.listarOCI(0); // Placeholder
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
        TratadorExcecoes.validarNaoVazio("Nome", nome);
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        TratadorExcecoes.validarCPF(cpf);
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        TratadorExcecoes.validarNaoVazio("Telefone pessoal", telefone);

        System.out.print("Agente de Saúde: ");
        String agente = scanner.nextLine();
        TratadorExcecoes.validarNaoVazio("Nome do agente de saúde", agente);
        
        System.out.print("Nome da UBS: ");
        String ubs = scanner.nextLine();
        TratadorExcecoes.validarNaoVazio("Nome da UBS", ubs);

        System.out.print("Telefone da UBS: ");
        String telefoneUBS = scanner.nextLine();
        TratadorExcecoes.validarNaoVazio("Telefone da UBS", telefoneUBS);
        
        // OCI para teste
        OCI oci = new OCI("09.01.01.001-4", "Avaliação Geral", "Geral",
                         List.of("Consulta", "Exame básico"));
        
        System.out.print("Data de início (AAAA-MM-DD): ");
        String dataInicioStr = scanner.nextLine();
        LocalDate dataInicio = TratadorExcecoes.validarData(dataInicioStr);
        oci.setDataInicio(LocalDate.parse(dataInicioStr));
        
        sistema.cadastrarPaciente(nome, cpf, telefone, agente, ubs, telefoneUBS, oci);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    private static void listarPacientes(SistemaOCI sistema) {
        List<Paciente> pacientes = sistema.listarPacientes();

        // se a lista estiver vazia, retornar informacao
        if (pacientes.isEmpty()) {
            System.out.println("\nNenhum paciente cadastrado.");
            return;
        }
        // se nao estiver vazia, liste os pacientes
        System.out.println("\nLista de Pacientes:");
        for (Paciente p : pacientes) {
            System.out.println(p.getId() + " - " + p.getNome() + " (" + p.getOci().getDescricao() + ")");
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
