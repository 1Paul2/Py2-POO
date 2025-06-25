package logicadenegocios;

import interfaces.Cifrable;

public class CifradorCesar implements Cifrable {

    /* ── “matriz” de sustitución ──────────────────────────── */
    private static final char[] FILA_PLAIN  = {           // A-Z (índice 0-25)
        'a','b','c','d','e','f','g','h','i','j','k','l','m',
        'n','o','p','q','r','s','t','u','v','w','x','y','z'
    };

    private static final char[] FILA_CIFRADA = {          // D-Z A-C  (desplaz +3)
        'd','e','f','g','h','i','j','k','l','m','n','o','p',
        'q','r','s','t','u','v','w','x','y','z','a','b','c'
    };

    /* ── utilidades de búsqueda en la fila superior ───────── */
    private static int indiceEnPlain(char c) {
        c = Character.toLowerCase(c);
        for (int i = 0; i < FILA_PLAIN.length; i++) {
            if (FILA_PLAIN[i] == c) return i;
        }
        return -1;                         // no es letra a-z
    }

    /* ── núcleo común (true = cifrar, false = descifrar) ─── */
    private String procesa(String texto, boolean cifrar) {
        StringBuilder sb = new StringBuilder();

        for (char c : texto.toCharArray()) {

            int idx = indiceEnPlain(c);

            if (idx != -1) {               // es una letra válida
                char nuevo = cifrar
                             ? FILA_CIFRADA[idx]     // A→D, B→E, …
                             : FILA_PLAIN [idxDeCifrada(c)]; // inverso
                sb.append(nuevo);
            } else {
                sb.append(c);              // se deja tal cual (espacios, signos…)
            }
        }
        return sb.toString();
    }

    /* ── para descifrar: localizar la letra en la 2ª fila ── */
    private static int idxDeCifrada(char c) {
        c = Character.toLowerCase(c);
        for (int i = 0; i < FILA_CIFRADA.length; i++) {
            if (FILA_CIFRADA[i] == c) return i;
        }
        return -1;
    }

    /* ── implementación de la interfaz Cifrable ──────────── */
    @Override
    public String cifrarMensaje(Mensaje m) {
        return procesa(m.getTexto(), true);
    }

    @Override
    public String descifrarMensaje(Mensaje m) {
        return procesa(m.getTexto(), false);
    }
}
