package proyectobimestralpro2;

import java.util.Formatter;
import java.util.Locale;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Desktop;

public class ProyectoBimestralPro2 {
    static int diaElegido;
    public static void main(String[] args) throws IOException {
        Random aleatorio = new Random();
        Scanner tecla = new Scanner(System.in);
        int filas = 18, columnas = 9, horario, cantidadNormal, cantidadPrefer, numCli = 0, totalCantidad, opcionMetPago, contrasena, contSuerte=0;
        double tarifaNormal = 0, tarifaPreferencial = 0, totalNormal, totalPreferencial, totalAPagar,
                acumuladorTotales = 0, acumEntrNormals = 0, acumEntrPref = 0;
        String respuesta, nombreCliente, diaAsis, fechaAsis, mesAsis, resp, participaSorteo;
        String[][] archivoJava = new String[filas][columnas];
        int[][] entradasPorDia = new int[filas][2];
        
        do {
            lecturaArchivoPrin(archivoJava);
            System.out.println("o---------> NOTA <-----");
            System.out.println(
                    "El dia con funciones ya sea especiales, nacionales o internacionales el precio del boleto aumenta a partir de las 18:00 horas hasta las 02:00 horas");
            System.out.println(
                    "___________________________________________FERIA INTERNACIONAL DE LOJA 2024______________________________________________");
            System.out.println(
                    "--------------------------------+---------------+-------+---------------+-----------------------+-------+-------+-------+-------+");
            System.out.println(
                    "     ARTISTAS \t\t\t| DIAS \t\t| NUM \t|\t MES \t|\t FUNCIONES \t|NORMAL2|PREFER2|NORMAL1|PREFER1|");
            System.out.println(
                    "--------------------------------+---------------+-------+---------------+-----------------------+-------+-------+-------+-------+");
            imprimirArchivo(archivoJava, filas, columnas);
            System.out.println(
                    "=================================================================================================================================");

            System.out.print("Ingrese el nombre del cliente: ");
            nombreCliente = tecla.nextLine();
            System.out.println("o------> Hola " + nombreCliente
                    + " a continuacion se le mostrara la informacion de su solicitud =D.");
            System.out.print("Ingrese el nombre del dia del cual desea asistir (Ejemplo: Sabado): ");
            diaAsis = tecla.nextLine();
            System.out.print("Ingrese el dia del cual desea asistir (Ejemplo:7): ");
            fechaAsis = tecla.nextLine();
            System.out.print("Ingrese el mes del cual desea asistir (Ejemplo: Septiembre): ");
            mesAsis = tecla.nextLine();
            do {
                System.out.println( "------------------------------------------------------------------------------------------------------------------");
                System.out.println(
                        "Ingrese el horario el cual desea asistir (ingrese el numeral del horario a escoger): \n 1. Horario Normal (Empieza a partir de las 02:01 am hasta las 17:59 pm) \n 2. Horario Nocturno  (Empieza a partir de las 18:00 pm hasta las 02:00 am)");
                horario = tecla.nextInt();
                tarifaNormal = tarifaNormal(diaAsis, fechaAsis, mesAsis, horario);
                tarifaPreferencial = tarifaPreferencial(diaAsis, fechaAsis, mesAsis, horario);
            } while (horario != 1 && horario != 2);

            System.out.println("---------------------------------------------------------------");
            imprimirInfoClien(archivoJava, fechaAsis, diaAsis, mesAsis, tarifaNormal, tarifaPreferencial);
            System.out.println("---------------------------------------------------------------");
            System.out.print("Cuantas entradas de tarifa normal desea comprar?: ");
            cantidadNormal = tecla.nextInt();
            System.out.print("Cuantas entradas de tarifa preferencial desea comprar?: ");
            cantidadPrefer = tecla.nextInt();
            System.out.println();
            totalCantidad = (cantidadNormal + cantidadPrefer);
            totalNormal = (tarifaNormal * cantidadNormal);
            totalPreferencial = (tarifaPreferencial * cantidadPrefer);
            totalAPagar = (totalNormal + totalPreferencial);

            if (totalCantidad > 5) {
                System.out.print("Supera la compra de 5 boletos. Desea participar en un sorteo para ganar un boleto a una entrada normal (Si/no) ?: ");
                participaSorteo = tecla.next();
                if (participaSorteo.equalsIgnoreCase("si")) {
                    int numeroSorteo = aleatorio.nextInt(15) + 1;
                    System.out.printf("Su numero de sorteo es: %d%n", numeroSorteo);
                    if (numeroSorteo == 3 || numeroSorteo == 6 || numeroSorteo == 9) {
                        System.out.println("----------> Felicitaciones, ha ganado un boleto gratis para el ultimo dia de la feria!");
                    } else {
                        System.out.println("----------> Lo sentimos, no ha ganado en esta ocasion. Suerte para la proxima!");
                        System.out.println("El numero del sorteo ganador es 3, 6 o 9");
                    }
                }
            }
            do {
                System.out.println("METODO DE PAGO <-----------------------");
                System.out.println("1. Efectivo");
                System.out.println("2. Tarjeta SITU");
                System.out.println("Ingrese el metodo de pago que utilizo: ");
                opcionMetPago = tecla.nextInt();
                switch (opcionMetPago) {
                    case 1:
                        System.out.println("=====================================================================");
                        System.out.println("---------FACTURA FINAL------------------");
                        System.out.println("Metodo de Pago: EFECTIVO");
                        System.out.println("Cliente: " + nombreCliente);
                        System.out.println("Compro entradas para el dia " + diaAsis + " " + fechaAsis + " " + mesAsis);
                        System.out.println("Comprando " + cantidadNormal + " entradas normales ($" + tarifaNormal
                                + ") y dandole un costo de $" + totalNormal);
                        System.out.println(
                                "Comprando " + cantidadPrefer + " entradas preferenciales ($" + tarifaPreferencial
                                        + ") y dandole un costo de $" + totalPreferencial);
                        System.out.println("El costo final de las entradas es de $" + totalAPagar);
                        System.out.println("----------------------------------------------------------");
                        break;
                    case 2:
                        System.out.println("Introduzca el ID de su tarjeta SITU: ");
                        String idTarjeta = tecla.next();
                        System.out.println("La transaccion fue realizada con exito!");
                        System.out.println("=====================================================================");
                        System.out.println("---------FACTURA FINAL------------------");
                        System.out.println("Metodo de Pago: Tarjeta SITU");
                        System.out.println("ID: " + idTarjeta);
                        System.out.println("Cliente: " + nombreCliente);
                        System.out.println("Compro entradas para el dia " + diaAsis + " " + fechaAsis + " " + mesAsis);
                        System.out.println("Comprando " + cantidadNormal + " entradas normales ($" + tarifaNormal+ ") y dandole un costo de $" + totalNormal);
                        System.out.println("Comprando " + cantidadPrefer + " entradas preferenciales ($" + tarifaPreferencial + ") y dandole un costo de $" + totalPreferencial);
                        System.out.println("El costo final de las entradas es de $" + totalAPagar);
                        System.out.println("----------------------------------------------------------");
                        break;
                    default:
                        System.out.println("Opción incorrecta, vuelva a intentarlo.");
                        break;
                }
            
            } while (opcionMetPago != 1 && opcionMetPago != 2);
            System.out.print("Desea ingresar la compra de un nuevo cliente? (Si/No): ");
            respuesta = tecla.next();
            tecla.nextLine();

            diaElegido = Integer.parseInt(fechaAsis)-1;
            entradasPorDia[diaElegido][1] += cantidadNormal + cantidadPrefer ;
            acumuladorTotales += totalAPagar;
            acumEntrNormals += cantidadNormal;
            acumEntrPref += cantidadPrefer;

            generarEstadistica(diaElegido , entradasPorDia , acumuladorTotales , acumEntrNormals);
            numCli = numCli + 1;
            if (respuesta.equalsIgnoreCase("No")) {
                do {
                    System.out.println("------------------------------------------------------");
                    System.out.println("Que tipo de estadistica desea ver? (1/2):  ");
                    System.out.println("1. Estadisticas generales");
                    System.out.println("2. Estadistica detallada (Personal Autorizado) ");
                    int opcEstadistica = tecla.nextInt();
                    switch (opcEstadistica) {
                        case 1:
                            System.out.println(
                                    "------------------------------------------------------------------ESTADISTICA FINAL-------------------------------------------------------------------------------------");
                            System.out.println("\n\tEstadísticas Generales");
                            System.out.println("Numero de Clientes que realizaron una compra: " + numCli);
                            System.out.println("El costo total de entradas normales adquiridas en todo el evento es de: " + acumEntrNormals);
                            System.out
                                    .println("El costo total de entradas preferenciales adquiridas en todo el evento es de: " + acumEntrPref);
                            System.out.println(
                                    "El ingreso que se obtuvo de todo el evento de la Feria Internacional de Loja 2024 es de: $" + acumuladorTotales);
                            System.out.println("El dia que se ingreso para asistir: "+ diaElegido);
                            break;
                        case 2:
                            do {
                                System.out.print("Ingrese la contrasena:");
                                contrasena = tecla.nextInt();
                            } while (contrasena != 2000);
                            if(contrasena == 2000)
                                System.out.println("\n\tEstadística Detallada");
                                abrirArchivo("Estadisticas_Dia"+diaElegido+".csv");
                            
                            break;
                    }
                    System.out.println("=======================================");
                    System.out.println("Desea probar las otras opciones? (Si/No)");
                    resp = tecla.next();
                } while (resp.equalsIgnoreCase("Si"));
            }
        } while (respuesta.equalsIgnoreCase("Si"));
    }

