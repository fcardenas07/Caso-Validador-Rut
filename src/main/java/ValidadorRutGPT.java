import java.util.Scanner;

public class ValidadorRutGPT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rut = "";
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Ingrese su RUT: ");
                rut = scanner.nextLine();
                rut = rut.replaceAll("\\.|\\-", "");
                validateRutFormat(rut);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        if (validateRutDigit(rut)) {
            System.out.println("El RUT es válido");
        } else {
            System.out.println("El RUT es inválido");
        }
    }

    public static void validateRutFormat(String rut) {
        if (!rut.matches("[0-9]+")) {
            throw new IllegalArgumentException("El RUT debe contener solo números");
        }
    }

    public static boolean validateRutDigit(String rut) {
        char expectedDV = calculateExpectedDV(rut);
        char actualDV = rut.charAt(rut.length() - 1);
        return (actualDV == expectedDV);
    }

    public static char calculateExpectedDV(String rut) {
        int sum = 0;
        int mul = 2;

        for (int i = rut.length() - 2; i >= 0; i--) {
            int digit = Character.getNumericValue(rut.charAt(i));
            sum += digit * mul;
            mul++;
            if (mul > 7) {
                mul = 2;
            }
        }

        int dv = 11 - (sum % 11);
        return (dv == 11) ? '0' : ((dv == 10) ? 'K' : (char) (dv + '0'));
    }
}

