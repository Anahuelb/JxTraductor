
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *
 * @author BRYAN
 */
public class EditorTexto {

    public void LeeYTraduce() {
        FabricaArbol FA = new FabricaArbol();
        FA.confeccionaArbol();
        try {
            String name = "ENTRADA.txt";
            File arch = new File(name);
            FileReader fr = new FileReader(arch);
            BufferedReader buf = new BufferedReader(fr);
            StringTokenizer stk;
            File sale = new File("SALIDA.txt");
            FileWriter w = new FileWriter(sale);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            while (buf.ready()) {
                String reglon = buf.readLine();
                stk = new StringTokenizer(reglon);
                String ln = System.getProperty("line.separator");
                while (stk.hasMoreTokens()) {
                    String palabra = stk.nextToken();
                    String espacio = " ";
                    String ante = "";
                    if (palabra.substring(palabra.length() - 1, palabra.length()).equals(",")) {
                        palabra = palabra.substring(0, palabra.length() - 1);
                        espacio = ", ";
                    }
                    if (palabra.substring(palabra.length() - 1, palabra.length()).equals(".")) {
                        palabra = palabra.substring(0, palabra.length() - 1);
                        espacio = ". ";
                    }
                    if (palabra.substring(palabra.length() - 1, palabra.length()).equals(";")) {
                        palabra = palabra.substring(0, palabra.length() - 1);
                        espacio = "; ";
                    }
                    if (palabra.substring(palabra.length() - 1, palabra.length()).equals(")")) {
                        palabra = palabra.substring(0, palabra.length() - 1);
                        espacio = ") ";
                    }
                    if (palabra.substring(palabra.length() - 1, palabra.length()).equals("\"")) {
                        palabra = palabra.substring(0, palabra.length() - 1);
                        espacio = "\" ";
                    }
                    if (palabra.substring(0, 1).equals("\"")) {
                        palabra = palabra.substring(1, palabra.length());
                        ante = "\"";
                    }
                    if (palabra.substring(0, 1).equals("(")) {
                        palabra = palabra.substring(1, palabra.length());
                        ante = "(";
                    }
                    if (palabra.substring(palabra.length() - 1, palabra.length()).equals("!")) {
                        palabra = palabra.substring(0, palabra.length() - 1);
                        espacio = "! ";
                    }
                    if (palabra.substring(palabra.length() - 1, palabra.length()).equals("?")) {
                        palabra = palabra.substring(0, palabra.length() - 1);
                        espacio = "? ";
                    }
                    String palabra2 = FA.traduceme(FA.AVL, palabra);
                    wr.write(ante + palabra2 + espacio);
                    if (stk.hasMoreTokens() == false) {
                        wr.write(ln);
                    }
                }
            }
            wr.close();
            bw.close();
            buf.close();

        } catch (IOException e) {
        }
    }
}
