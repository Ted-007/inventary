import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Product {

    String nombre;
    String referencia;
    Integer cantidad;

    @Override
    public String toString() {
        return  "*********************\n"+
                "Nombre:"+nombre+"\n"+
                "Referencia:"+referencia+"\n" +
                "Cantidad:"+cantidad+"\n" +
                "**********************\n";
    }
}
