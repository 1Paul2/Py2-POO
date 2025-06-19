package logicadenegocios;

public class Mensaje {
    private String texto;
    private String clave;
    private int cifra;

    public Mensaje(String texto, String clave) {
        this.texto = texto;
        this.clave = clave;
        this.cifra = calcularDesplazamientoDesdeClave(clave);
    }

    // NUEVO CONSTRUCTOR para mensajes sin clave
    public Mensaje(String texto) {
        this.texto = texto;
        this.clave = "";
        this.cifra = 0;
    }

    private int calcularDesplazamientoDesdeClave(String clave) {
        if (clave == null || clave.isEmpty()) return 0;
        char letra = Character.toLowerCase(clave.charAt(0));
        if (letra < 'a' || letra > 'z') return 0;
        return letra - 'a';  // 'a' = 0, 'b' = 1, ..., 'z' = 25
    }

    public String getTexto() {
        return texto;
    }

    public String getClave() {
        return clave;
    }

    public int getCifra() {
        return cifra;
    }
}
