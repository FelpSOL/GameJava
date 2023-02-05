package gameFinal.modelo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener  
{   
    private Image fundo;                
    private Jogador jogador;
    private Timer timer;               
    private List<Inimigo1> inimigo1;  
    private List<Inimigo2> inimigo2;  
    private List<Bolha> bolha;       
    private boolean emJogo;   
    private boolean inimigosFinalizados;
          
    Font fonte = new Font("Ariel Black", Font.BOLD, 18 );

    public Fase()
    {
        setFocusable(true); 
        setDoubleBuffered(true);
        
        ImageIcon referencia = new ImageIcon("res\\oceano.png"); 
        fundo = referencia.getImage();         
                                      
        timer = new Timer(5, this);    
        timer.start();
        
        jogador = new Jogador();
        jogador.load(); 
        
        addKeyListener(new TecladoAdapter ()); 
        
        inicializaInimigos();
        inicializaBolha();
        emJogo = true;
        inimigosFinalizados = false;
    }    
    public void inicializaInimigos(){
        
        int coordenadas[ ] = new int [25];  
        inimigo1 = new ArrayList<Inimigo1>();
        
        
        for (int i = 0; i < coordenadas.length; i++) {
            int x = (int)(Math.random() * 4000 + 1024);  
            int y = (int)(Math.random() * 550 + 50);
            inimigo1.add(new Inimigo1(x, y));
        }
        
        int coordenadas2[ ] = new int [25];   
        inimigo2 = new ArrayList<Inimigo2>(); 
        
        
        for (int k = 0; k < coordenadas2.length; k++) {
            int x = (int)(Math.random() * 4000 + 1024);  
            int y = (int)(Math.random() * 550 + 50);
            inimigo2.add(new Inimigo2(x, y));
        }
    }    
    public void inicializaBolha(){
        int coordenadas[ ] = new int [500];
        bolha = new ArrayList<Bolha>();
        
        for (int i = 0; i < coordenadas.length; i++) {
            int x = (int)(Math.random() * 1024 + 0);
            int y = (int)(Math.random() * 768 + 0);
            bolha.add(new Bolha(x, y));
        }    
    }    
    public void paint(Graphics g) 
    {
        Graphics2D graficos = (Graphics2D) g; 
        if(emJogo == true){
            graficos.drawImage(fundo, 0, 0, null); 
            
            for(int l = 0; l < bolha.size(); l++){
            Bolha b = bolha.get(l);
                b.load();
                graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);        
            }
                
            graficos.drawImage(jogador.getImagem(), jogador.getX(), jogador.getY(), this);
        
            List<Tiro> tiros = jogador.getTiros(); 
            for(int i = 0; i < tiros.size(); i++){            
                Tiro t= tiros.get(i);
                t.load();
                graficos.drawImage(t.getImagem(), t.getX(), t.getY(), this);        
            }
        
            for (int c = 0; c < inimigo1.size(); c++){
                Inimigo1 in = inimigo1.get(c); 
                in.load();
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);        
            } 
            
            for (int d = 0; d < inimigo2.size(); d++){
                Inimigo2 n = inimigo2.get(d);
                n.load();
                graficos.drawImage(n.getImagem(), n.getX(), n.getY(), this);        
            } 
                    
            g.setFont(fonte);
            g.setColor(Color.black);
            graficos.drawString("INIMIGOS: " + (inimigo2.size() + inimigo1.size()), 5, 20);             
        }
        else{
            ImageIcon fimJogo = new ImageIcon("res\\gameover.png");
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }  
        
       if(inimigosFinalizados){
           
            ImageIcon parabens = new ImageIcon("res\\parabens.png");
            graficos.drawImage(parabens.getImage(), 0, 0, null);        
        }             
                               
        g.dispose();  
    }    
    @Override
    public void actionPerformed(ActionEvent e){
        repaint();         
        jogador.update(); 
        
        if(jogador.isTurbo()){
            timer.setDelay(2);
        }        
        if(jogador.isTurbo() == false){
            timer.setDelay(16);
        }        
        for(int f = 0; f < bolha.size(); f++){
            Bolha b = bolha.get(f);
            if(b.isVisivel()){
                    b.update();
                }else{
                    bolha.remove(f);
                }            
        }        
        List<Tiro> tiros = jogador.getTiros(); 
        for(int i = 0; i < tiros.size(); i++){
            Tiro t = tiros.get(i);
                if(t.isVisivel()){ 
                    t.update();
                    if(jogador.isTurbo()){
                        tiros.get(i).setVELOCIDADE(-1);
                    }
                    if(jogador.isTurbo() == false){
                        tiros.get(i).setVELOCIDADE(2);
                    }
                }else{
                    tiros.remove(i); 
                }
        }
        int fim = inimigo1.size();
        for (int c = 0; c < inimigo1.size(); c++){ 
           Inimigo1 in = inimigo1.get(c);         
                if(in.isVisivel()){               
                    in.update();
               }else{
                 inimigo1.remove(c);
                 fim --;
               }                                  
        } 
        int fim2 = inimigo2.size();
        for (int d = 0; d < inimigo2.size(); d++){  
           Inimigo2 n = inimigo2.get(d);          
                if(n.isVisivel()){               
                    n.update();
               }else{
                 inimigo2.remove(d);
                 fim2 --;
               }                                  
        } 
        if(fim == 0 && fim2 ==0){
            inimigosFinalizados = true;
        
        }
        checarColisoes();        
                                         
    }    
    public void checarColisoes(){  
        Rectangle formaSubmarino = jogador.getBounds();
        Rectangle formaInimigo1;
        Rectangle formaInimigo2;
        Rectangle formaTiro;
        
        for (int i = 0; i < inimigo1.size(); i++){  
           Inimigo1 tempInimigo1 = inimigo1.get(i); 
            formaInimigo1 = tempInimigo1.getBounds();
                if(formaSubmarino.intersects(formaInimigo1)){ 
                    if(jogador.isTurbo()){    
                    tempInimigo1.setVisivel(false);                    
                    }else{
                        jogador.setVisivel(false);  
                        tempInimigo1.setVisivel(false);
                        emJogo = false;
                    }    
                }
        }        
        for (int d = 0; d < inimigo2.size(); d++){ 
           Inimigo2 tempInimigo2 = inimigo2.get(d); 
            formaInimigo2 = tempInimigo2.getBounds();
                if(formaSubmarino.intersects(formaInimigo2)){ 
                    if(jogador.isTurbo()){  
                    tempInimigo2.setVisivel(false);                    
                    }else{
                        jogador.setVisivel(false);  
                        tempInimigo2.setVisivel(false);
                        emJogo = false;
                    }    
                }
        }        
        List<Tiro> tiros = jogador.getTiros();
        for (int m = 0; m < tiros.size(); m++){
            Tiro tempTiro = tiros.get(m);
            formaTiro = tempTiro.getBounds();
            
            for (int p = 0; p < inimigo1.size(); p++){
                Inimigo1 tempInimigo1 = inimigo1.get(p); 
                formaInimigo1 = tempInimigo1.getBounds();
                if(formaTiro.intersects(formaInimigo1)){
                    tempInimigo1.setVisivel(false);
                    tempTiro.setVisivel(false);
                }
               for (int q = 0; q < inimigo2.size(); q++){
                Inimigo2 tempInimigo2 = inimigo2.get(q); 
                formaInimigo2 = tempInimigo2.getBounds();
                if(formaTiro.intersects(formaInimigo2)){
                    tempInimigo2.setVisivel(false);
                    tempTiro.setVisivel(false);
                }    
               }                 
            }                
        }
    }    
    private class TecladoAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e) 
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                emJogo = true;
                jogador = new Jogador ();
                inicializaInimigos();
            }
            jogador.keyPressed(e);                       
        }         
        @Override
        public void keyReleased(KeyEvent e) 
        {
            jogador.keyRelease(e);                       
        }        
    }     
}
