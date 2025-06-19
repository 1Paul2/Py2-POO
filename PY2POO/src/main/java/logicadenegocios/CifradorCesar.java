package logicadenegocios;

import interfaces.Cifrable;

public class CifradorCesar implements Cifrable {

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        StringBuilder resultado = new StringBuilder();
        String texto = pMensaje.getTexto().toLowerCase();
        int desplazamiento = pMensaje.getCifra();

        for (char c : texto.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                char nuevo = (char) ((c - 'a' + desplazamiento) % 26 + 'a');
                resultado.append(nuevo);
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }

    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        StringBuilder resultado = new StringBuilder();
        String texto = pMensaje.getTexto().toLowerCase();
        int desplazamiento = pMensaje.getCifra();

        for (char c : texto.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                char nuevo = (char) ((c - 'a' - desplazamiento + 26) % 26 + 'a');
                resultado.append(nuevo);
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }
}
