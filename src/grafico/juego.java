/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafico;
import estructuras.matriz;
import estructuras.lista;
import estructuras.nodoM;
import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
/**
 *
 * @author Raulk
 */
public class juego extends javax.swing.JFrame implements KeyListener {

    /**
     * Creates new form juego
     */
    
    private class HiloTiempo extends Thread{
        int num = 200; 
        boolean para = true;
        @Override
        public void run() {
            
            while(para){
               jLabel2.setText(""+num );
               
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(juego.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(num == 0) break;
                
                num--;
            }   
        }
    }
    
    private class gravedad extends Thread{
        boolean para = true;
        @Override
        public void run(){
            while(para){
                if (mario.getAbajo() == null) {
                    perdio ven = new perdio();
                    ven.fondo();
                    para = false;
                    sonido.stop();
                    ven.show();                   
                }else if( mario.getAbajo().getValor() == 1){//si hay datos enel nodo
                    
                    if(mario.getAbajo().getTipo() == 0 || mario.getAbajo().getTipo()==1){
                        //no se mueve ni nada
                    }else if (mario.getAbajo().getTipo() == 2){
                        mario.getAbajo().setNombre(mario.getNombre());
                        mario.getAbajo().setTipo(mario.getTipo());
                        mario.getAbajo().setRuta(mario.getRuta());
                        mario.getAbajo().setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getAbajo();
                        puntos+=100;
                        jLabel9.setText("" + puntos);
                        dibujar_matriz();
                    }
                }else{//si no hay datos en el nodo
                    mario.getAbajo().setNombre(mario.getNombre());
                    mario.getAbajo().setTipo(mario.getTipo());
                    mario.getAbajo().setRuta(mario.getRuta());
                    mario.getAbajo().setValor(1);
                    mario.setValor(0);
                    mario.setRuta("/imagenes/fondo.png");
                    mario.setTipo(0);
                    mario.setName("");
                    mario = mario.getAbajo();
                    dibujar_matriz();
                }
               
                
                try {
                    Thread.sleep(700);
                } catch (InterruptedException ex) {
                    Logger.getLogger(juego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    
    private class moverEnemigo extends Thread{
        
        boolean para = true;
        
        @Override
        public void run(){
            //para mover a los enemigos
            
            while(para == true){
                
                nodoM aux1, aux2;
                aux1 = mat.inicial;
                aux2 = mat.inicial;

                aux1 = aux2;

                while (aux2 != null) {
                    while (aux1 != null) {
                        int direccion = ((int) Math.random() * 100);// mayor a 50 derecha menor izq
                        switch(aux1.getTipo()){
                            
                            case 3:
                                if(direccion < 50){
                                    if(aux1.getDerecha().getTipo() == 0 && aux1.getDerecha().getValor() == 0){
                                        aux1.getDerecha().setNombre(aux1.getNombre());
                                        aux1.getDerecha().setTipo(aux1.getTipo());
                                        aux1.getDerecha().setRuta(aux1.getRuta());
                                        aux1.getDerecha().setValor(1);
                                        aux1.setValor(0);
                                        aux1.setRuta("/imagenes/fondo.png");
                                        aux1.setTipo(0);
                                        aux1.setName("");
                                    }else if(aux1.getDerecha().getTipo() == 6){
                                        vidas--;
                                        aux1.setValor(0);
                                        aux1.setRuta("/imagenes/fondo.png");
                                        aux1.setTipo(0);
                                        aux1.setName("");
                                    }else{
                                        aux1.getIzquierda().setNombre(aux1.getNombre());
                                        aux1.getIzquierda().setTipo(aux1.getTipo());
                                        aux1.getIzquierda().setRuta(aux1.getRuta());
                                        aux1.getIzquierda().setValor(1);
                                        aux1.setValor(0);
                                        aux1.setRuta("/imagenes/fondo.png");
                                        aux1.setTipo(0);
                                        aux1.setName("");
                                    }
                                    
                                }else{
                                    
                                    if(aux1.getIzquierda().getTipo() == 7){
                                        aux1.getDerecha().setNombre(aux1.getNombre());
                                        aux1.getDerecha().setTipo(aux1.getTipo());
                                        aux1.getDerecha().setRuta(aux1.getRuta());
                                        aux1.getDerecha().setValor(1);
                                        aux1.setValor(0);
                                        aux1.setRuta("/imagenes/fondo.png");
                                        aux1.setTipo(0);
                                        aux1.setName("");
                                    }else if(aux1.getIzquierda().getTipo() == 6){
                                        vidas--;
                                        aux1.setValor(0);
                                        aux1.setRuta("/imagenes/fondo.png");
                                        aux1.setTipo(0);
                                        aux1.setName("");
                                    }else{
                                        aux1.getDerecha().setNombre(aux1.getNombre());
                                        aux1.getDerecha().setTipo(aux1.getTipo());
                                        aux1.getDerecha().setRuta(aux1.getRuta());
                                        aux1.getDerecha().setValor(1);
                                        aux1.setValor(0);
                                        aux1.setRuta("/imagenes/fondo.png");
                                        aux1.setTipo(0);
                                        aux1.setName("");
                                    }
                                }
                                
                                break;
                                
                            case 4:
                                if(direccion < 50){
                                    
                                }else{
                                    
                                }
                                
                                break;
                            default:
                                break;
                                
                        }
     
                        aux1 = aux1.getDerecha();
                    }
           
                    aux2 = aux2.getArriba();
                    aux1 = aux2;
                }
                
                try {
                    Thread.sleep(600);
                } catch (InterruptedException ex) {
                    Logger.getLogger(juego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    matriz mat;
    matriz jugar;
    lista list;
    nodoM mario; 
    nodoM castillo;
    int vidas;
    int puntos;
    Clip sonido;
    gravedad grav;
    HiloTiempo hil;
    moverEnemigo movE;
    
    public juego() {
        initComponents();
        vidas = 5;
        puntos = 0;
    }

    public void recibeDatos(matriz recibida){
        jugar = recibida;
        vidas = 5;
        jLabel11.setText("" + vidas);
        puntos = 0;
        jLabel9.setText("" + puntos);
        clonar();
        dibujar_matriz();
        hil = new HiloTiempo();
        movE = new moverEnemigo();
        grav = new gravedad();
    }
    
    public void clonar(){
        
        mat = new matriz();
          
        nodoM aux = jugar.inicial; 
        nodoM aux1 = jugar.inicial;
        mat.eliminar_matriz();
        mat = new matriz();
        mat.crear(jugar.filas,jugar.columnas);
        
        nodoM aux3 = mat.inicial;
        nodoM aux4 = mat.inicial;
        
        while(aux1 != null){
            while(aux != null){
                if(aux.getValor() == 1){
                    aux3.setValor(aux.getValor());
                    aux3.setNombre(aux.getNombre());
                    aux3.setRuta(aux.getRuta());
                    aux3.setTipo(aux.getTipo());
                }
                
                aux = aux.getDerecha();
                aux3 = aux3.getDerecha();
            }
            
            aux3 = aux4.getArriba();
            aux4 = aux4.getArriba();
            
            aux = aux1.getArriba();
            aux1 = aux1.getArriba();
        }
        
        
        mario = mat.personaje;
       
    }
    
    public void dibujar_matriz(){
        jPanel2.removeAll();
        nodoM aux1,aux2;
        aux1 = mat.inicial;
        aux2 = mat.inicial;
        int x, y;
        x=30; y =30;
    
        
        while(aux2.getArriba() != null)  aux2 = aux2.getArriba();
        
      
        while(aux1 != null){  
                aux1.setBounds(x, y, 30, 30);
                String path = aux1.getRuta();
                URL url = this.getClass().getResource(path);
                ImageIcon icon = new ImageIcon(url);
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(aux1.getWidth(), aux1.getHeight(), Image.SCALE_DEFAULT));
                aux1.setIcon(icono);
                jPanel2.add(aux1);
                aux1 = aux1.getDerecha();
            }
        
        aux1 = aux2;
            
        while(aux2 != null){
            while(aux1 != null){   

                aux1.setBounds(x, y, 30, 30);
                String path = aux1.getRuta(); 
                URL url = this.getClass().getResource(path);
                ImageIcon icon = new ImageIcon(url);
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(aux1.getWidth(), aux1.getHeight(), Image.SCALE_DEFAULT));
                aux1.setIcon(icono);
                jPanel2.add(aux1);
                x += 30;
                                
                if(aux1.getTipo() == 6)  mario = aux1;
                
                if(aux1.getTipo() == 7)  castillo = aux1;
                
                aux1 = aux1.getDerecha();
            }
            
            x = 30;
            y += 30;
            aux2 = aux2.getAbajo();
            aux1 = aux2;
        }
        jPanel2.repaint();
    }
    
    
    @Override
    public void keyPressed(KeyEvent e)
    {
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton12 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reinicio.png"))); // NOI18N
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pausa.png"))); // NOI18N
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/play.png"))); // NOI18N
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });
        jButton10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton10KeyPressed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 0, 0));
        jButton11.setText("Graficar");

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
        );

        jLabel10.setText("Vidas: ");

        jLabel11.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("0");

        jLabel9.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("0");

        jLabel8.setText("Puntos:");

        jLabel1.setText("Tiempo: ");

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 0));
        jLabel2.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jButton11)
                .addGap(68, 68, 68)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(161, 161, 161))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton11))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        // hace andar el hilo
        hil.start();
        movE.start();
        grav.start();
        try {
            sonido();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(juego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(juego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(juego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10MouseClicked

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        
    }//GEN-LAST:event_jPanel2KeyPressed

    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed
         mover(evt);
    }//GEN-LAST:event_jButton10KeyPressed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        sonido.stop();
        grav.para = false;
        hil.para = false;
        movE.para = false;
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        // para volver y echarse otro juego cuando se pierde        
        reinicio();
    }//GEN-LAST:event_jButton12MouseClicked

    private void reinicio(){
        jPanel2.removeAll();
        recibeDatos(jugar);
    }
    
    private void mover(java.awt.event.KeyEvent evento){
        
        switch(evento.getKeyCode()){
            case KeyEvent.VK_UP:
                colisiones(mario.getArriba(),3);
                break;
            case KeyEvent.VK_DOWN:
                colisiones(mario.getAbajo(),4);
                break;
            case KeyEvent.VK_LEFT:
                colisiones(mario.getIzquierda(),1);
                break;
            case KeyEvent.VK_RIGHT:
                colisiones(mario.getDerecha(),2);
                break;
            default:
                
            
        }
    }
    
    public void colisiones(nodoM mov,int dir){//izq 1, der 2, arr 3, aba 4
        
        if(mov == null)  return;
        
        switch(mov.getTipo()){
            case 0://suelo o sin nada
                if(mov.getValor() == 0){//si es un nodo vacio solo se mueve
                    if(dir == 1){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getIzquierda();
                        dibujar_matriz();
                    }else if(dir == 2){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getDerecha();
                        dibujar_matriz();
                    }else if(dir == 3){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getArriba();
                        dibujar_matriz();
                    }else if(dir == 4){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getAbajo();
                        dibujar_matriz();
                    }
                }
        
                //si el nodo no esta vacio no puede moverse para ningun lado
                break;
            case 1://pared
                
                break;
            case 2://goomba
                if(dir == 1 || dir == 2 || dir == 3){//pierde
                    vidas--;
                    jLabel11.setText(""+ vidas);
                    
                    if(dir == 1){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getIzquierda();
                        dibujar_matriz();
                    }else if(dir == 2){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getDerecha();
                        dibujar_matriz();
                    }else if(dir == 3){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getArriba();
                        dibujar_matriz();
                    }else if(dir == 4){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getAbajo();
                        dibujar_matriz();
                    }
                }
                
                break;
            case 3://koopa pierde si se toca
                vidas--;
                jLabel11.setText(""+ vidas);
                
                if(dir == 1){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getIzquierda();
                        dibujar_matriz();
                    }else if(dir == 2){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getDerecha();
                        dibujar_matriz();
                    }else if(dir == 3){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getArriba();
                        dibujar_matriz();
                    }else if(dir == 4){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getAbajo();
                        dibujar_matriz();
                    }
                
                break;
            case 4://ficha
                puntos+=100;
                jLabel9.setText("" + puntos);
                if(dir == 1){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getIzquierda();
                        dibujar_matriz();
                    }else if(dir == 2){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getDerecha();
                        dibujar_matriz();
                    }else if(dir == 3){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getArriba();
                        dibujar_matriz();
                    }else if(dir == 4){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getAbajo();
                        dibujar_matriz();
                    }
                break;
            case 5://hongo
                vidas++;
                jLabel11.setText("" + vidas);
                
                if(dir == 1){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getIzquierda();
                        dibujar_matriz();
                    }else if(dir == 2){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getDerecha();
                        dibujar_matriz();
                    }else if(dir == 3){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getArriba();
                        dibujar_matriz();
                    }else if(dir == 4){
                        mov.setNombre(mario.getNombre());
                        mov.setTipo(mario.getTipo());
                        mov.setRuta(mario.getRuta());
                        mov.setValor(1);
                        mario.setValor(0);
                        mario.setRuta("/imagenes/fondo.png");
                        mario.setTipo(0);
                        mario.setName("");
                        mario = mario.getAbajo();
                        dibujar_matriz();
                    }
                
                break;
                
            case 7://castillo
                    victoria vic = new victoria();
                    sonido.stop();
                    grav.para = false;
                    int num = Integer.parseInt(jLabel2.getText());
                    vic.punteo(puntos , vidas, num);
                    vic.show();                
                break;
            case 100://si no hay nada en el cuadro
                
                break;
        }
    }
    
    void sonido() throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        BufferedInputStream bis = new BufferedInputStream(getClass().getResourceAsStream("/musica/tema.MID"));
        AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
        sonido = AudioSystem.getClip();
        sonido.open(ais);
        //sonido.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/musica/tema.MID")));
        sonido.start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new juego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
