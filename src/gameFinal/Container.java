
package gameFinal;

import gameFinal.modelo.Fase;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;  
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

public class Container extends JFrame{    
    
    public Container() {                
        JMenuBar barraMenu = new JMenuBar();        
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic('M');       
        JMenuItem sobre = new JMenuItem("Sobre");
        sobre.setMnemonic('O'); 
        sobre.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent e) {              
             JOptionPane.showMessageDialog(null, "Jogo desenvolvido para APS", "Informações", JOptionPane.INFORMATION_MESSAGE);                     
            } });          
        JMenuItem sair = new JMenuItem("Sair");
        sair.setMnemonic('S'); 
        sair.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                System.exit(0);            
            } });        
        menu.add(sobre);
        menu.add(new JSeparator());
        menu.add(sair);        
        barraMenu.add(menu);        
        setJMenuBar(barraMenu); 
        
        add(new Fase());                   
        setTitle("Jogo");                 
        setSize(1024, 728);              
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);    
        setResizable(false);      
        setVisible(true);             
        
        musicaTema(); 
    } 
    public void musicaTema() {
        try {
            
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res\\themeSong.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    } 
    
    public static void main (String []args) throws LineUnavailableException, UnsupportedAudioFileException, IOException 
    {
        new Container();
        
    }
}
