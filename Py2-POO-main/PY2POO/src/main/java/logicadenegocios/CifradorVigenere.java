package logicadenegocios;

import interfaces.Cifrable;

public class CifradorVigenere implements Cifrable {

    /* ───────── TABLA «humanizada» 1-26 ───────── */
    private static final char[] TABLA = {
        '-',        // posición 0 NO se usa
        'a','b','c','d','e','f','g','h','i','j','k','l','m',
        'n','o','p','q','r','s','t','u','v','w','x','y','z'
    };
    //            1  2  3  …                    …        26

    /** Devuelve 1-26 según la tabla */
    private static int letraANumero(char c){
        c = Character.toLowerCase(c);
        for (int i = 1; i <= 26; i++){
            if (TABLA[i] == c) return i;
        }
        return -1;                  // no debería ocurrir con entrada válida
    }
    /** Devuelve la letra de 1-26 según la tabla */
    private static char numeroALetra(int n){
        return TABLA[n];
    }

    /* ───────── Algoritmo (true=cifrar | false=descifrar) ───────── */
    private String procesa(Mensaje m, boolean cifrar){

        String txt   = m.getTexto().toLowerCase();
        String clave = m.getClave();          // «23», «57», …

        int saltoA = Character.getNumericValue(clave.charAt(0)); // primer dígito
        int saltoB = Character.getNumericValue(clave.charAt(1)); // segundo
        int[] saltos = { saltoA, saltoB };                       // A-B-A-B…

        StringBuilder sb = new StringBuilder();
        int idxPalabra   = 0;                // posición dentro de la palabra

        for (char c : txt.toCharArray()) {

            /* separadores → reiniciar patrón */
            if (c == ' ' || c == '\n'){
                sb.append(c);
                idxPalabra = 0;
                continue;
            }

            int pos = letraANumero(c);       // 1-26
            if (pos != -1){                  // es una letra válida
                int desplaz = saltos[idxPalabra % 2];   // A o B
                int nuevo   = cifrar
                                ? pos + desplaz
                                : pos - desplaz;

                /* tabla circular 1-26 */
                if (nuevo > 26) nuevo -= 26;
                if (nuevo < 1 ) nuevo += 26;

                sb.append(numeroALetra(nuevo));
                idxPalabra++;
            } else {
                sb.append(c);                // símbolo extraño → igual
            }
        }
        return sb.toString();
    }

    /* ───────── Interface Cifrable ───────── */
    @Override public String cifrarMensaje   (Mensaje m){ return procesa(m,true ); }
    @Override public String descifrarMensaje(Mensaje m){ return procesa(m,false); }
}
