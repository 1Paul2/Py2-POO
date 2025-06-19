package logicadenegocios;

import interfaces.Cifrable;

public class CifradorVigenere implements Cifrable {

    private int letraANumero(char c) {
        return c - 'a';
    }

    private char numeroALetra(int n) {
        return (char) ('a' + n);
    }

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        String texto = pMensaje.getTexto().toLowerCase().replaceAll(" ", "");
        String clave = pMensaje.getClave().toLowerCase();
        StringBuilder resultado = new StringBuilder();

        int lenClave = clave.length();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c)) {
                int t = letraANumero(c);
                int k = letraANumero(clave.charAt(i % lenClave));
                int cifrado = (t + k) % 26;
                resultado.append(numeroALetra(cifrado));
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }

    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        String texto = pMensaje.getTexto().toLowerCase().replaceAll(" ", "");
        String clave = pMensaje.getClave().toLowerCase();
        StringBuilder resultado = new StringBuilder();

        int lenClave = clave.length();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c)) {
                int t = letraANumero(c);
                int k = letraANumero(clave.charAt(i % lenClave));
                int descifrado = (t - k + 26) % 26;
                resultado.append(numeroALetra(descifrado));
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }
}
