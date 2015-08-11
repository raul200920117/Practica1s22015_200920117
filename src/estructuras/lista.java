/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Raulk
 */
public class lista {
    public listaNodo inicial;
    public listaNodo ultimo;
    int numerado;
    
    public void iniciar()
    {
       inicial = null;
       ultimo = null;
       numerado = 0;
    }
    
    public void insertar(String nombre, int tipo, String ruta)
    {
        
        if (inicial != null) {
            numerado++;
            listaNodo nuevo = new listaNodo(numerado,nombre, tipo, ruta);
            listaNodo aux = inicial;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
            nuevo.setAnterior(aux);
            ultimo = nuevo;

        } else {
            numerado++;
            listaNodo nuevo = new listaNodo(numerado,nombre, tipo, ruta);
            inicial = nuevo;
            ultimo = nuevo;
        }
    }
    
    
    public listaNodo sacar_cola(){
        
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
    
    public listaNodo sacar_pila(){
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
    
    public void eliminar(int i){
        
        listaNodo aux;
        aux = inicial;
        
       
        
        while(aux != null)
        {
            if(aux.getCorrelativo() == i ){
                 
                if(aux.getAnterior() != null && aux.getSiguiente() != null){
                    
                    aux.getAnterior().setSiguiente( aux.getSiguiente());
                    aux.getSiguiente().setAnterior( aux.getAnterior() );
                    
                    
                }else if(aux.getAnterior() != null){
                    
                    if(aux == ultimo)  ultimo = aux;
                    
                    aux.getAnterior().setSiguiente( null);
                                        
                }else if(aux.getSiguiente() != null){
                    
                    if(aux == inicial)  inicial = aux.getSiguiente();
                    
                    aux.getSiguiente().setAnterior( null );
                }else{
                    
                    if(inicial == ultimo)  inicial = ultimo = null;
                    
                }
                
            }    
            
            aux = aux.getSiguiente();
        }
        
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
    
    public void graficar(){
        
        listaNodo aux;
        aux = inicial;
        String rel = "";
        String der = "";
        String izq = "";
        
        while (aux != null) {
            
                rel += "nod" + aux.getCorrelativo() + " [shape=record ,color=\"cyan\", label= \" { nombre : " + aux.getNombre() +
                         " |  posicion: " + "" +aux.getCorrelativo() + " } | { tipo: " + aux.getTipo()+ " }  \"] ; \n" ;             
               
                if(aux.getSiguiente() != null){
                    der += "nod" +aux.getCorrelativo() + " -> nod" + aux.getSiguiente().getCorrelativo() + " [color = indigo] ;\n" ;
                }
                
                if(aux.getAnterior() != null){
                    izq += "nod" +aux.getCorrelativo() +  " -> nod" + aux.getAnterior().getCorrelativo() + " [color = red] ;\n" ;
                }

            System.out.println(aux.getNombre() + "  ");
            aux = aux.getSiguiente();
        }
        
         try{
            //Abro stream, crea el fichero si no existe
            FileWriter fw = new FileWriter("lista.dot");
            fw.write("digraph g { \n");
          
            fw.write(rel + "\n");   
            fw.write(der + "\n");
            fw.write(izq + "\n");

            fw.write("} \n");
            //Cierro el stream
            fw.close(); 
                //Abro el stream, el fichero debe existir
            FileReader fr=new FileReader("lista.dot");
            //Leemos el fichero y lo mostramos por pantalla
            int valor=fr.read();
            while(valor!=-1){
                System.out.print((char)valor);
                valor=fr.read();
            }
            //Cerramos el stream
            fr.close();
            
            //llamamos graphviz
            graphviz();
            
        }catch(IOException e){
            System.out.println("Error E/S: "+e);
        }
    }
    
    private void graphviz(){
     
        try {

            String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            String fileInputPath = "lista.dot";
            String fileOutputPath = "D:\\lista.jpg";
            //Users\\Raulk\\Documents\\NetBeansProjects\\practica1\\src\\imagenes\\lista.jpg
            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;
            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }
}