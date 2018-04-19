/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1.practica2_201700507.pkg1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author kimel
 */
public class Estadisticas_201700507 {
    
    Login_201700507 nombre = new Login_201700507();
    
    public Estadisticas_201700507(){
    
        Font fuente = new Font("Copperplate Gothic Light",1,17);
        Font fuenteTextField = new Font("Monospaced",1,15);
       
        JFrame login = new JFrame("Estadísticas");
        login.setBounds(650,300,700,350);
        login.setLayout(null);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel info = new JLabel();
        info.setBounds(130,50,550,150);
        info.setText("<html>"
                + "<center>¡Juego terminado " + Login_201700507.nombreNave + "!</center>"
                + "<br>Naves enemigas impactadas (Disparo): " + IPC1Practica2_201700507.contadorEnemigoDisparo
                + "<br>Naves enemigas impactadas (Bonus): " + IPC1Practica2_201700507.contadorEnemigoBonus
                + "<br>Naves enemigas impactadas (Colisión): " + IPC1Practica2_201700507.contadorEnemigoColision
                + "<br>Disparos: " + IPC1Practica2_201700507.contadorDisparos
                + "</html>");
        login.add(info);
        info.setFont(fuente);
        
        
        login.setVisible(true);
    
    }
    
    
}
