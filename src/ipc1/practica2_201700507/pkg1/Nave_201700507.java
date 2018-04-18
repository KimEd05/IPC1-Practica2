/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1.practica2_201700507.pkg1;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author kimel
 */
public class Nave_201700507 {
    
    private Image nave;
    private int xN;
    private int yN;
    private int vida;
    private int bonus;
    private int puntos;

    
    
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public Image getNave() {
        return nave;
    }

    public void setNave(Image nave) {
        this.nave = nave;
    }

    public int getxN() {
        return xN;
    }

    public void setxN(int xN) {
        this.xN = xN;
    }

    public int getyN() {
        return yN;
    }

    public void setyN(int yN) {
        this.yN = yN;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
   
    
    
}
