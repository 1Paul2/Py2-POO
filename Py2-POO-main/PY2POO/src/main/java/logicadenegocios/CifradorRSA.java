
package logicadenegocios;

import interfaces.Cifrable;

public class CifradorRSA implements Cifrable {

   
    private static final int N = 3233;   
    private static final int E = 17;    
    private static final int D = 2753;   


    private int powMod(int base, int exp, int mod) {
        long resultado = 1;
        long b = base % mod;

        while (exp > 0) {
            if ((exp & 1) == 1) {          
                resultado = (resultado * b) % mod;
            }
            b = (b * b) % mod;             
            exp >>= 1;                     
        }
        return (int) resultado;
    }


    @Override
    public String cifrarMensaje(Mensaje pMensaje) {

        StringBuilder sb = new StringBuilder();
        String textoClaro = pMensaje.getTexto();         

        for (int i = 0; i < textoClaro.length(); i++) {
            int ascii = textoClaro.charAt(i);            
            int bloque = powMod(ascii, E, N);           
            sb.append(bloque);

            if (i < textoClaro.length() - 1) {
                sb.append('*');                          
            }
        }
        return sb.toString();
    }

 
    @Override
    public String descifrarMensaje(Mensaje pMensaje) {

        String[] bloques = pMensaje.getTexto().split("\\*");
        StringBuilder sb = new StringBuilder();

        for (String bloque : bloques) {
            try {
                int C = Integer.parseInt(bloque.trim());
                int ascii = powMod(C, D, N);             // m = C^d mod n
                sb.append((char) ascii);
            } catch (NumberFormatException ex) {
                return "⚠ Formato de texto cifrado inválido.";
            }
        }
        return sb.toString();
    }
}
