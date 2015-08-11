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
public class nodoM extends JLabel {

    public nodoM(int fila, int columna, int valor, String nombre, String ruta) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
        this.tipo = 0;
        this.ruta =ruta;
        this.nombre = nombre;
        this.direc = 0;// cero para derecha 1 para izquierda
        this.izquierda = null;
        this.derecha = null;
        this.abajo = null;
        this.arriba = null;
    }
    
    
    private int fila;
    private int columna;
    private int valor;// si esta cupado es 1 y si esta vacio es 0
    private int tipo;
    private int direc;//para los goobas y tortugas
    private String ruta;
    private String nombre;
    private nodoM arriba;
    private nodoM abajo;
    private nodoM izquierda;
    private nodoM derecha;

    /**
     * @return the fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the arriba
     */
    public nodoM getArriba() {
        return arriba;
    }

    /**
     * @param arriba the arriba to set
     */
    public void setArriba(nodoM arriba) {
        this.arriba = arriba;
    }

    /**
     * @return the abajo
     */
    public nodoM getAbajo() {
        return abajo;
    }

    /**
     * @param abajo the abajo to set
     */
    public void setAbajo(nodoM abajo) {
        this.abajo = abajo;
    }

    /**
     * @return the izquierda
     */
    public nodoM getIzquierda() {
        return izquierda;
    }

    /**
     * @param izquierda the izquierda to set
     */
    public void setIzquierda(nodoM izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * @return the derecha
     */
    public nodoM getDerecha() {
        return derecha;
    }

    /**
     * @param derecha the derecha to set
     */
    public void setDerecha(nodoM derecha) {
        this.derecha = derecha;
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

    /**
     * @return the direc
     */
    public int getDirec() {
        return direc;
    }

    /**
     * @param direc the direc to set
     */
    public void setDirec(int direc) {
        this.direc = direc;
    }
}
