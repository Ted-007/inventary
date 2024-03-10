import java.util.ArrayList;

public class PilaVentas extends Pila {
    public PilaVentas(int s) {
        super(s, "Ventas");
    }

    @Override
    public void getOrden (){
        System.out.println("Factura de venta generada: ");
        System.out.println("___________________________________");
        int numProdut = 0;
        for(String[] s: this.lista){
            numProdut++;
            System.out.println("producto nro: "+numProdut);
            System.out.println("*********************\n"+
                    "Nombre:"+s[2]+"\n"+
                    "Referencia:"+s[0]+"\n" +
                    "Cantidad:"+s[1]+"\n" +
                    "**********************\n");
        }
        System.out.println("___________________________________");
    }
}