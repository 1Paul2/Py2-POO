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
                // Saltar letras inválidas en clave
                while (iClave < lenClave && (clave.charAt(iClave % lenClave) < 'a' || clave.charAt(iClave % lenClave) > 'z')) {
                    iClave++;
                }

                char letraClave = clave.charAt(iClave % lenClave);
                int valTexto = letraANumero(c);
                int valClave = letraANumero(letraClave);

                int suma = valTexto + valClave;
                if (suma > 26) suma -= 26;

                resultado.append(numeroALetra(suma));
                iClave++;
            } else {
                resultado.append(c);  // conservar espacios u otros caracteres
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
                // Saltar letras inválidas en clave
                while (iClave < lenClave && (clave.charAt(iClave % lenClave) < 'a' || clave.charAt(iClave % lenClave) > 'z')) {
                    iClave++;
                }

                char letraClave = clave.charAt(iClave % lenClave);
                int valTexto = letraANumero(c);
                int valClave = letraANumero(letraClave);

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
