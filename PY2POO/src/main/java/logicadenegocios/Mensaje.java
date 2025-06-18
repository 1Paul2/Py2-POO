package logicadenegocios;

public class Mensaje {
    private String texto;
    private String clave;
    private int cifra;

    public Mensaje(String texto, String clave, int cifra) {
        this.texto = texto;
        this.clave = clave;
        this.cifra = cifra;
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
