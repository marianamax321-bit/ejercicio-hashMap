import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class actividad2 {

    final int TIPO_A = 1, TIPO_B = 2, TIPO_C = 3, TIPO_D = 4;
    String nombreVendedor;
    double ventas, comision, porComision, sumaComisiones;
    int tipoVendedor, cantMayor;
    String menu, tipoLetra;


    HashMap<String, Double> empleadosMap;
    ArrayList<String> vendedoresDelMesList;

    public actividad2() {
        empleadosMap = new HashMap<>();
        vendedoresDelMesList = new ArrayList<>();

        menu = "TIPO VENDEDOR\n";
        menu += "1. A\n";
        menu += "2. B\n";
        menu += "3. C\n";
        menu += "4. D\n\n";
        menu += "Ingrese el tipo de vendedor";

        String repita = "";

        do {
            iniciar();
            repita = JOptionPane.showInputDialog("Quiere continuar? (si/no)");
            if (repita == null) repita = "no"; // Por si cancelan la ventana
        } while (repita.equalsIgnoreCase("si"));

        sumarComisiones();
        calcularMayores();
        imprimirDatosGenerales();
    }

    private void solicitarDatos() {
        nombreVendedor = JOptionPane.showInputDialog("Ingrese el nombre del vendedor:");
        if (nombreVendedor == null) nombreVendedor = "Sin nombre";

        String inputTipo = JOptionPane.showInputDialog(menu);
        tipoVendedor = (inputTipo != null && !inputTipo.isEmpty()) ? Integer.parseInt(inputTipo) : 1;

        String inputVentas = JOptionPane.showInputDialog("Ingrese el valor de las ventas:");
        ventas = (inputVentas != null && !inputVentas.isEmpty()) ? Double.parseDouble(inputVentas) : 0.0;
    }

    private void obtenerPocentajeComision() {
        switch (tipoVendedor) {
            case TIPO_A:
                porComision = 0.10;
                tipoLetra = "A";
                break;
            case TIPO_B:
                porComision = 0.15;
                tipoLetra = "B";
                break;
            case TIPO_C:
                porComision = 0.20;
                tipoLetra = "C";
                break;
            case TIPO_D:
                porComision = 0.25;
                tipoLetra = "D";
                break;
            default:
                porComision = 0.05;
                tipoLetra = "Desconocido";
                break;
        }
    }

    private void calcularComision() {
        comision = ventas * porComision;


        empleadosMap.put(nombreVendedor, comision);
    }

    private void calcularMayores() {
        cantMayor = 0;
        vendedoresDelMesList.clear();


        for (Map.Entry<String, Double> entry : empleadosMap.entrySet()) {
            if (entry.getValue() >= 50000) {
                cantMayor++;
                System.out.println("Empleado con comisión alta: " + entry.getKey());
                vendedoresDelMesList.add(entry.getKey());
            }
        }
    }

    private void sumarComisiones() {
        sumaComisiones = 0;

        for (double com : empleadosMap.values()) {
            sumaComisiones += com;
        }
    }

    private void imprimirDatosGenerales() {
        System.out.println("------- DATOS ALMACENADOS EMPLEADOS (HASHMAP) ------");


        for (Map.Entry<String, Double> entry : empleadosMap.entrySet()) {
            System.out.println("Comisión para " + entry.getKey() + " es : " + entry.getValue());
        }

        System.out.println("Total a pagar por comisiones: " + sumaComisiones);
        System.out.println("Cantidad empleados con comisión mayor: " + cantMayor);
        System.out.println("Vendedores destacados: " + vendedoresDelMesList);
        System.out.println("------------------------------------------");
    }

    private void iniciar() {
        solicitarDatos();
        obtenerPocentajeComision();
        calcularComision();
        imprimir();
    }

    private void imprimir() {
        String datos = "Nombre: " + nombreVendedor + "\n";
        datos += "Tipo Vendedor: " + tipoLetra + "\n";
        datos += "Ventas: " + ventas + "\n";
        datos += "Por Comisión: " + porComision + "\n";
        datos += "Comisión: " + comision + "\n";

        System.out.println("=== DATOS EMPLEADOS ===");
        System.out.println(datos);
        System.out.println("=======================");
    }

}
