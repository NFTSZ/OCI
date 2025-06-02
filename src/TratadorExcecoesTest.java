import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TratadorExcecoesTest {

    // 1) validarNaoVazio
    @Test
    void validarNaoVaziodeveLancarParaValorNuloouVazio() {
        // null
        IllegalArgumentException ex1 = assertThrows(
                IllegalArgumentException.class, () -> TratadorExcecoes.validarNaoVazio("Nome",null));
        assertEquals("Nome não pode ser vazio.", ex1.getMessage());

        // string vazia
        IllegalArgumentException ex2 = assertThrows(
                IllegalArgumentException.class, () -> TratadorExcecoes.validarNaoVazio("Email", "   "));
        assertEquals("Email não pode ser vazio.", ex2.getMessage());
    }

    // 2) validarCPF
    @Test
    void validarCPFdeveAceitarCpfValidoERejeitarCpfInvalido() {
        // válido: 11 dígitos
        assertDoesNotThrow(() -> TratadorExcecoes.validarCPF("12345678901"));

        // inválido: menos de 11 dígitos
        IllegalArgumentException ex1 = assertThrows(
                IllegalArgumentException.class, () -> TratadorExcecoes.validarCPF("123"));
        assertEquals("CPF inválido. Deve conter exatamente 11 dígitos numéricos.", ex1.getMessage());

        // inválido: contém letra
        IllegalArgumentException ex2 = assertThrows(
                IllegalArgumentException.class, () -> TratadorExcecoes.validarCPF("12345678a01"));
        assertEquals("CPF inválido. Deve conter exatamente 11 dígitos numéricos.", ex2.getMessage());
    }

    // 3) validarData
    @Test
    void validarDatadeveRetornarLocalDateParaFormatoCorreto() {
        LocalDate data = TratadorExcecoes.validarData("2025-05-30");
        assertEquals(LocalDate.of(2025, 5, 30), data);
    }
    @Test
    void validarDatadeveLancarParaFormatoIncorreto() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class, () -> TratadorExcecoes.validarData("30/05/2025"));
        assertEquals("Data inválida. Use o formato AAAA-MM-DD.", ex.getMessage());
    }

    // 4) validarIndice
    @Test
    void validarIndicedeveAceitarDentroDoIntervaloERejeitarFora() {
        // assumindo tamanho = 5
        assertDoesNotThrow(() -> TratadorExcecoes.validarIndice(1, 5));
        assertDoesNotThrow(() -> TratadorExcecoes.validarIndice(5, 5));

        IllegalArgumentException ex1 = assertThrows(
                IllegalArgumentException.class, () -> TratadorExcecoes.validarIndice(0, 5));
        assertEquals("Índice inválido. Deve estar entre 1 e 5.", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(
                IllegalArgumentException.class, () -> TratadorExcecoes.validarIndice(6, 5));
        assertEquals("Índice inválido. Deve estar entre 1 e 5.", ex2.getMessage());
    }
}
