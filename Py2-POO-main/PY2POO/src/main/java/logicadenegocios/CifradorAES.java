package logicadenegocios;

import interfaces.Cifrable;

public class CifradorAES implements Cifrable {

    @Override
    public String cifrarMensaje(Mensaje m) {
        return ejecutaPython("encrypt", m);
    }

    @Override
    public String descifrarMensaje(Mensaje m) {
        return ejecutaPython("decrypt", m);
    }

    /*------------------------------------------------*/
    private String ejecutaPython(String modo, Mensaje m) {
        try {
            // “aes” es el identificador que entiende crypto_api.py
            return PyCryptoRunner.run("aes", modo, m.getClave(), m.getTexto());
        } catch (Exception e) {
            return "⚠ Error AES: " + e.getMessage();
        }
    }
}
