import java.util.Arrays;
import java.util.Scanner;

public class ValidadorRut {
    public static void main(String[] args) {
        String rut = ingresarTexto();

        if (rutNoTieneFormatoValido(rut)) {
            //throw new RuntimeException("Rut con formato no valido");
            System.err.print("Rut con formato no valido");
        }

        String digitoVerificadorCalculado = calcularDigitoVerificador(rut);
        String digitoVerificadorIngresado = extraerDigitoVerificador(rut);

        if (digitoVerificadorIngresado.equals(digitoVerificadorCalculado)) {
            System.out.println("Rut Valido");
        } else {
            System.out.println("Rut no valido");
        }

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
            throw new RuntimeException("El rut contiene caracteres no validos");
        }
    }

    public static String invertirCadena(String rut) {
        return new StringBuilder(rut).reverse().toString();
    }

    public static String quitarDigitoVerificador(String rut) {
        return rut.substring(0, rut.length() - 1);
    }

    public static boolean rutNoTieneFormatoValido(String rut) {
        rut = quitarPuntosYGuion(rut);
        return rut.length() < 8 || rut.length() > 9;
    }

    public static String quitarPuntosYGuion(String rut) {
        return rut.replaceAll("[.-]", "");
    }


    public static String ingresarTexto() {
        return new Scanner(System.in).next();
    }
}
