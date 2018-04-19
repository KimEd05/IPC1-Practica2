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
public class Login_201700507 {
    
    
    static String nombreNave;
    JTextField usuariotxt;


    public void Registro(){
    
        Font fuente = new Font("Copperplate Gothic Light",1,17);
        Font fuenteTextField = new Font("Monospaced",1,15);
       
        JFrame login = new JFrame("Login");
        login.setBounds(650,300,700,350);
        login.setLayout(null);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel info = new JLabel();
        info.setBounds(195,0,350,125);
        info.setText("<html><center>"
                + "Ingrese el nombre de su nave:"
                + "</center><html>");
        login.add(info);
        info.setFont(fuente);
        
            usuariotxt = new JTextField();
            usuariotxt.setBounds(175,115,350,25);
            usuariotxt.setFont(fuenteTextField);
            login.add(usuariotxt);
            
            
        
        JButton aceptarBoton = new JButton("Aceptar");
        aceptarBoton.setBounds(260,185,150,75);
        aceptarBoton.setFont(fuente);
        aceptarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                nombreNave = usuariotxt.getText();
                login.dispose();
                IPC1Practica2_201700507 galaga = new IPC1Practica2_201700507();
                
            }
        });
        login.add(aceptarBoton);
        
        login.setVisible(true);
    
    }
    
 
}
