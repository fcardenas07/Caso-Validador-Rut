import java.util.Arrays;
import java.util.Scanner;

public class ValidadorRut {
    public static void main(String[] args) {
        String rut = ingresarRut();
        validarLargo(rut);
        mostrarResultadoValidacion(rut);
    }

    private static void validarLargo(String rut) {
        if (rut.isBlank()) {
            System.err.println("El rut esta vacio");
            System.exit(0);
        }
    }

    private static void mostrarResultadoValidacion(String rut) {
        System.out.println(resultadoValidacion(rut));
    }

    private static String resultadoValidacion(String rut) {
        return "El rut %s valido".formatted(
                esRutValido(calcularDigitoVerificador(rut), extraerDigitoVerificador(rut)) ? "es" : "no es"
        );
    }

    private static boolean esRutValido(String digitoVerificadorCalculado, String digitoVerificadorIngresado) {
        return digitoVerificadorIngresado.equalsIgnoreCase(digitoVerificadorCalculado);
    }

    public static String extraerDigitoVerificador(String rut) {
        return rut.substring(rut.length() - 1);
    }

    public static String calcularDigitoVerificador(String rut) {
        rut = quitarPuntosYGuion(rut);
        rut = quitarDigitoVerificador(rut);
        rut = invertirCadena(rut);

        int[] multiplicacionDigitos = multiplicarDigitosRut(rut);
        int sumaMultiplicaciones = sumarMultiplicaciones(multiplicacionDigitos);

        double division = dividirSuma(sumaMultiplicaciones);
        int divisionSinDecimales = quitarDecimales(division);
        int multiplicacion = multiplicarDivision(divisionSinDecimales);
        int restaPaso7 = restar(sumaMultiplicaciones, multiplicacion);
        int restaPaso8 = restar(11, restaPaso7);

        return asignarDigitoVerificador(restaPaso8);
    }

    public static String asignarDigitoVerificador(int restaPaso8) {
        if (restaPaso8 == 10) return "k";
        if (restaPaso8 == 11) return "0";
        return String.valueOf(restaPaso8);
    }

    public static int restar(int numero1, int numero2) {
        return numero1 - numero2;
    }

    public static int multiplicarDivision(int division) {
        return division * 11;
    }

    public static int quitarDecimales(double division) {
        return (int) division;
    }

    public static double dividirSuma(int sumaMultiplicaciones) {
        return (double) sumaMultiplicaciones / 11;
    }

    public static int sumarMultiplicaciones(int[] multiplicacionDigitos) {
        return Arrays.stream(multiplicacionDigitos).sum();
    }

    public static int[] multiplicarDigitosRut(String rut) {
        int[] multiplicaciones = new int[rut.length()];
        int factorMultiplicacion = 2;

        for (int i = 0; i < rut.length(); i++) {
            int digito = convertirCharAInt(rut.charAt(i));
            multiplicaciones[i] = digito * factorMultiplicacion;
            factorMultiplicacion++;
            if (factorMultiplicacion == 8) factorMultiplicacion = 2;
        }
        return multiplicaciones;
    }

    public static int convertirCharAInt(char digito) {
        try {
            return Integer.parseInt(String.valueOf(digito));
        } catch (NumberFormatException e) {
            System.err.println("El rut contiene caracteres no validos");
            System.exit(0);
        }
        return 0;
    }

    public static String invertirCadena(String rut) {
        return new StringBuilder(rut).reverse().toString();
    }

    public static String quitarDigitoVerificador(String rut) {
        return rut.substring(0, rut.length() - 1);
    }

    public static String quitarPuntosYGuion(String rut) {
        return rut.replaceAll("[.-]", "");
    }

    public static String ingresarRut() {
        System.out.println("Ingrese su RUT:");
        return new Scanner(System.in).nextLine();
    }
}
