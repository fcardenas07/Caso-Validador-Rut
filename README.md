# Tarea caso Rut

Se desea calcular el dígito verificador de un rut, para ello se tienen los siguientes pasos:

1. Se ingresa un rut, por ejemplo 12.345.678-5


2. Se toman los números del rut sin el dígito verificador, en este caso, 12345678


3. Invertir el orden de la cadena, en este caso, 87654321


4. Multiplicar cada dígito por la siguiente cadena 2, 3, 4, 5, 6, 7 y si se acaba la serie, volvemos a empezar 2, 3, 4... En este caso, 8 x 2, 7 x 3, 6 x 4, 5 x 5, 4 x 6, 3 x 7, 2 x 2, 1 x 3.


5. Sumar cada resultado de las multiplicaciones y dividir por 11. En este caso, 138 / 11 = 12.54


6. Se toma el resultado sin los decimales y sin aproximar, luego se multiplica por 11. En este caso 12 x 11= 132


7. A la suma de las multiplicaciones obtenidas en el paso 4, se le resta la multiplicación del paso 6 y se deja en valor absoluto (signo positivo). En este caso 138 - 132 = 6


8. Para finalizar, a 11 le restamos el resultado de la resta en el paso 7, obteniendo el dígito verificador. Si da 10=k, 11=0. En este caso 11 - 6 = 5


Implemente una solución en Java que permita calcular el dígito verificador del rut, el cual debe ser ingresado por teclado y validado a partir de pruebas unitarias y gestión de excepciones.