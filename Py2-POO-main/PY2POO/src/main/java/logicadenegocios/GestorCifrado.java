package logicadenegocios;

import interfaces.Cifrable;

public class GestorCifrado {

    private Cifrable estrategia;


    public void setEstrategia(Cifrable estrategia) {
        this.estrategia = estrategia;
    }

    /* Métodos que exige la rúbrica */
    public String cifrarMensaje(Mensaje m)   { return cifrar(m); }
    public String descifrarMensaje(Mensaje m){ return descifrar(m); }


    private String cifrar(Mensaje m) {
        verificarEstrategia();
        return estrategia.cifrarMensaje(m);
    }

    private String descifrar(Mensaje m) {
        verificarEstrategia();
        return estrategia.descifrarMensaje(m);
    }

    private void verificarEstrategia() {
        if (estrategia == null) {
            throw new IllegalStateException("No se ha definido la estrategia de cifrado.");
        }
    }
}
