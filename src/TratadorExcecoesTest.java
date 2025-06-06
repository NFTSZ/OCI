import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TratadorExcecoesTest {

    // ---- Testes para datas válidas ----
    @Test
    public void testValidarData_FormatoCorreto_DeveRetornarData() {
        String dataValida = "2023-12-31";
        LocalDate data = TratadorExcecoes.validarData(dataValida);
        assertEquals(LocalDate.of(2023, 12, 31), data);
    }

    // ---- Testes para datas inválidas ----
    @Test
    public void testValidarData_FormatoIncorreto_DeveLancarExcecao() {
        String dataInvalida = "31/12/2023";
        try {
            TratadorExcecoes.validarData(dataInvalida);
            fail("Deveria ter lançado IllegalArgumentException!");
        } catch (IllegalArgumentException e) {
            assertEquals("Data inválida. Use o formato AAAA-MM-DD (ex: 2023-12-31).", e.getMessage());
        }
    }

    @Test
    public void testValidarData_DataVazia_DeveLancarExcecao() {
        String dataVazia = "";
        try {
            TratadorExcecoes.validarData(dataVazia);
            fail("Deveria ter lançado IllegalArgumentException!");
        } catch (IllegalArgumentException e) {
            assertEquals("Data não pode ser vazia ou nula.", e.getMessage());
        }
    }

    @Test
    public void testValidarData_DataNula_DeveLancarExcecao() {
        try {
            TratadorExcecoes.validarData(null);
            fail("Deveria ter lançado IllegalArgumentException!");
        } catch (IllegalArgumentException e) {
            assertEquals("Data não pode ser vazia ou nula.", e.getMessage());
        }
    }

    @Test
    public void testValidarData_DataInexistente_DeveLancarExcecao() {
        String dataInexistente = "2023-02-30"; // 30 de fevereiro não existe
        try {
            TratadorExcecoes.validarData(dataInexistente);
            fail("Deveria ter lançado IllegalArgumentException!");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().startsWith("Data inválida."));
        }
    }
}