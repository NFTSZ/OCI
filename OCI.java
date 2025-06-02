import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OCI {
    private String codigo;
    private String descricao;
    private String tipo;
    private LocalDate dataInicio;
    private LocalDate dataLimite;
    private List<String> procedimentos;

    public OCI(String codigo, String descricao, String tipo, List<String> procedimentos) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.procedimentos = new ArrayList<>(procedimentos);
    }

    public void setDataInicio(LocalDate data) {
        this.dataInicio = data;
        int dias = tipo.equalsIgnoreCase("Oncologia") ? 30 : 60;
        this.dataLimite = data.plusDays(dias);
    }

    public void mostrarDados() {
        System.out.println("\n--- Dados da OCI ---");
        System.out.println("Código: " + codigo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Tipo: " + tipo);
        System.out.println("Data Limite: " + dataLimite);
        System.out.println("Procedimentos:");
        for (String proc : procedimentos) {
            System.out.println("- " + proc);
        }
    }
}
