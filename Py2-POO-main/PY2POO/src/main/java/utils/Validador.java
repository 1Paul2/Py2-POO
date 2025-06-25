package utils;

public class Validador {

    // Acepta letras (mayúsculas o minúsculas), espacios, tabulaciones y saltos de línea
    private static final String ALFABETICO_REGEX = "[A-Za-z\\s]+";
  
    
    public static boolean esMensajeValido(String texto, String algoritmo) {
        if (texto == null || texto.isEmpty()) return false;

        if (algoritmo.equalsIgnoreCase("Codigo Telefonico")) {
            return texto.matches(ALFABETICO_REGEX);
        }
    
        if (algoritmo.equalsIgnoreCase("Binario") ||
            algoritmo.equalsIgnoreCase("Codigo Telefonico") ||
            algoritmo.equalsIgnoreCase("Palabra Inversa") ||
            algoritmo.equalsIgnoreCase("RSA") ||
            algoritmo.equalsIgnoreCase("Triple DES") ||
            algoritmo.equalsIgnoreCase("AES") ||
            algoritmo.equalsIgnoreCase("Mensaje Inverso")) {
            return true;
        }
        

        return texto.matches(ALFABETICO_REGEX);
    }

    
    public static boolean esClaveValida(String clave, String algoritmo) {

        /* ───── Algoritmos SIN clave ───── */
        if (algoritmo.equalsIgnoreCase("Mensaje Inverso") ||
            algoritmo.equalsIgnoreCase("Palabra Inversa") ||
            algoritmo.equalsIgnoreCase("Binario") ||
            algoritmo.equalsIgnoreCase("RSA")||
            algoritmo.equalsIgnoreCase("Cesar")||
            algoritmo.equalsIgnoreCase("Codigo Telefonico")) {
            return true;                     // se ignora la clave
        }

        // ─── A partir de aquí, SÍ se exige clave ───
        if (clave == null || clave.isEmpty()) return false;

        if (algoritmo.equalsIgnoreCase("AES") ||
        algoritmo.equalsIgnoreCase("Triple DES")) {
        // cualquier combinación de letras y números, al menos 1 carácter
        return clave.matches("[A-Za-z0-9]+");
        }
        
        if (algoritmo.equalsIgnoreCase("Vigenere")) {
            return clave.matches("\\d{2}");
        }

        // Resto de algoritmos: solo letras (mayúsculas o minúsculas)
        return clave.matches("[A-Za-z]+");
    }

}
