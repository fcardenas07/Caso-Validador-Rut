import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorRutTest {
    private String rut;

    @BeforeEach
    void setUp() {
        rut = "12.345.678-5";
    }

    @Test
    void extraerDigitoVerificador() {
        assertEquals("5", ValidadorRut.extraerDigitoVerificador(rut));
    }

    @Test
    void calcularDigitoVerificador() {
        assertEquals("5", ValidadorRut.calcularDigitoVerificador(rut));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "15.336.785-K",
            "20080984-K",
            "19889737K"
    })
    void digitoVerificadorK(String rut) {
        assertEquals("k", ValidadorRut.calcularDigitoVerificador(rut));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1.941.669-0",
            "9714602-0",
            "196396810"
    })
    void digitoVerificadorCero(String rut) {
        assertEquals("0", ValidadorRut.calcularDigitoVerificador(rut));
    }

    @Test
    void invertirCadena() {
        assertEquals("87654321", ValidadorRut.invertirCadena("12345678"));
    }

    @Test
    void multiplicarDigitosRut() {
        int[] esperado = {2, 6, 12, 20, 30, 42, 14, 24};
        assertArrayEquals(esperado, ValidadorRut.multiplicarDigitosRut("12345678"));
    }

    @Test
    void quitarDigitoVerificador() {
        assertEquals("12345678", ValidadorRut.quitarDigitoVerificador("123456785"));
    }

    @Test
    void quitarPuntosYGuion() {
        assertEquals("123456785", ValidadorRut.quitarPuntosYGuion(rut));
    }
}