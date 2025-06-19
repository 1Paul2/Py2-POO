package logicadenegocios;

import interfaces.Cifrable;

public class GestorCifrado {
    private Cifrable estrategia;

    public void setEstrategia(Cifrable estrategia) {
        this.estrategia = estrategia;
    }

    public String cifrar(Mensaje mensaje) {
        return estrategia.cifrarMensaje(mensaje);
    }

    public String descifrar(Mensaje mensaje) {
        return estrategia.descifrarMensaje(mensaje);
    }

    // ✅ Métodos solicitados explícitamente:
    public String cifrarMensaje(Mensaje m) {
        return cifrar(m);
    }

    public String descifrarMensaje(Mensaje m) {
        return descifrar(m);
    }
}
