package logicadenegocios;

import interfaces.Cifrable;

public class CifradorMensajeInverso implements Cifrable {

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        return new StringBuilder(pMensaje.getTexto()).reverse().toString();
    }

    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        // Invertir de nuevo el texto para descifrarlo
        return new StringBuilder(pMensaje.getTexto()).reverse().toString();
    }
}
