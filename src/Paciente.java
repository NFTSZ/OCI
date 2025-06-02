import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private static int proximoId = 1;
    
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String agenteSaude;
    private String nomeUBS;
    private OCI oci;
    private LocalDate dataCadastro;

    public Paciente(String nome, String cpf, String telefone, 
                  String agenteSaude, String nomeUBS, OCI oci) {
        this.id = proximoId++;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.agenteSaude = agenteSaude;
        this.nomeUBS = nomeUBS;
        this.oci = oci;
        this.dataCadastro = LocalDate.now();
    }

    // Getters básicos
    public int getId() { return id; }
    public String getNome() { return nome; }
    public OCI getOci() { return oci; }
    
    public void mostrarDados() {
        System.out.println("\n--- Dados do Paciente ---");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("UBS: " + nomeUBS);
        System.out.println("Agente: " + agenteSaude);
        System.out.println("Telefone: " + telefone);
        oci.mostrarDados();
    }
}
