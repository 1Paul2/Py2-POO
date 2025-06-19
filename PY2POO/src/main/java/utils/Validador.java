package utils;

public class Validador {

    // Permite validar según el algoritmo elegido
    public static boolean esMensajeValido(String texto, String algoritmo) {
        if (texto == null || texto.isEmpty()) return false;

        if (algoritmo.equalsIgnoreCase("Binario")) {
            // Validar que el mensaje contenga solo bloques binarios de 8 bits separados por espacios
            String[] bloques = texto.trim().split(" ");
            for (String bloque : bloques) {
                if (bloque.length() != 8 || !bloque.matches("[01]+")) {
                    return false;
                }
            }
            return true;
        }

        if (algoritmo.equalsIgnoreCase("Codigo Telefonico")) {
            // Acepta cualquier mensaje textual
            return true;
        }

        // Para los demás algoritmos, solo letras minúsculas y espacios
        return texto.matches("[a-z ]+");
    }

    // Solo letras minúsculas sin espacios ni tildes
    public static boolean esClaveValida(String clave) {
        return clave != null && clave.matches("[a-z]+");
    }

    // Valida que un número sea entre 10 y 99 (para Vigenere si se usa cifra)
    public static boolean esCifraValida(int cifra) {
        return cifra >= 10 && cifra <= 99;
    }
}
