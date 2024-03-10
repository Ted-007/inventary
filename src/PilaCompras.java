import java.util.ArrayList;

public class PilaCompras extends Pila {

    public PilaCompras(int s) {
        super(s, "Compras");
    }

    @Override
    public void getOrden (){
        System.out.println("Orden de compra generada: ");
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
