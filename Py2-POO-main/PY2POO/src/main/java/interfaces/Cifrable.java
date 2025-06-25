package interfaces;

import logicadenegocios.Mensaje;

public interface Cifrable {
    String cifrarMensaje(Mensaje pMensaje);
    String descifrarMensaje(Mensaje pMensaje);
}

