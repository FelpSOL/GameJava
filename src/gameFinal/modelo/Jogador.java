package gameFinal.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Jogador implements ActionListener{   
   
    private int x,y;     
    private int dx,dy;   
    private Image imagem;
    private int altura, largura; 
    private List <Tiro> tiros; 
    private boolean isVisivel, isTurbo;
    private Timer timer;   
   
    public Jogador(){        
        this.x = 100;
        this.y = 250;
        isVisivel = true;
        isTurbo = false;
        tiros = new ArrayList <Tiro>(); 
        timer = new Timer(5000, this); 
        timer.start();}    
    @Override
    public void actionPerformed(ActionEvent e){
        if(isTurbo == true){ 
            turbo();
            isTurbo = false;}        
        if (isTurbo == false){
            load();}}     
    
    public void load(){
        ImageIcon referencia = new ImageIcon("res\\submarino.png");
        imagem = referencia.getImage(); 
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);}
     
    public void update(){  
        x += dx;
        y += dy;       
        if(this.x < 1){
            x = 1;}
        if(this.x > 892){
            x = 892;}        
        if(this.y < 1){
            y = 1;}
        if(this.y > 640){
            y = 640;}}
    
    public void tiroSimples(){
        this.tiros.add(new Tiro(x + largura, y + (altura/2)));}    
    public void turbo(){
        isTurbo = true;
        ImageIcon referencia = new ImageIcon("res\\modoturbo.png");
        imagem = referencia.getImage();}    
    public Rectangle getBounds(){ 
        return new Rectangle(x,y,largura,altura);}
    
    
    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();     
        if (codigo == KeyEvent.VK_Z){ 
            turbo();}       
        if (codigo == KeyEvent.VK_X){ 
            if(isTurbo == false){
                 tiroSimples();}}           
        if (codigo == KeyEvent.VK_UP){ 
            dy = -3;}        
        if (codigo == KeyEvent.VK_DOWN){ 
            dy = 3;}                           
        if (codigo == KeyEvent.VK_LEFT){        
            dx = -3;}            
        if (codigo == KeyEvent.VK_RIGHT){     
            dx = +3;}}                                   
       
    public void keyRelease(KeyEvent tecla){
        int codigo = tecla.getKeyCode();      
        if (codigo == KeyEvent.VK_UP) 
        {dy = 0;}        
        if (codigo == KeyEvent.VK_DOWN) 
        {dy = 0;}
        if (codigo == KeyEvent.VK_LEFT) 
        {dx = 0;}
        if (codigo == KeyEvent.VK_RIGHT) 
        {dx = 0;}}
    
    public boolean isVisivel(){
        return isVisivel;}
    public void setVisivel(boolean isVisivel){
        this.isVisivel = isVisivel;}     
    public boolean isTurbo(){
        return isTurbo;}
    public void setTurbo(boolean isTurbo){
        this.isTurbo = isTurbo;}     
    public int getX(){
        return x;}
    public int getY(){
        return y;}          
    public Image getImagem(){
        return imagem;}
    public List <Tiro> getTiros(){
        return tiros;}}