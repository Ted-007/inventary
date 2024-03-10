import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public abstract class Pila {
    private int tope;
    private int size;

    Collection<String[]> lista;

    public String getType() {
        return type;
    }

    private String type;

    public Pila(int s, String types) {
        tope = 0;
        size = s;
        lista = new ArrayList<>();
        type = types;
    }

    public void addProduct(String nombre, String referecia, String cantidad) {
        if (tope < size) {
            Stream<String> stringStream = procesar(lista, referecia, cantidad);
            if (!addValidation(stringStream)){
                String nuevo[] = new  String[3];
                nuevo[0] = referecia;
                nuevo[1] = cantidad;
                nuevo[2] = nombre;
                lista.add(nuevo);
            }
            tope++;
        } else {
            System.out.println("La Pila de "+type+" se Encuentra llena");
        }
    }

    private boolean addValidation(Stream<String> stringStream) {
        return stringStream.anyMatch(s -> s.equalsIgnoreCase("Elemento procesado"));
    }

    public String removeOrEditProduct(String referecia, String cantidad) {
        String state = "";
        if (covacio()) {
            System.out.println("No existen Facturas de "+type+" a Ejecutar");
        } else {
            if(cantidad != null){
                lista.remove(lista.removeIf(product -> product[0].equalsIgnoreCase(referecia)));
            }else{
                Stream<String> stringStream = procesar(lista, referecia, cantidad);
            }
            tope--;
        }
        return state;
    }

    private Stream<String> procesar(Collection<String[]> lista, String referecia, String cantidad) {
        return lista.stream().map(product -> {
            if (product[0].equalsIgnoreCase(referecia)){
                product[1]=cantidad;
                return "Elemento procesado";
            };
            return "Elemento no procesado";
        });
    }

    public boolean covacio() {
        return tope == 0;
    }

    public abstract void getOrden ();
}
