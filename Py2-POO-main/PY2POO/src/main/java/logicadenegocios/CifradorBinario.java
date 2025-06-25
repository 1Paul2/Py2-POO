package logicadenegocios;

import interfaces.Cifrable;

public class CifradorBinario implements Cifrable {

    /** 
     * Tabla fija de 5 bits (a → 00000 … z → 11001). 
     * El índice 0 corresponde a 'a', el 1 a 'b', etc.
     */
    private static final String[] TABLA = {
        "00000","00001","00010","00011","00100","00101","00110","00111",
        "01000","01001","01010","01011","01100","01101","01110","01111",
        "10000","10001","10010","10011","10100","10101","10110","10111",
        "11000","11001"
    };

    /* ---------------------------  CIFRAR  --------------------------- */
    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        StringBuilder sb = new StringBuilder();
        String texto = pMensaje.getTexto().toLowerCase();

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (c == ' ') {                 // espacio → separador de palabra
                sb.append("* ");
            } else if (c >= 'a' && c <= 'z') {
                sb.append(TABLA[c - 'a']).append(' ');
            } else {
                // Cualquier símbolo no permitido: lo omitimos o puedes lanzar
                // excepción si lo prefieres.
            }
        }
        // Eliminar el último espacio si existe
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ')
            sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    /* -------------------------  DESCIFRAR  -------------------------- */
    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        StringBuilder sb = new StringBuilder();
        String[] tokens = pMensaje.getTexto().trim().split("\\s+");

        for (String token : tokens) {
            if (token.equals("*")) {
                sb.append(' ');
            } else {
                int idx = buscarEnTabla(token);
                if (idx != -1) {
                    sb.append((char) ('a' + idx));
                } else {
                    // Código inválido: puedes ignorarlo o poner �
                }
            }
        }
        return sb.toString();
    }

    /* Busca un código binario en la tabla; devuelve el índice o -1 si no existe. */
    private int buscarEnTabla(String bin) {
        for (int i = 0; i < TABLA.length; i++) {
            if (TABLA[i].equals(bin)) return i;
        }
        return -1;
    }
}
