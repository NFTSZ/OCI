import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TratadorExcecoes {

    public static void validarNaoVazio(String nomeCampo, String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nomeCampo + " não pode ser vazio.");
        }
    }


    //Pedaço do codigo refatorado.
    public static void validarCPF(String cpf) {
        // Validação básica (nulo/vazio)
        validarNaoVazio("CPF", cpf);

        // Remove caracteres não numéricos (opcional, mas útil para aceitar formatos como "123.456.789-09")
        String cpfNumerico = cpf.replaceAll("[^0-9]", "");

        // Validação do comprimento e dígitos
        if (cpfNumerico.length() != 11 || !cpfNumerico.matches("\\d{11}")) {
            throw new IllegalArgumentException(
                    "CPF inválido. Deve conter 11 dígitos numéricos.\n" +
                            "Exemplo: 12345678901 ou 123.456.789-01"
            );
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
