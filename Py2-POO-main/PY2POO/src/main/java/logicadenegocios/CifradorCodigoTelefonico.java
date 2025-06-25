package logicadenegocios;

import interfaces.Cifrable;

/**
 * Cifrado por “código telefónico”.
 *
 *  ┌───┬────┬────┐
 *  │ 2 │ ABC│    │   Letras –> 21-23
 *  │ 3 │ DEF│    │   Letras –> 31-33
 *  │ 4 │ GHI│    │   ...
 *  │ 5 │ JKL│    │
 *  │ 6 │ MNO│    │
 *  │ 7 │ PQRS│   │
 *  │ 8 │ TUV│    │
 *  │ 9 │ WXYZ│   │
 *  └───┴────┴────┘
 *
 * Ejemplo: “e” = 32 (tecla 3, posición 2) ;  “s” = 74.
 */
public class CifradorCodigoTelefonico implements Cifrable {

    /* ---------- tablas auxiliares ---------- */

    /** tecla → lista de letras en orden */
    private static final String[] TECLAS = {
            "",      // 0 – no se usa
            "",      // 1 – no se usa
            "abc",   // 2
            "def",   // 3
            "ghi",   // 4
            "jkl",   // 5
            "mno",   // 6
            "pqrs",  // 7
            "tuv",   // 8
            "wxyz"   // 9
    };

    /** Convierte una letra a su código “XY” (X-tecla, Y-posición) */
    private static String letraACodigo(char ch) {
        char c = Character.toLowerCase(ch);
        for (int tecla = 2; tecla <= 9; tecla++) {
            int pos = TECLAS[tecla].indexOf(c);
            if (pos != -1) {                      // encontrado
                return "" + tecla + (pos + 1);    // posición es 1-based
            }
        }
        return "";                                // no era letra (se ignora)
    }

    /** Convierte un código “XY” a su letra correspondiente */
    private static char codigoALetra(String code) {
        if (code.length() != 2) return '?';
        int tecla = code.charAt(0) - '0';         // 2-9
        int pos   = code.charAt(1) - '1';         // 0-3
        if (tecla < 2 || tecla > 9)  return '?';
        String letras = TECLAS[tecla];
        if (pos < 0 || pos >= letras.length()) return '?';
        return letras.charAt(pos);
    }

    /* ---------- Cifrable ---------- */

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        StringBuilder sb = new StringBuilder();
        String[] palabras = pMensaje.getTexto().split("\\s+");    // separa por espacios

        for (int w = 0; w < palabras.length; w++) {
            String palabra = palabras[w];
            for (int i = 0; i < palabra.length(); i++) {
                String cod = letraACodigo(palabra.charAt(i));
                if (!cod.isEmpty()) {
                    sb.append(cod).append(' ');
                }
            }
            if (w < palabras.length - 1) sb.append("* ");         // separador de palabras
        }
        return sb.toString().trim();
    }

    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        StringBuilder sb = new StringBuilder();
        String[] tokens = pMensaje.getTexto().trim().split("\\s+");

        for (String tk : tokens) {
            if (tk.equals("*")) {                 // separador de palabra
                sb.append(' ');
            } else {
                char letra = codigoALetra(tk);
                sb.append(letra);
            }
        }
        return sb.toString();
    }
}
