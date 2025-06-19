package logicadenegocios;

import interfaces.Cifrable;

public class CifradorCodigoTelefonico implements Cifrable {

    private char letraANumero(char c) {
        switch (c) {
            case 'a': case 'b': case 'c': return '2';
            case 'd': case 'e': case 'f': return '3';
            case 'g': case 'h': case 'i': return '4';
            case 'j': case 'k': case 'l': return '5';
            case 'm': case 'n': case 'o': return '6';
            case 'p': case 'q': case 'r': case 's': return '7';
            case 't': case 'u': case 'v': return '8';
            case 'w': case 'x': case 'y': case 'z': return '9';
            default: return c;
        }
    }

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        String texto = pMensaje.getTexto().toLowerCase();
        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            resultado.append(letraANumero(c));
        }

        return resultado.toString();
    }

    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        // Este cifrado no es reversible de forma única, así que devolvemos un mensaje aclaratorio
        return "No se puede descifrar un mensaje codificado en teclado telefonico.";
    }
}
