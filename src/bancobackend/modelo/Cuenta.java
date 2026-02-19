/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancoBackend.modelo;

/**
 *
 * @author julio
 */
public class Cuenta {
    private int id;
    private String nombre;
    private double saldo;
    
    public Cuenta(){
        
    }

    public Cuenta(int id, String nombre, double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public Cuenta(String nombre, double saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString(){
        return "Cuenta{" +
                "id=" + id +
                ", nombre=" + nombre + "\"" +
                ", saldo=" + saldo +
                "}";
    }

}