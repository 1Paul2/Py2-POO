package logicadenegocios;

import interfaces.Cifrable;

public class CifradorLlave implements Cifrable {


    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
    private static final int[] VALORES = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26
    };

    // Convertir letra a valor según tabla personalizada
    private int letraAValor(char letra) {
        letra = Character.toLowerCase(letra);
        int pos = ALFABETO.indexOf(letra);
        return pos >= 0 ? VALORES[pos] : 0;
    }

 
    private char valorALetra(int valor) {
        valor = ((valor - 1) % 26 + 26) % 26;
        return ALFABETO.charAt(valor);
    }

    // Normaliza clave para que tenga misma longitud que el texto (sin contar espacios o saltos)
    private String expandirClave(String clave, int longitud) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; sb.length() < longitud; i++) {
            sb.append(clave.charAt(i % clave.length()));
        }
        return sb.toString();
    }

    private String procesa(Mensaje m, boolean cifrar) {
        String texto = m.getTexto().toLowerCase();
        String clave = m.getClave().toLowerCase().replaceAll("[^a-z]", "");
        StringBuilder resultado = new StringBuilder();

        int contador = 0;
        String claveExpandida = expandirClave(clave, texto.replaceAll("[^a-z]", "").length());

        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);

            if (letra >= 'a' && letra <= 'z') {
                char letraClave = claveExpandida.charAt(contador++);
                int valorLetra = letraAValor(letra);
                int valorClave = letraAValor(letraClave);

                int nuevoValor = cifrar
                        ? valorLetra + valorClave
                        : valorLetra - valorClave;

                resultado.append(valorALetra(nuevoValor));
            } else {
                resultado.append(letra); 
            }
        }

        return resultado.toString();
    }

    @Override
    public String cifrarMensaje(Mensaje m) {
        return procesa(m, true);
    }

    @Override
    public String descifrarMensaje(Mensaje m) {
        return procesa(m, false);
    }
}
