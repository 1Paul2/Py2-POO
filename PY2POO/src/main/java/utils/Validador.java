package utils;

public class Validador {

    // Solo letras minúsculas, espacios y sin tildes
    public static boolean esMensajeValido(String texto) {
        return texto != null && texto.matches("[a-z ]+");
    }

    // Solo letras minúsculas sin espacios ni tildes
    public static boolean esClaveValida(String clave) {
        return clave != null && clave.matches("[a-z]+");
    }

    // Valida que un número sea entre 10 y 99 (para Vigenere)
    public static boolean esCifraValida(int cifra) {
        return cifra >= 10 && cifra <= 99;
    }
}
