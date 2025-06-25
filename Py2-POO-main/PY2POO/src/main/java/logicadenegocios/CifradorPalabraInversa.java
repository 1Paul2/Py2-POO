package logicadenegocios;

import interfaces.Cifrable;

public class CifradorPalabraInversa implements Cifrable {

    private String invertirPalabra(String palabra) {
        return new StringBuilder(palabra).reverse().toString();
    }

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        StringBuilder resultado = new StringBuilder();
        String[] lineas = pMensaje.getTexto().split("\n");

        for (int i = 0; i < lineas.length; i++) {
            String[] palabras = lineas[i].split(" ");
            for (int j = 0; j < palabras.length; j++) {
                resultado.append(new StringBuilder(palabras[j]).reverse());
                if (j < palabras.length - 1) resultado.append(" ");
            }
            if (i < lineas.length - 1) resultado.append("\n");
        }

        return resultado.toString();
    }


    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        // Es igual que cifrar
        return cifrarMensaje(pMensaje);
    }
}
