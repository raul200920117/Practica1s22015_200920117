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
    int numerado;
    
    public void iniciar()
    {
       inicial = null;
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

        } else {
            numerado++;
            listaNodo nuevo = new listaNodo(numerado,nombre, tipo);
            inicial = nuevo;
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