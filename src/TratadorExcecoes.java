import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TratadorExcecoes {

    public static void validarNaoVazio(String nomeCampo, String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nomeCampo + " não pode ser vazio.");
        }
    }

    public static void validarCPF(String cpf) {
        validarNaoVazio("CPF", cpf);
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
        }
    }


    //Pedaço do codigo refatorado.
    public static LocalDate validarData(String dataStr) {
        if (dataStr == null || dataStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Data não pode ser vazia ou nula.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dataStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida. Use o formato AAAA-MM-DD (ex: 2023-12-31).");
        }
    }
}
