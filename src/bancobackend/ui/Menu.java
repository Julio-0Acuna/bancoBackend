/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancobackend.ui;

import bancobackend.modelo.Transaccion;
import bancobackend.servicio.ServicioBanco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Julio Acuña
 */
public class Menu {
 private final Scanner sc;
    private final ServicioBanco servicio;

    public Menu() {
        this.sc = new Scanner(System.in);
        this.servicio = new ServicioBanco();
    }

    public void iniciar() {
        int opcion;

        do {
            
            boolean acceso = false; 
            for (int i = 1 ; i <=3 ; i++){
               
                System.out.println("Login: ");
                String usu= sc.nextLine();
            
                System.out.println("Contraseña: ");
                String cont = sc.nextLine();
                
                if (usu.equals("admin") && cont.equals("1234")){
                       System.out.println("Usuario y Contraseña Correcto");
                       System.out.println("\n Felicitaciones");
                       acceso = true;
                       break;
                   }else{ 
                    if (!usu.equals("admin") && !cont.equals("1234")){
                       System.out.println("Login Incorrecto");
                       System.out.println("Contraseña incorrecta");
                   }else if (!usu.equals("admin")){
                    System.out.println("Login incorrecto: ");}
                   else{           
                    System.out.println("Contraseña incorrecta ");
                }
                System.out.println("intento " + i+ " de 3 ");
                
                }
            }mostrarMenu();
            opcion = leerEntero("Elige una opcion: ");

            switch (opcion) {
                case 1 -> opcionCrearCuenta();
                case 2 -> opcionDepositar();
                case 3 -> opcionRetirar();
                case 4 -> opcionVerSaldo();
                case 5 -> opcionVerHistorial();
                case 6 -> opcionVerUltimasN();
                case 7 -> opcionReporte();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);

        System.out.println("Programa terminado.");
    }

    // ===================== OPCIONES =====================

    private void opcionCrearCuenta() {
        System.out.println("\n--- CREAR CUENTA ---");
        System.out.print("Nombre del titular: ");
        String nombre = sc.nextLine().trim();

        while (nombre.isEmpty()) {
            System.out.print("Nombre no puede estar vacio. Intente de nuevo: ");
            nombre = sc.nextLine().trim();
        }

        try {
            int id = servicio.crearCuenta(nombre);
            System.out.println("✅ Cuenta creada. ID: " + id);
        } catch (SQLException e) {
            System.out.println("❌ Error SQL al crear cuenta.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void opcionDepositar() {
        System.out.println("\n--- DEPOSITAR ---");
        int id = leerEntero("ID cuenta: ");
        double monto = leerDouble("Monto a depositar: ");

        while (monto <= 0) {
            System.out.println("Monto invalido. Debe ser mayor que 0.");
            monto = leerDouble("Monto a depositar: ");
        }

        try {
            servicio.depositar(id, monto);
            System.out.println("✅ Deposito realizado.");
            System.out.println("Saldo actual: " + servicio.verSaldo(id));
        } catch (SQLException e) {
            System.out.println("❌ Error SQL en deposito.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void opcionRetirar() {
        System.out.println("\n--- RETIRAR ---");
        int id = leerEntero("ID cuenta: ");
        double monto = leerDouble("Monto a retirar: ");

        while (monto <= 0) {
            System.out.println("Monto invalido. Debe ser mayor que 0.");
            monto = leerDouble("Monto a retirar: ");
        }

        try {
            servicio.retirar(id, monto);
            System.out.println("✅ Retiro realizado.");
            System.out.println("Saldo actual: " + servicio.verSaldo(id));
        } catch (SQLException e) {
            System.out.println("❌ Error SQL en retiro.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void opcionVerSaldo() {
        System.out.println("\n--- VER SALDO ---");
        int id = leerEntero("ID cuenta: ");

        try {
            double saldo = servicio.verSaldo(id);
            System.out.println("Saldo actual: " + saldo);
        } catch (SQLException e) {
            System.out.println("❌ Error SQL al ver saldo.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void opcionVerHistorial() {
        System.out.println("\n--- HISTORIAL COMPLETO ---");
        int id = leerEntero("ID cuenta: ");

        try {
            ArrayList<Transaccion> lista = servicio.verHistorial(id);

            if (lista.isEmpty()) {
                System.out.println("No hay transacciones.");
                return;
            }

            // ✅ FOR para mostrar
            for (int i = 0; i < lista.size(); i++) {
                Transaccion t = lista.get(i);
                System.out.println((i + 1) + ") " + t.getTipo()
                        + " | " + t.getMonto()
                        + " | " + t.getFecha());
            }

        } catch (SQLException e) {
            System.out.println("❌ Error SQL al ver historial.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void opcionVerUltimasN() {
        System.out.println("\n--- ULTIMAS N TRANSACCIONES ---");
        int id = leerEntero("ID cuenta: ");
        int n = leerEntero("Cuantas quieres ver?: ");

        while (n <= 0) {
            System.out.println("N debe ser mayor que 0.");
            n = leerEntero("Cuantas quieres ver?: ");
        }

        try {
            ArrayList<Transaccion> lista = servicio.verUltimasN(id, n);

            if (lista.isEmpty()) {
                System.out.println("No hay transacciones.");
                return;
            }

            // ✅ FOR para mostrar
            for (int i = 0; i < lista.size(); i++) {
                Transaccion t = lista.get(i);
                System.out.println((i + 1) + ") " + t.getTipo()
                        + " | " + t.getMonto()
                        + " | " + t.getFecha());
            }

        } catch (SQLException e) {
            System.out.println("❌ Error SQL al ver ultimas N.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void opcionReporte() {
        System.out.println("\n--- REPORTE GENERAL ---");
        try {
            int total = servicio.totalTransacciones();
            System.out.println("Total de transacciones en el banco: " + total);
        } catch (SQLException e) {
            System.out.println("❌ Error SQL en reporte.");
            e.printStackTrace();
        }
    }

    // ===================== HELPERS (VALIDACION) =====================

    private void mostrarMenu() {
        System.out.println("\n========= BANCO (OOP) =========");
        System.out.println("1. Crear cuenta");
        System.out.println("2. Depositar");
        System.out.println("3. Retirar");
        System.out.println("4. Ver saldo");
        System.out.println("5. Ver historial");
        System.out.println("6. Ver ultimas N");
        System.out.println("7. Reporte general");
        System.out.println("0. Salir");
        System.out.println("===============================");
    }

    private int leerEntero(String msg) {
        while (true) {
            System.out.print(msg);
            if (sc.hasNextInt()) {
                int v = sc.nextInt();
                sc.nextLine(); // limpiar
                return v;
            } else {
                System.out.println("Entrada invalida. Debe ser numero entero.");
                sc.nextLine();
            }
        }
    }

    private double leerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            if (sc.hasNextDouble()) {
                double v = sc.nextDouble();
                sc.nextLine(); // limpiar
                return v;
            } else {
                System.out.println("Entrada invalida. Debe ser numero.");
                sc.nextLine();
            }
        }
    }
}