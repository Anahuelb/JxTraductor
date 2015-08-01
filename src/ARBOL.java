
/**
 *
 * @author BRYAN
 */
public class ARBOL {

    private String español, ingles;
    private int balancear;
    private static boolean propaga;
    ARBOL izq, der;
    private static int Nivel;

    ARBOL(String ing, String esp) {
        español = esp;
        ingles = ing;
        propaga = true;
        balancear = 0;
        izq = der = null;
    }

    public String getIngles() {
        return ingles;
    }

    public String getEspañol() {
        return español;
    }

    public ARBOL insertar(ARBOL t, String d, String e) {
        if (t == null) {
            return new ARBOL(d, e);
        }
        if (d.equals(t.ingles)) {
            return t;
        }
        if (d.compareTo(t.ingles) < 0)//d< t.dato
        {
            t.izq = insertar(t.izq, d, e);
            if (propaga) {
                t.balancear--;
                if (t.balancear == -2) {
                    if (t.izq.balancear < 0) {
                        t = LL(t);
                    } else {
                        t = LR(t);
                    }
                    propaga = false;
                    t = corrigeBalance(t);
                } else if (t.balancear == 0) {
                    propaga = false;
                }
            }
        } else {
            t.der = insertar(t.der, d, e);
            if (propaga) {
                t.balancear++;
                if (t.balancear == 2) {
                    if (t.der.balancear < 0) {
                        t = RL(t);
                    } else {
                        t = RR(t);
                    }
                    propaga = false;
                    t = corrigeBalance(t);
                } else if (t.balancear == 0) {
                    propaga = false;
                }
            }
        }
        return t;
    }

    public ARBOL LL(ARBOL t) {
        ARBOL p = t.izq;
        t.izq = p.der;
        p.der = t;
        return p;
    }

    public ARBOL RR(ARBOL t) {
        ARBOL p = t.der;
        t.der = p.izq;
        p.izq = t;
        return p;
    }

    public ARBOL LR(ARBOL t) {
        ARBOL p = t.izq;
        ARBOL h = p.der;
        t.izq = h.der;
        p.der = h.izq;
        h.der = t;
        h.izq = p;
        return h;

    }

    public ARBOL RL(ARBOL t) {
        ARBOL p = t.der;
        ARBOL h = p.izq;
        t.der = h.izq;
        p.izq = h.der;
        h.izq = t;
        h.der = p;
        return h;
    }

    public ARBOL corrigeBalance(ARBOL t) {
        if (t != null) {
            Nivel = 0;
            numNv(t.izq, 1);
            int a = Nivel;
            Nivel = 0;
            numNv(t.der, 1);
            int b = Nivel;
            t.balancear = b - a;
            corrigeBalance(t.izq);
            corrigeBalance(t.der);
        }
        return t;
    }

    private void numNv(ARBOL t, int nivel) {
        if (t != null) {
            if (nivel > Nivel) {
                Nivel++;
            }
            numNv(t.izq, nivel + 1);
            numNv(t.der, nivel + 1);
        }

    }
}
