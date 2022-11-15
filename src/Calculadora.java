import javax.swing.JOptionPane;

public class Calculadora {
    static int n, x, a, b, N, k, cont = 0;
    static double result, numerador, denominador, p, landa;
    static final double e = 2.7182;

    public static void main(String[] args) {
        boolean condicion = true;
        JOptionPane.showMessageDialog(null, "Bienvenido a la calculadora de probabilidad");
        do {
            a = Integer.parseInt(JOptionPane.showOptionDialog(null, "Seleccione la operacion que desea realizar",
                    "Calculadora de probabilidad", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[] { "Binomial", "Poisson", "Hipergeometrica", "Salir" }, "Binomial") + "");

            switch (a) {
                case 0:
                    n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor para n"));

                    p = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor para p"));

                    x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor para x"));
                    result = binomial(n, p, x);
                    JOptionPane.showMessageDialog(null,
                            "Siendo:\n n= " + n + "\np= " + p + "\nx= " + x + "\nEl resultado es: P(x=" + x + ")= "
                                    + result);

                    do {
                        System.out.println("¿desea hacer otro calculo?\n1-si\n2-no");
                        b = Integer.parseInt(JOptionPane.showOptionDialog(null, "¿desea hacer otro calculo?",
                                "Calculadora de probabilidad", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, new Object[] { "Si", "No" }, "Si") + "");
                        if (b == 0) {
                            condicion = true;
                        } else {
                            condicion = false;
                            JOptionPane.showMessageDialog(null, "Gracias por usar la calculadora");
                        }

                    } while (b != 0 && b != 1);
                    break;
                case 1:
                    landa = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor para ⁁"));

                    x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor para x"));
                    result = poisson(landa, x);
                    JOptionPane.showMessageDialog(null,
                            "Siendo:\n⁁= " + landa + "\nx= " + x + "\nEl resultado es: P(x=" + x + ")= " + result);

                    do {
                        System.out.println("¿desea hacer otro calculo?\n1-si\n2-no");
                        b = Integer.parseInt(JOptionPane.showOptionDialog(null, "¿desea hacer otro calculo?",
                                "Calculadora de probabilidad", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, new Object[] { "Si", "No" }, "Si") + "");
                        if (b == 0) {
                            condicion = true;
                        } else {
                            condicion = false;
                            JOptionPane.showMessageDialog(null, "Gracias por usar la calculadora");
                        }

                    } while (b != 0 && b != 1);
                    break;
                case 2:

                    N = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor para N"));

                    n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor para n"));

                    k = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor para k"));

                    x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor para x"));
                    do {

                        b = Integer.parseInt(JOptionPane.showOptionDialog(null, "Ingrese el caso",
                                "Calculadora de probabilidad", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, new Object[] { "P(X=x)", "P(X≤x)" }, "P(X=x)") + "");
                        if (b == 0) {
                            result = hipergeometrica(N, n, k, x);
                            JOptionPane.showMessageDialog(null,
                                    "Siendo:\n N= " + N + "\nn= " + n + "\nk= " + k + "\nx= " + x
                                            + "\nEl resultado es: P(x=" + x + ")= "
                                            + result);
                        } else if (b == 1) {
                            result = hipergeometrica(N, n, k, x);
                            for (int i = 0; i < x; i++) {
                                result = result + hipergeometrica(N, n, k, i);
                            }
                            JOptionPane.showMessageDialog(null,
                                    "Siendo:\n N= " + N + "\nn= " + n + "\nk= " + k + "\nx= " + x
                                            + "\nEl resultado es: P(x≤" + x + ")= "
                                            + result);
                        }

                    } while (b != 0 && b != 1);
                    do {
                        System.out.println("¿desea hacer otro calculo?\n1-si\n2-no");
                        b = Integer.parseInt(JOptionPane.showOptionDialog(null, "¿desea hacer otro calculo?",
                                "Calculadora de probabilidad", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, new Object[] { "Si", "No" }, "Si") + "");
                        if (b == 0) {
                            condicion = true;
                        } else {
                            condicion = false;
                            JOptionPane.showMessageDialog(null, "Gracias por usar la calculadora");
                        }

                    } while (b != 0 && b != 1);
                    break;
                case 3:
                    condicion = false;
                    JOptionPane.showMessageDialog(null, "Gracias por usar la calculadora");
                    break;
                default:
                    condicion = false;
            }

        } while (condicion);
    }

    // binomial
    public static double binomial(int n, double p, int x) {
        result = convinatorio(n, x) * Math.pow(p, x) * (Math.pow(1 - p, n - x));
        return result;
    }

    // Poison
    public static double poisson(double landa, int x) {
        // necesito landa, x y e(como constante)
        // formula (e^-landa)*(landa^x)/(x!)
        // e^-landa
        numerador = (float) Math.pow(e, (-1 * landa));
        // landa^x
        numerador = (float) (numerador * Math.pow(landa, x));
        // x!
        denominador = factorial(x);
        result = numerador / denominador;
        return result;
    }

    // hipergeometrica
    public static double hipergeometrica(int N, int n, int k, int x) {
        // formula P(x=k)=((k convinaddo x)*(N-k convinado n-x))/(N convinado n)
        numerador = convinatorio(k, x) * convinatorio((N - k), (n - x));
        denominador = convinatorio(N, n);
        result = numerador / denominador;
        return result;
    }

    public static double convinatorio(int cantidad, int agrupaciones) {
        // (cantidad!)/(agrupaciones!*(cantidad-agrupaciones)!)
        if (cantidad >= agrupaciones) {
            result = (factorial(cantidad) / (factorial(agrupaciones) * factorial(cantidad - agrupaciones)));
            return result;
        } else
            System.out.println("error");
        return 0;
    }

    public static int factorial(int numero) {
        if (numero == 0)
            return 1;
        else
            return numero * factorial(numero - 1);
    }

}
