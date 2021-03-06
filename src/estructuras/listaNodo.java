/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import javax.swing.JLabel;

/**
 *
 * @author Raulk
 */
public class listaNodo extends JLabel {
     public listaNodo(int correlativo, String nombre, int tipo , String ruta) {
        this.correlativo = correlativo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ruta = ruta;
        this.siguiente = null;
        this.anterior = null;
    }
    
    private int correlativo;
    private String nombre;
    private int tipo;
    private String ruta;
    private listaNodo siguiente;
    private listaNodo anterior;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the siguiente
     */
    public listaNodo getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(listaNodo siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the anterior
     */
    public listaNodo getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(listaNodo anterior) {
        this.anterior = anterior;
    }

    /**
     * @return the correlativo
     */
    public int getCorrelativo() {
        return correlativo;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
