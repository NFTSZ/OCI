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
        // os pazos de Ocncologia sao menores, essa linha calcula automaticamente. Para oncologia = 30, para os demais = 60
        int dias = tipo.equalsIgnoreCase("Oncologia") ? 30 : 60;
        // adiciona os dias (30 ou 60) a partir da data atual (dataInicio)
        this.dataLimite = data.plusDays(dias);
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCodigo() {
        return codigo;
    }
    public String getTipo() {
        return tipo;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public LocalDate getDataLimite() {
        return dataLimite;
    }
    public List<String> getProcedimentos() {
        return procedimentos;
    }
    public String getStatus() {
        if (LocalDate.now().isAfter(dataLimite)) {
            return "Atrasado";
        }
        return "Em andamento";
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
