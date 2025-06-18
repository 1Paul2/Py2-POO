package logicadenegocios;

import interfaces.Cifrable;

public class CifradorLlave implements Cifrable {

    private int letraANumero(char c) {
        return c - 'a' + 1;
    }

    private char numeroALetra(int n) {
        return (char) ('a' + n - 1);
    }

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        String texto = pMensaje.getTexto().toLowerCase();
        String clave = pMensaje.getClave().toLowerCase();
        StringBuilder resultado = new StringBuilder();

        int lenClave = clave.length();
        int iClave = 0;

        for (char c : texto.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                int valTexto = letraANumero(c);
                int valClave = letraANumero(clave.charAt(iClave % lenClave));

                int suma = valTexto + valClave;
                if (suma > 26) suma -= 26;

                resultado.append(numeroALetra(suma));
                iClave++;
            } else {
                resultado.append(c);  // mantener espacios
            }
        }

        return resultado.toString();
    }

    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        String texto = pMensaje.getTexto().toLowerCase();
        String clave = pMensaje.getClave().toLowerCase();
        StringBuilder resultado = new StringBuilder();

        int lenClave = clave.length();
        int iClave = 0;

        for (char c : texto.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                int valTexto = letraANumero(c);
                int valClave = letraANumero(clave.charAt(iClave % lenClave));

                int resta = valTexto - valClave;
                if (resta <= 0) resta += 26;

                resultado.append(numeroALetra(resta));
                iClave++;
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }
}
