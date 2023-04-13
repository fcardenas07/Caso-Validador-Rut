import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void invertirCadena() {
        assertEquals("87654321", ValidadorRut.invertirCadena("12345678"));
    }

    @Test
    void multiplicarDigitosRut() {
        int[] esperado = {1 * 2, 2 * 3, 3 * 4, 4 * 5, 5 * 6, 6 * 7, 7 * 2, 8 * 3};
        assertArrayEquals(esperado, ValidadorRut.multiplicarDigitosRut("12345678"));
    }

    @Test
    void convertirCharAInt() {
    }

    @Test
    void quitarDigitoVerificador() {
        assertEquals("12345678", ValidadorRut.quitarDigitoVerificador("123456785"));
    }

    @Test
    void rutNoTieneFormatoValido() {
    }

    @Test
    void quitarPuntosYGuion() {
        assertEquals("123456785", ValidadorRut.quitarPuntosYGuion(rut));
    }
}