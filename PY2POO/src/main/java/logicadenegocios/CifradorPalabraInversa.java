package logicadenegocios;

import interfaces.Cifrable;

public class CifradorPalabraInversa implements Cifrable {

    private String invertirPalabra(String palabra) {
        return new StringBuilder(palabra).reverse().toString();
    }

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        String[] palabras = pMensaje.getTexto().split(" ");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            resultado.append(invertirPalabra(palabra)).append(" ");
        }

        return resultado.toString().trim();
    }

    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        // Es igual que cifrar
        return cifrarMensaje(pMensaje);
    }
}
