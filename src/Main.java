import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        char opc;
        boolean val = false;
        boolean active = true;
        Scanner scanner = new Scanner(System.in);
        Collection<Product> products = new ArrayList<>();
        PilaCompras pilaCompras = new PilaCompras(10);
        PilaVentas pilaVentas = new PilaVentas(10);
        productBase(products);

        while (active) {
            clearScreen();
            System.out.println("\nMenu Principal");
            System.out.println("................\n");
            System.out.println("1 Articulos del Inventario");
            System.out.println("2 Ordenes de Compras");
            System.out.println("3 Facturas de Ventas");
            System.out.println("4 Reportes del Sistema");
            System.out.println("5 Salida del Sistema");

            System.out.print("\nIngrese la Opcion a ejecutar: ");
            opc = scanner.next().charAt(0);

            val = Character.isDigit(opc) && opc >= '1' && opc <= '5';
            if (val) {
                int opcInt = Character.getNumericValue(opc);

                if (opcInt >= 1 && opcInt <= 5) {
                    if (opcInt == 1) {
                        if (val) {
                            System.out.println("\nInventario");
                            System.out.println("___________________________________");
                            int numProdut = 0;
                            for (Product product : products) {
                                numProdut++;
                                System.out.println("producto nro: " + numProdut);
                                System.out.println(product.toString());
                            }
                            System.out.println("___________________________________");
                        }
                    } else if (opcInt == 2) {
                        System.out.println("2 Ordenes de Compras");
                        inizialiceProcess(pilaCompras, opc, scanner, val, products);
                    } else if (opcInt == 3) {
                        System.out.println("3 Facturas de Ventas");
                        inizialiceProcess(pilaVentas, opc, scanner, val, products);
                    } else if (opcInt == 4) {
                        System.out.println("4 Reportes del Sistema");
                        pilaVentas.getOrden();
                        pilaCompras.getOrden();
                    } else if (opcInt == 5) {
                        System.out.println("Salida exitosa del sistema");
                        active = false;
                    }

                } else {
                    System.out.println("la opción ingresada no es valida: " + opcInt);
                    active = true;
                }
            }

        }
    }

    private static void inizialiceProcess(Pila pila, char opc, Scanner scanner, Boolean val, Collection<Product> products) {
        Boolean activeProcess = true;
        while (activeProcess){
            if(!pila.lista.isEmpty()){
                pila.getOrden();
            }
            System.out.print("\nIngrese la Opcion a ejecutar: \n");
            System.out.println("1 agregar");
            System.out.println("2 eliminar");
            String opcSellorBuild = scanner.next();
            System.out.print("\nIngrese la referencia del producto: ");
            String product = scanner.next();
            System.out.print("\nIngrese la cantidad del producto: ");
            String count = scanner.next();
            if (opcSellorBuild.equals("1")) {
                if (pila.getType().equals("Compras")) {
                    System.out.print("\nIngrese el nombre del producto: ");
                    String name = scanner.next();
                    pila.addProduct(name, product, count);
                } else {
                    validateExistProduct(products, count, product);
                    pila.addProduct(null, product, count);
                }

            } else if (opcSellorBuild.equals("2")) {

                if (!pila.lista.isEmpty()) {
                    pila.removeOrEditProduct(product, String.valueOf(count));
                    System.out.print("\nproducto procesado");
                } else {
                    System.out.print("\n>>>>>>>>sin productos en la Pila<<<<<<<<");
                }


            } else {
                System.out.print("\nOpción no valida: " + opcSellorBuild);
            }
            System.out.print("\nsi desea cerrar la operación digite la opción: (1)");
            System.out.print("\nde lo contrario marque (0)");
            String terminate = scanner.next();
            if(terminate.equals("1")){
                modifyStock(pila, products);
                activeProcess=false;
            };
        }
    }

    private static void modifyStock(Pila pila, Collection<Product> products) {
        if(!pila.lista.isEmpty()){
            AtomicReference<Boolean> addProduct = new AtomicReference<>(true);
            for(String[] s:pila.lista) {
                for(Product product: products){
                    if (product.getReferencia().equals(s[0])) {
                        addProduct.set(false);
                        if (pila.getType().equals("Compras")) {
                            product.setCantidad(product.getCantidad() + Integer.parseInt(s[1]));
                            System.out.print("\nStock actulizado con una compra");
                        } else {
                            product.setCantidad(product.getCantidad() - Integer.parseInt(s[1]));
                            System.out.print("\nStock actulizado con una venta");
                        }
                        break;
                    }

                };
                if (addProduct.get()&&pila.getType().equals("Compras")) {
                    products.add(new Product(s[2], s[0], Integer.parseInt(s[1])));
                }
            }
        }
    }

    private static void validateExistProduct(Collection<Product> products, String count, String product) {
        Stream<Product> allowCount = products.stream().filter(product1 -> product1.getReferencia().equalsIgnoreCase(product));
        Product productExists = allowCount.findFirst().get();
        if (productExists != null) {
            if (productExists.getCantidad() > Integer.parseInt(count)) {


                System.out.print("\nproducto procesado");
            } else {
                System.out.print("\nCantidad no disponible");
                //ingresar de nuevo el producto
            }
        } else {
            System.out.print("\nProducto no valido: ");
            //ingresar de nuevo el producto
        }
    }


    private static void productBase(Collection<Product> products) {
        products.add(new Product("cepillo", "1", 5));
        products.add(new Product("enjuague bucal", "2", 5));
        products.add(new Product("crema", "3", 5));
        products.add(new Product("shampoo", "4", 5));
        products.add(new Product("bloqueador", "5", 5));

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}