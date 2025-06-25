package logicadenegocios;

import java.io.*;

public class PyCryptoRunner {

    private static final String PY_SCRIPT = "crypto_api.py";

public static String run(String algoritmo, String modo,
                         String clave, String texto)
                         throws IOException, InterruptedException {

    ProcessBuilder pb = new ProcessBuilder("python", PY_SCRIPT,
                                           algoritmo, modo, clave);
    Process p = pb.start();

    try (BufferedWriter w = new BufferedWriter(
            new OutputStreamWriter(p.getOutputStream()))) {
        w.write(texto);
        w.flush();   // <-- importante
        w.close();   // <-- cierra stdin para Python
    }

    StringBuilder out = new StringBuilder();
    try (BufferedReader r = new BufferedReader(
            new InputStreamReader(p.getInputStream()))) {
        String line;
        while ((line = r.readLine()) != null) out.append(line);
    }

    int exit = p.waitFor();
    if (exit != 0)
        throw new RuntimeException("python exit = " + exit);

    return out.toString();
}
}
