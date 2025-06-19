package logicadenegocios;

import interfaces.Cifrable;

public class CifradorBinario implements Cifrable {

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        StringBuilder resultado = new StringBuilder();
        for (char c : pMensaje.getTexto().toCharArray()) {
            String binario = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            resultado.append(binario).append(" ");
        }
        return resultado.toString().trim();
    }

    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        String[] bloques = pMensaje.getTexto().split(" ");
        StringBuilder resultado = new StringBuilder();
        for (String bloque : bloques) {
            try {
                int ascii = Integer.parseInt(bloque, 2);
                resultado.append((char) ascii);
            } catch (NumberFormatException e) {
                resultado.append('?'); // Caracter inválido en binario
            }
        }
        return resultado.toString();
    }
}
