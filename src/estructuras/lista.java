/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 *
 * @author Raulk
 */
public class lista {
    listaNodo inicial;
    listaNodo ultimo;
    int numerado;
    
    public void iniciar()
    {
       inicial = null;
       ultimo = null;
       numerado = 0;
    }
    
    public void insertar(String nombre, int tipo)
    {
        
        if (inicial != null) {
            numerado++;
            listaNodo nuevo = new listaNodo(numerado,nombre, tipo);
            listaNodo aux = inicial;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
            nuevo.setAnterior(aux);
            ultimo = nuevo;

        } else {
            numerado++;
            listaNodo nuevo = new listaNodo(numerado,nombre, tipo);
            inicial = nuevo;
            ultimo = nuevo;
        }
    }
    
    
    public listaNodo sacar_pila(){
        
        listaNodo aux = inicial;

        if (aux != null) {
            if (aux.getSiguiente() != null) {
                inicial = aux.getSiguiente();
                inicial.setAnterior(null);
                return aux;
            } else {
                inicial = null;
                ultimo = null;
                return aux;
            }
        } else {
            return null;
        }

    }
    
    public listaNodo sacar_cola(){
        listaNodo aux = ultimo;
        if(aux != null){
            if(aux.getAnterior() != null){
                ultimo = aux.getAnterior();
                ultimo.setSiguiente(null);
                return aux;
            } else {
                ultimo = null;
                inicial = null;
                return aux;
            }
        }else{
           return null; 
        }
    }
    
    public listaNodo modificar(int numero)
    {
        listaNodo aux = inicial;
        while(aux != null)
        {
            if(aux.getCorrelativo() == numero){
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;        
    }
    
    public void mostrar(){
        listaNodo aux;
        aux = inicial;
        
        while(aux != null)
        {
            if(aux.getSiguiente() != null)  System.out.println(aux.getCorrelativo() + " -> " + aux.getSiguiente().getCorrelativo());
            else System.out.println(aux.getCorrelativo());
            
            System.out.println(aux.getNombre() + "  ");
            aux = aux.getSiguiente();
        }
    }
}