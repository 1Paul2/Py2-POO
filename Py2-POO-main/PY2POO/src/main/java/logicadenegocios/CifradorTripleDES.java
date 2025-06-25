package logicadenegocios;

import interfaces.Cifrable;

public class CifradorTripleDES implements Cifrable {

    @Override
    public String cifrarMensaje(Mensaje m) {
        return ejecutaPython("encrypt", m);
    }

    @Override
    public String descifrarMensaje(Mensaje m) {
        return ejecutaPython("decrypt", m);
    }

    /* ----------  helper  -------------------------------------------------- */
    private String ejecutaPython(String modoPython, Mensaje m) {
        try {
            // 3des  ←  el nombre que entiende crypto_api.py
            return PyCryptoRunner.run("3des", modoPython,
                                      m.getClave(), m.getTexto());
        } catch (Exception e) {
            return "⚠ Error 3DES: " + e.getMessage();
        }
    }
}

