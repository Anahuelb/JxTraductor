
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author BRYAN
 */
public class FabricaArbol {

    ARBOL AVL = new ARBOL("1", "1");

    public void confeccionaArbol() {
        try {
            String name = "DICCIONARIO.txt";
            File arch = new File(name);
            FileReader fr = new FileReader(arch);
            BufferedReader buf = new BufferedReader(fr);
            StringTokenizer stk;
            int contador = 0;
            String ing, esp = "";
            while (buf.ready()) {
                String reglon = buf.readLine();
                stk = new StringTokenizer(reglon);
                while (stk.hasMoreTokens()) {
                    String palabra = stk.nextToken();
                    contador++;
                    if (contador % 2 == 0) {
                        ing = palabra;
                        //ARBOL A;
                        AVL = AVL.insertar(AVL, ing, esp);
                        AVL.corrigeBalance(AVL);
                    } else {
                        esp = palabra;
                    }
                }
            }
            buf.close();

        } catch (IOException e) {
        }
    }
    String resul;

    public String traduceme(ARBOL X, String ing) {
        resul = "";
        traducido(X, ing);
        if (resul.equals("")) {
            return "(" + ing + ")";
        } else {
            return resul;
        }
    }

    public void traducido(ARBOL A, String ing) {
        if (A == null) {
            return;
        }
        if (ing.equals(A.getIngles())) {
            resul = "" + A.getEspañol();
            return;
        }
        traducido(A.izq, ing);
        traducido(A.der, ing);
    }

    public void lol(ARBOL t) {//para revisar numero de palabras
        if (t == null) {
            return;

        }
        System.out.println(t.getIngles());
        lol(t.izq);
        lol(t.der);
    }
}
