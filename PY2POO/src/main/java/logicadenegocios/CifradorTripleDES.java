package logicadenegocios;

import interfaces.Cifrable;

public class CifradorTripleDES implements Cifrable {

    private String cifrarCesar(String texto, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                int original = c - 'a';
                int nuevo = (original + desplazamiento) % 26;
                resultado.append((char) ('a' + nuevo));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    private String descifrarCesar(String texto, int desplazamiento) {
        return cifrarCesar(texto, 26 - (desplazamiento % 26));
    }

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        String texto = pMensaje.getTexto().toLowerCase();
        String clave = pMensaje.getClave().toLowerCase();
        int d1 = (clave.length() > 0) ? clave.charAt(0) - 'a' : 3;
        int d2 = (clave.length() > 1) ? clave.charAt(1) - 'a' : 5;
        int d3 = (clave.length() > 2) ? clave.charAt(2) - 'a' : 7;

        texto = cifrarCesar(texto, d1);
        texto = cifrarCesar(texto, d2);
        texto = cifrarCesar(texto, d3);
        return texto;
    }

    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        String texto = pMensaje.getTexto().toLowerCase();
        String clave = pMensaje.getClave().toLowerCase();
        int d1 = (clave.length() > 0) ? clave.charAt(0) - 'a' : 3;
        int d2 = (clave.length() > 1) ? clave.charAt(1) - 'a' : 5;
        int d3 = (clave.length() > 2) ? clave.charAt(2) - 'a' : 7;

        texto = descifrarCesar(texto, d3);
        texto = descifrarCesar(texto, d2);
        texto = descifrarCesar(texto, d1);
        return texto;
    }
}