    public static void lecturaArchivoPrin(String archivoJava[][]) {
        try {
            Scanner archivo = new Scanner(new File("fechaFija.csv"));
            int i = 0;
            while (archivo.hasNext()) {
                String[] partes = archivo.nextLine().split(";");
                archivoJava[i][0] = partes[0];
                archivoJava[i][1] = partes[1];
                archivoJava[i][2] = partes[2];
                archivoJava[i][3] = partes[3];
                archivoJava[i][4] = partes[4];
                archivoJava[i][5] = partes[5];
                archivoJava[i][6] = partes[6];
                archivoJava[i][7] = partes[7];
                archivoJava[i][8] = partes[8];
                i++;
            }
            archivo.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void imprimirArchivo(String archivoJava[][], int filas, int columnas) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(archivoJava[i][j] + " \t|");
            }
            System.out.println(" ");
        }
    }

    public static double tarifaNormal(String diaAsis, String fechaAsis, String mesAsis, int horario ) {
        double tarifaNormal=0;
        if (("Sabado".equals(diaAsis) && "31".equals(fechaAsis) && "Agosto".equals(mesAsis))
                || ("Martes".equals(diaAsis) && "17".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
            if (horario == 2) {
                tarifaNormal = 10.00;
            } else {
                tarifaNormal = 1.50;
            }
        } else {
            if (("Sabado".equals(diaAsis) && "14".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                    || ("Lunes".equals(diaAsis) && "16".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
                if (horario == 2) {
                    tarifaNormal = 7.00;
                } else {
                    tarifaNormal = 1.50;
                }
            } else {
                if (("Domingo".equals(diaAsis) && "1".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Viernes".equals(diaAsis) && "6".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Sabado".equals(diaAsis) && "7".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Viernes".equals(diaAsis) && "13".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Domingo".equals(diaAsis) && "15".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
                    if (horario == 2) {
                        tarifaNormal = 5.00;
                    } else {
                        tarifaNormal = 1.50;
                    }
                } else {
                    if (horario == 2) {
                        tarifaNormal = 1.50;
                    } else {
                        tarifaNormal = 1.50;
                    }
                }
            }
        }
        return tarifaNormal;
    }

    public static double tarifaPreferencial(String diaAsis, String fechaAsis, String mesAsis, int horario) {
        double tarifaPreferencial;
        if (("Sabado".equals(diaAsis) && "31".equals(fechaAsis) && "Agosto".equals(mesAsis))
                || ("Martes".equals(diaAsis) && "17".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
            if (horario == 2) {
                tarifaPreferencial = 5.00;
            } else {
                tarifaPreferencial = 0.75;
            }
        } else {
            if (("Sabado".equals(diaAsis) && "14".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                    || ("Lunes".equals(diaAsis) && "16".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
                if (horario == 2) {
                    tarifaPreferencial = 3.50;
                } else {
                    tarifaPreferencial = 0.75;
                }
            } else {
                if (("Domingo".equals(diaAsis) && "1".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Viernes".equals(diaAsis) && "6".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Sabado".equals(diaAsis) && "7".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Viernes".equals(diaAsis) && "13".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Domingo".equals(diaAsis) && "15".equals(fechaAsis)
                                && "Septiembre".equals(mesAsis))) {
                    if (horario == 2) {
                        tarifaPreferencial = 2.50;
                    } else {
                        tarifaPreferencial = 0.75;
                    }
                } else {
                    if (horario == 2) {
                        tarifaPreferencial = 0.75;
                    } else {
                        tarifaPreferencial = 0.75;
                    }
                }
            }
        }
        return tarifaPreferencial;
    }
    
    public static void imprimirInfoClien(String archivoJava[][],String fechaAsis, String diaAsis, String mesAsis, double tarifaNormal, double tarifaPreferencial ){
        for (int i = 0; i < archivoJava.length; i++) {
            if (fechaAsis.equals(archivoJava[i][2])) {
                System.out.println(archivoJava[i][4]);
                System.out.println("---> Ese día es " + diaAsis + " " + fechaAsis + " de " + mesAsis);
                System.out.println("---> El valor del boleto normal por persona es de: " + tarifaNormal);
                System.out.println("---> El valor del boleto preferencial por persona es de: " + tarifaPreferencial);
                break;
            }
        }
    }

    public static void generarEstadistica(int dia , int[][] matriz , double acumuladorTotales , double acumEntrNormals){
        try {
            String nombreArchivo = "Estadisticas_Dia"+dia+".csv";
            Formatter escritura = new Formatter(nombreArchivo , "UTF-8", Locale.US);            
            escritura.format("%s\n","~~~~~~~~~ Estadisticas ~~~~~~~~~~");
            escritura.format("%s%d\n","Dia Elegido: ", dia+1);
            escritura.format("%s%.0f\n","Entradas Tarifa Normal Acumuladas: ", acumEntrNormals);
            escritura.format("%s%.0f\n","Entradas Tarifa Preferencial Acumuladas: ", (matriz[dia][1]-acumEntrNormals));
            escritura.format("%s%d\n","Numero Total Entradas: ", matriz[dia][1]);
            escritura.format("%s%.2f\n","Ingresos Totales: ", acumuladorTotales);
            escritura.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void abrirArchivo(String rutaArchivo) throws IOException {
        File archivoCSV = new File(rutaArchivo);

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (archivoCSV.exists()) {
                desktop.open(archivoCSV);
            } else {
                System.out.println("El archivo CSV no existe en la ruta especificada.");
            }
        } else {
            System.out.println("La apertura de archivos no es compatible en este sistema.");
        }
    }
}
/*
 o---------> NOTA <-----
El dia con funciones ya sea especiales, nacionales o internacionales el precio del boleto aumenta a partir de las 18:00 horas hasta las 02:00 horas
___________________________________________FERIA INTERNACIONAL DE LOJA 2024______________________________________________
--------------------------------+---------------+-------+---------------+-----------------------+-------+-------+-------+-------+
     ARTISTAS                   | DIAS          | NUM   |        MES    |        FUNCIONES      |NORMAL2|PREFER2|NORMAL1|PREFER1|
--------------------------------+---------------+-------+---------------+-----------------------+-------+-------+-------+-------+
MELENDI                         |Sabado         |31     |Agosto         |Funcion Especial       |10.00  |5.00   |1.50   |1.50   | 
GERARDO MORAN                   |Domingo        |1      |Septiembre     |Funcion Nacional       |5.00   |2.50   |1.50   |1.50   | 
---------------------------     |Lunes          |2      |Septiembre     |Sin Funcion            |1.50   |0.75   |1.50   |1.50   | 
---------------------------     |Martes         |3      |Septiembre     |Sin Funcion            |1.50   |0.75   |1.50   |1.50   | 
---------------------------     |Miercoles      |4      |Septiembre     |Sin Funcion            |1.50   |0.75   |1.50   |1.50   | 
---------------------------     |Jueves         |5      |Septiembre     |Sin Funcion            |1.50   |0.75   |1.50   |1.50   | 
JAYAC                           |Viernes        |6      |Septiembre     |Funcion Nacional       |5.00   |2.50   |1.50   |1.50   | 
---------------------------     |Sabado         |7      |Septiembre     |Funcion Nacional       |5.00   |2.50   |1.50   |1.50   | 
---------------------------     |Domingo        |8      |Septiembre     |Sin Funcion            |1.50   |0.75   |1.50   |1.50   | 
---------------------------     |Lunes          |9      |Septiembre     |Sin Funcion            |1.50   |0.75   |1.50   |1.50   | 
---------------------------     |Martes         |10     |Septiembre     |Sin Funcion            |1.50   |0.75   |1.50   |1.50   | 
---------------------------     |Miercoles      |11     |Septiembre     |Sin Funcion            |1.50   |0.75   |1.50   |1.50   | 
---------------------------     |Jueves         |12     |Septiembre     |Sin Funcion            |1.50   |0.75   |1.50   |1.50   | 
ALEJANDRO LERNER                |Viernes        |13     |Septiembre     |Funcion Nacional       |5.00   |2.50   |1.50   |1.50   | 
CHYNO MIRANDA                   |Sabado         |14     |Septiembre     |Funcion Internacional  |7.00   |3.50   |1.50   |1.50   | 
DON MEDARDO Y SUS PLAYERS       |Domingo        |15     |Septiembre     |Funcion Nacional       |5.00   |2.50   |1.50   |1.50   | 
VALLENATOS "BINOMIO DE ORO"     |Lunes          |16     |Septiembre     |Funcion Internacional  |7.00   |3.50   |1.50   |1.50   | 
LOS PRISIONEROS                 |Martes         |17     |Septiembre     |Funcion Especial       |10.00  |5.00   |1.50   |1.50   | 
=================================================================================================================================
Ingrese el nombre del cliente: Paula
o------> Hola Paula a continuacion se le mostrara la informacion de su solicitud =D.
Ingrese el nombre del dia del cual desea asistir (Ejemplo: Sabado): Lunes
Ingrese el dia del cual desea asistir (Ejemplo:7): 16
Ingrese el mes del cual desea asistir (Ejemplo: Septiembre): Septiembre
------------------------------------------------------------------------------------------------------------------
Ingrese el horario el cual desea asistir (ingrese el numeral del horario a escoger): 
 1. Horario Normal (Empieza a partir de las 02:01 am hasta las 17:59 pm)
 2. Horario Nocturno  (Empieza a partir de las 18:00 pm hasta las 02:00 am)
2
---------------------------------------------------------------
Funcion Internacional
---> Ese día es Lunes 16 de Septiembre
---> El valor del boleto normal por persona es de: 7.0
---> El valor del boleto preferencial por persona es de: 3.5
---------------------------------------------------------------
Cuantas entradas de tarifa normal desea comprar?: 5
Cuantas entradas de tarifa preferencial desea comprar?: 2

Supera la compra de 5 boletos. Desea participar en un sorteo para ganar un boleto a una entrada normal (Si/no) ?: Si
Su numero de sorteo es: 3
----------> Felicitaciones, ha ganado un boleto gratis para el ultimo dia de la feria!
METODO DE PAGO <-----------------------
1. Efectivo
2. Tarjeta SITU
Ingrese el metodo de pago que utilizo:
2
Introduzca el ID de su tarjeta SITU:
12563
La transaccion fue realizada con exito!
=====================================================================
---------FACTURA FINAL------------------
Metodo de Pago: Tarjeta SITU
ID: 12563
Cliente: Paula
Compro entradas para el dia Lunes 16 Septiembre
Comprando 5 entradas normales ($7.0) y dandole un costo de $35.0
Comprando 2 entradas preferenciales ($3.5) y dandole un costo de $7.0
El costo final de las entradas es de $42.0
----------------------------------------------------------
Desea ingresar la compra de un nuevo cliente? (Si/No): no
Que tipo de estadistica desea ver? (1/2):  
1. Estadisticas generales
2. Estadistica detallada (Personal Autorizado)
2
Ingrese la contrasena:2000

        Estadística Detallada
=======================================
Desea probar las otras opciones? (Si/No)
no
*/