import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.*;

public class TesteValidacaoData {

    // Teste para data válida (formato yyyy-MM-dd)
    @Test
    public void testDataValida_FormatoCorreto() {
        String dataValida = "2023-12-31";
        try {
            LocalDate data = LocalDate.parse(dataValida);
            assertNotNull(data); // Só para confirmar que a data foi criada.
        } catch (DateTimeParseException e) {
            fail("Data válida não deveria lançar exceção!");
        }
    }

    // Teste para data com formato errado (dd/MM/yyyy)
    @Test
    public void testDataInvalida_FormatoErrado() {
        String dataInvalida = "31/12/2023";
        try {
            LocalDate.parse(dataInvalida);
            fail("Data inválida deveria lançar exceção!");
        } catch (DateTimeParseException e) {
            // Teste passou (lançou a exceção esperada)
        }
    }

    // Teste para campo vazio.
    @Test
    public void testDataVazia() {
        String dataVazia = "";
        try {
            LocalDate.parse(dataVazia);
            fail("Data vazia deveria lançar exceção!");
        } catch (DateTimeParseException e) {
            // Teste passou
        }
    }

    // Teste para data inexistente (30 de fevereiro)
    @Test
    public void testDataInexistente() {
        String dataInexistente = "2023-02-30";
        try {
            LocalDate.parse(dataInexistente);
            fail("Data inexistente deveria lançar exceção!");
        } catch (DateTimeParseException e) {
            // Teste passou
        }
    }
}