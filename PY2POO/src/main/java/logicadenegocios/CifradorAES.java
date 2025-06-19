package logicadenegocios;

import interfaces.Cifrable;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CifradorAES implements Cifrable {

    private static final String ALGORITMO = "AES";

    private SecretKeySpec generarLlave(String clave) {
        byte[] key = clave.getBytes();
        byte[] keyBytes = new byte[16];
        System.arraycopy(key, 0, keyBytes, 0, Math.min(key.length, 16));
        return new SecretKeySpec(keyBytes, ALGORITMO);
    }

    @Override
    public String cifrarMensaje(Mensaje pMensaje) {
        try {
            SecretKeySpec secretKey = generarLlave(pMensaje.getClave());
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(pMensaje.getTexto().getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            return "Error al cifrar: " + e.getMessage();
        }
    }

    @Override
    public String descifrarMensaje(Mensaje pMensaje) {
        try {
            SecretKeySpec secretKey = generarLlave(pMensaje.getClave());
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(pMensaje.getTexto()));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            return "Error al descifrar: " + e.getMessage();
        }
    }
}
