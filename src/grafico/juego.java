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
import java.util.logging.Level;
import java.util.logging.Logger;
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
               jLabel9.setText(""+num );
               
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(juego.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(num == 0) break;
                
                num--;
            }   
        }
    }
    
    
    
    private class moverEnemigo extends Thread{
        
        boolean moverse = true;
        
        @Override
        public void run(){
            //para mover a los enemigos
            
            while(moverse == true){
                
                
                try {
                    Thread.sleep(600);
                } catch (InterruptedException ex) {
                    Logger.getLogger(juego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    matriz mat;
    lista list;
    nodoM mario; 
    nodoM castillo;
    int vidas;
    int puntos;
    HiloTiempo hil;
    moverEnemigo movE;
    
    public juego() {
        initComponents();
        vidas = 0;
        puntos = 0;
        mat = new matriz();
        hil = new HiloTiempo();
        movE = new moverEnemigo();
    }

    public void recibeDatos(matriz recibida){
        mat = recibida;
        dibujar_matriz();
        
    }
    
    public void dibujar_matriz(){
        jPanel2.removeAll();
        nodoM aux1,aux2;
        aux1 = mat.inicial;
        aux2 = mat.inicial;
        int x, y;
        x=30; y =30;
    
        Border border = LineBorder.createGrayLineBorder();
        
        while(aux2.getArriba() != null)  aux2 = aux2.getArriba();
        
      
        while(aux1 != null){  
                aux1.setBounds(x, y, 30, 30);
                aux1.setBorder(border);
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
                aux1.setBorder(border);
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
    
    public void colisiones(nodoM mov,int dir){//izq 1, der 2, arr 3, aba 4
        switch(mov.getTipo()){
            case 0://suelo o sin nada
                if(mov.getValor() == 0){//si es un nodo vacio solo se mueve
                    if(dir == 1){
                        
                    }else if(dir == 2){
                        
                    }else if(dir == 3){
                        
                    }else if(dir == 4){
                        
                    }
                }
                
                //si el nodo no esta vacio no puede moverse para ningun lado
                break;
            case 1://pared
                
                break;
            case 2://goomba
                if(dir == 1 || dir == 2 || dir == 3){//pierde
                    
                }else if(dir == 4){
                    
                }
                break;
            case 3://koopa pierde si se toca
                
                break;
            case 4://ficha
                puntos+=100;
                
                break;
            case 5://hongo
                vidas++;
                
                break;
                
            case 7://castillo
                
                break;
            case 100://si no hay nada en el cuadro
                
                break;
        }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reinicio.png"))); // NOI18N

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pausa.png"))); // NOI18N

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
            .addGap(0, 461, Short.MAX_VALUE)
        );

        jLabel10.setText("Vidas: ");

        jLabel11.setText("0");

        jLabel9.setText("0");

        jLabel8.setText("Puntos:");

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
                    .addComponent(jLabel10))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
    }//GEN-LAST:event_jButton10MouseClicked

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        
    }//GEN-LAST:event_jPanel2KeyPressed

    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed
         mover(evt);
    }//GEN-LAST:event_jButton10KeyPressed

    
    private void mover(java.awt.event.KeyEvent evento){
        
        
        switch(evento.getKeyCode()){
            case KeyEvent.VK_UP:
                JOptionPane.showMessageDialog(null, "arriba",
                "direccion", JOptionPane.INFORMATION_MESSAGE);
                break;
            case KeyEvent.VK_DOWN:
                JOptionPane.showMessageDialog(null, "abajo",
                "direccion", JOptionPane.INFORMATION_MESSAGE);
                break;
            case KeyEvent.VK_LEFT:
                JOptionPane.showMessageDialog(null,"izquierda",
                "direccion", JOptionPane.INFORMATION_MESSAGE);
                break;
            case KeyEvent.VK_RIGHT:
                JOptionPane.showMessageDialog(null, "derecha",
                "direccion", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                
            
        }
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
