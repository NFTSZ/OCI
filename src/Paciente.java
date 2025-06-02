import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
    // id para a identificacao do paciente, simplifica a pesquisa
    private static int proximoId = 1;
    
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String agenteSaude;
    private String nomeUBS;
    private String telefoneUBS;
    private OCI oci;
    private LocalDate dataCadastro;

    public Paciente(String nome, String cpf, String telefone, String agenteSaude, String nomeUBS, String telefoneUBS, OCI oci) {
        this.id = proximoId++;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.agenteSaude = agenteSaude;
        this.nomeUBS = nomeUBS;
        this.telefoneUBS = telefoneUBS;
        this.oci = oci;
        this.dataCadastro = LocalDate.now();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public OCI getOci() {
        return oci;
    }

    // mostra os dados completos do paciente
    public void mostrarDados() {
        System.out.println("\n--- Dados do Paciente ---");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("UBS: " + nomeUBS);
        System.out.println("Agente: " + agenteSaude);
        System.out.println("Telefone: " + telefone);
        System.out.println("Telefone da UBS: " + telefoneUBS);
        oci.mostrarDados();
    }
}
