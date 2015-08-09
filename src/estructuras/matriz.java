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
public class matriz {
    
    public nodoM inicial;
    public int filas;
    public int columnas;
    public nodoM personaje;
    public nodoM castillo;
    
    public void crear(int fila ,int columna)// se crea una matriz de tamaño f,c vacia
    {
        filas = fila;
        columnas = columna;
        int i = 0, j;
        inicial= new nodoM(0,0,0,"","/imagenes/fondo.png");
                
        nodoM aux;
        nodoM aux2;
        nodoM aux3;
        
        aux= inicial;
        aux2= inicial;
        
        while( i < fila){//si inician las filas         
            j = 1;
            if(i != 0)
            {
                nodoM nuevo = new nodoM(i, 0, 0,"","/imagenes/fondo.png");
                aux.setArriba(nuevo);
                nuevo.setAbajo(aux);
                aux = nuevo;
                aux2 = nuevo;  
            }
            
            while(j < columna)//se inician las columnas
            {
                nodoM nuevo1 = new nodoM(i,j,0,"","/imagenes/fondo.png");
                aux2.setDerecha(nuevo1);
                nuevo1.setIzquierda(aux2);
                aux2 = nuevo1;
                j++;
            }
            
            i++;
        }
        
        i=0;j=1;
        aux = inicial;
        aux2 = inicial;
        aux3 = inicial.getArriba();
        
        while(i < fila - 1)
        {
            while(j <= columna)
            {
               
                    aux2.setArriba(aux3);
                    aux3.setAbajo(aux2);
                    aux2 = aux2.getDerecha();
                    aux3 = aux3.getDerecha(); 
            
                j++;
            }
            
            i++;
            j=1;
           aux = aux.getArriba();
           aux2 = aux;
           aux3 = aux.getArriba();
        }
        
    }
    
    public void crear_fila(){
        nodoM aux = inicial;
        while(aux.getArriba()!=null)  aux = aux.getArriba();
        int i=1;
        int act;
        act = aux.getFila() +1; 
        
        //se crea el primer nodo
        nodoM nuevo = new nodoM(act,0,0,"","/imagenes/fondo.png");
        aux.setArriba(nuevo);
        nuevo.setAbajo(aux);
        aux = nuevo;
        
        //se crea el resto de nodos con sus apuntadores derecha e izquierda
        while(i < columnas)
        {
            nodoM agregar = new nodoM(act, i, 0,"","/imagenes/fondo.png");
            aux.setDerecha(agregar);
            agregar.setIzquierda(aux);
            aux = aux.getDerecha();
            i++;
        }
               
        //se agregan los apuntadores arriba y abajo
        i=0;
        aux  = nuevo.getAbajo();
        while(i < columnas){
            aux.setArriba(nuevo);
            nuevo.setAbajo(aux);
            aux = aux.getDerecha();
            nuevo = nuevo.getDerecha();
            i++;
        }
        
        filas++;//se agrega una fila mas
    }
    
    public void crear_columna(){
        nodoM aux = inicial;
        
        while(aux.getDerecha() != null)  aux = aux.getDerecha(); //se consigue el nodo mas a la derecha
        
        int act;
        act = aux.getColumna() + 1;
        
        nodoM ult = new nodoM(0,act,0,"","/imagenes/fondo.png");
        aux.setDerecha(ult);
        ult.setIzquierda(aux);
        int i=1;
        aux = aux.getArriba();//se busca el nodo de arriba para recorrer hacia arriba
        
        while(aux != null){
            nodoM nuevo = new nodoM(i,act,0,"","/imagenes/fondo.png");
            aux.setDerecha(nuevo);
            nuevo.setIzquierda(aux);
            nuevo.setAbajo(ult);
            ult.setArriba(nuevo);
            
            aux = aux.getArriba();
            ult = nuevo;
            i++;
        }
        
        columnas++;
    }
    
    public void eliminar_fila(int fila){
        
        nodoM aux = inicial;
        
        if(aux.getFila() == fila)  inicial = aux.getArriba();
        
        while(aux.getFila() != fila)  aux = aux.getArriba();
        
        while(aux != null){
            if(aux.getAbajo() != null && aux.getArriba() != null ){
                aux.getAbajo().setArriba(aux.getArriba());
                aux.getArriba().setAbajo(aux.getAbajo());
            }else if (aux.getAbajo() != null){
                aux.getAbajo().setArriba(null);
            }else if (aux.getArriba() != null){
                aux.getArriba().setAbajo(null);
            }
            
            aux = aux.getDerecha();
        }
    
        filas--;
    }
    
    public void eliminar_columna(int columna){
        nodoM aux = inicial;
        
        if(aux.getColumna() == columna)  inicial = aux.getDerecha();
            
        while(aux.getColumna() != columna) aux = aux.getDerecha();
        
        while(aux != null)
        {
            if(aux.getIzquierda() != null && aux.getDerecha() != null)
            {
                aux.getIzquierda().setDerecha(aux.getDerecha());
                aux.getDerecha().setIzquierda(aux.getIzquierda());
            }else if(aux.getDerecha() != null){
                aux.getDerecha().setIzquierda(null);
            }else if(aux.getIzquierda() != null){
                aux.getIzquierda().setDerecha(null);
            }
            
            aux = aux.getArriba();
        }
        
        columnas--;
    }
    
    public void mostrar(){
        nodoM aux = inicial;//para recorrer filas
        nodoM aux2 = inicial;//para recorrer columnas
        
        while (aux != null) { //se recorren las filas
            while (aux2 !=null)//se recorren las columnas
            {
                System.out.println(aux2.getFila() + "," + aux2.getColumna());
                if(aux2.getArriba() != null){
                    System.out.println("arr " + aux2.getArriba().getFila() + "," + aux2.getArriba().getColumna());
                }
                
                if(aux2.getDerecha() != null){
                    System.out.println("der " + aux2.getDerecha().getFila() + "," + aux2.getDerecha().getColumna());
                }
                
                if(aux2.getIzquierda() != null){
                    System.out.println("izq " + aux2.getIzquierda().getFila() + "," + aux2.getIzquierda().getColumna());
                }
                
                if(aux2.getAbajo() != null){
                    System.out.println("aba " + aux2.getAbajo().getFila() + "," + aux2.getAbajo().getColumna());
                }
                
                aux2 = aux2.getDerecha();
            }
            System.out.println("fin de fila");
            aux = aux.getArriba();
            aux2 = aux;
        }
        
        System.out.println("numero de filas: " + filas);
        System.out.println("numero de colum: " + columnas);
    }
    
    public void eliminar_matriz(){
        inicial = null;
    }
    
    public nodoM devolver_nodo(int fila, int col){
        nodoM aux = inicial;
  
        while(aux.getFila() != fila)  aux = aux.getArriba();
        
        while(aux.getColumna() != col)  aux = aux.getDerecha();
        
        return aux;
    }
    
    public void graficar(){
        //aqui debo poner la de graphviz
        //terminar esto mañana XD
        
    }
}
