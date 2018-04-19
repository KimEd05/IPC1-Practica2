/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc1.practica2_201700507.pkg1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kimel
 */
public class IPC1Practica2_201700507 extends JFrame {

//    int x, y;
    Image fondo;
    Image enemigoImagen;
    Image disparoImagen;
    Image bonusImagen;
    private Image dbImage;
    private Graphics dbg;
    Nave_201700507 navePrincipal;
    Enemigo_201700507[] listadoEnemigos = new Enemigo_201700507[1000];
    Disparo_201700507[] listadoDisparos = new Disparo_201700507[1000];
    Bonus_201700507[] listadoBonus = new Bonus_201700507[3];
    Bonus_201700507 bonusCantidad = new Bonus_201700507();

    static int tiempo = 60;
    static int contadorEnemigoDisparo = 0;
    static int contadorEnemigoBonus = 0;
    static int contadorEnemigoColision = 0;
    static int contadorDisparos = 0;

    TimerTask timerCreacionEnemigos = new TimerTask() {
        public void run() {
//               System.out.println("Creación de Enemigo"); 
            generarEnemigos();
            repaint();
        }
    };
    // Aquí se pone en marcha el timer cada segundo. 
    Timer timerCrearEnemigos = new Timer();

    TimerTask timerMovimientoEnemigos = new TimerTask() {
        public void run() {
//               System.out.println("Moviendo Enemigos"); 
            moverEnemigo();
            repaint();
        }
    };
    // Aquí se pone en marcha el timer cada segundo. 
    Timer timerMoverEnemigos = new Timer();

    TimerTask timerMovimientoDisparo = new TimerTask() {
        public void run() {
//               System.out.println("Moviendo Disparo"); 
            moverDisparo();
            repaint();
        }
    };
    // Aquí se pone en marcha el timer cada segundo. 
    Timer timerMoverDisparos = new Timer();

    TimerTask timerCreacionBonus = new TimerTask() {
        public void run() {
//               System.out.println("Creación de Bonus"); 
            generarBonus();
            repaint();

        }
    };
    // Aquí se pone en marcha el timer cada segundo. 
    Timer timerCrearBonus = new Timer();

    TimerTask timerMovimientoBonus = new TimerTask() {
        public void run() {
//               System.out.println("Moviendo Bonus"); 
            moverBonus();
            repaint();
        }
    };
    // Aquí se pone en marcha el timer cada segundo. 
    Timer timerMoverBonus = new Timer();

    TimerTask timer = new TimerTask() {
        public void run() {
//               System.out.println("Tiempo Juego"); 
            tiempo--;
            if (tiempo == 0) {
                Estadisticas_201700507 estadisticas = new Estadisticas_201700507();
                dispose();
            }
            repaint();
        }
    };
    // Aquí se pone en marcha el timer cada segundo. 
    Timer timerJuego = new Timer();

    public class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == e.VK_UP) {
                navePrincipal.setyN(navePrincipal.getyN() - 50);
                if (navePrincipal.getyN() < 35) {
                    navePrincipal.setyN(35);
                }
//                System.out.println("Posicion y " + navePrincipal.getyN());
                repaint();
            }

            if (keyCode == e.VK_DOWN) {
                navePrincipal.setyN(navePrincipal.getyN() + 50);
                if (navePrincipal.getyN() > 575) {
                    navePrincipal.setyN(575);
                }
//                System.out.println("Posicion y " + navePrincipal.getyN());
                repaint();
            }

            if (keyCode == e.VK_SPACE) {
                generarDisparo();
//                System.out.println("Disparo");
                repaint();
                contadorDisparos++;
            }
        }

        public void keyReleased(KeyEvent e) {

        }
    }

    public int obtenerPosicionDisparo() {
        for (int i = 0; i < listadoDisparos.length; i++) {
            if (listadoDisparos[i] == null) {
                return i;
            }
        }
        return -100;
    }

    public int obtenerPosicionBonus() {
        for (int i = 0; i < listadoBonus.length; i++) {
            if (listadoBonus[i] == null) {
                return i;
            }
        }
        return -100;
    }

    public int obtenerPosicionDisponible() {
        for (int i = 0; i < listadoEnemigos.length; i++) {
            if (listadoEnemigos[i] == null) {
                return i;
            }

        }

        return -100;
    }

    public IPC1Practica2_201700507() {
        Image nave = new ImageIcon(getClass().getResource("/img/MilleniumFalcon.png")).getImage();
        fondo = new ImageIcon(getClass().getResource("/img/Background3.jpg")).getImage();
        listadoEnemigos = new Enemigo_201700507[1000];
        timerJuego.scheduleAtFixedRate(timer, 0, 1000);
        timerCrearEnemigos.scheduleAtFixedRate(timerCreacionEnemigos, 0, 1600);
        timerMoverEnemigos.scheduleAtFixedRate(timerMovimientoEnemigos, 0, 3);
        timerMoverDisparos.scheduleAtFixedRate(timerMovimientoDisparo, 0, 1);
        timerCrearBonus.scheduleAtFixedRate(timerCreacionBonus, 0, 10000);
        timerMoverBonus.scheduleAtFixedRate(timerMovimientoBonus, 0, 4);

        //Inicializando Nave Principal
        navePrincipal = new Nave_201700507();
        navePrincipal.setNave(nave);
        navePrincipal.setxN(1000);
        navePrincipal.setyN(285);
        navePrincipal.setVida(100);
        navePrincipal.setPuntos(0);

        addKeyListener(new AL());
        setTitle("Mini-Galaga");
        setBounds(425, 215, 1155, 650);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void moverEnemigo() {

        for (int i = 0; i < listadoEnemigos.length; i++) {
            if (listadoEnemigos[i] != null) {
                Enemigo_201700507 enemigoTemp = listadoEnemigos[i];
                enemigoTemp.setxE(enemigoTemp.getxE() + 1);

            }

        }

        colisionNave();

    }

    public void moverDisparo() {

        for (int i = 0; i < listadoDisparos.length; i++) {
            if (listadoDisparos[i] != null) {
                Disparo_201700507 disparoTemp = listadoDisparos[i];
                disparoTemp.setxD(disparoTemp.getxD() - 1);

                for (int j = 0; j < listadoEnemigos.length; j++) {
                    if (listadoEnemigos[j] != null) {
                        Enemigo_201700507 enemigoTemp = listadoEnemigos[j];
//                Disparo_201700507 disparoTem = listadoDisparos[j];

                        if (((enemigoTemp.getxE() + 81) == disparoTemp.getxD()) && ((disparoTemp.getyD() >= (enemigoTemp.getyE())) && (disparoTemp.getyD() <= (enemigoTemp.getyE() + 79)))) {
//                    System.out.println("Colision Enemigo!!!!!!!!!!!!!!!!!!!!!!");
                            enemigoTemp.setVida(enemigoTemp.getVida() - 1);
                            System.out.println(enemigoTemp.getVida());
                            disparoTemp.setyD(-60);

                            if (enemigoTemp.getVida() == 0) {
                                navePrincipal.setPuntos((navePrincipal.getPuntos() + 25));
                                contadorEnemigoDisparo++;
                                enemigoTemp.setyE(-50);
                                System.out.println(navePrincipal.getPuntos());
                            }
                        }
                    }
                }

                if (disparoTemp.getxD() < -15) {
                    listadoDisparos[i] = null;
                }

            }
        }

    }

    public void moverBonus() {

        for (int i = 0; i < listadoBonus.length; i++) {
            if (listadoBonus[i] != null) {
                Bonus_201700507 bonusTemp = listadoBonus[i];
                bonusTemp.setxB(bonusTemp.getxB() + 1);
            }
        }
        colisionBonus();
    }

    public void generarEnemigos() {
        enemigoImagen = new ImageIcon(getClass().getResource("/img/TieFighter.png")).getImage();
        Enemigo_201700507 naveEnemiga = new Enemigo_201700507();
        naveEnemiga.setEnemigo(enemigoImagen);
        int posY = (int) (Math.random() * 540 + 40);

        naveEnemiga.setxE(20);
        naveEnemiga.setyE(posY);
        naveEnemiga.setVida(3);

        int posArreglo = obtenerPosicionDisponible();

        if (posArreglo != -100) {
            listadoEnemigos[posArreglo] = naveEnemiga;
        } else {
            System.out.println("Ya no hay lugares disponibles...");
        }

    }

    public void generarDisparo() {
        disparoImagen = new ImageIcon(getClass().getResource("/img/Lightshot.png")).getImage();
        Disparo_201700507 disparo = new Disparo_201700507();
        disparo.setShoot(disparoImagen);

        disparo.setxD(950);
        disparo.setyD(navePrincipal.getyN() + 36);

        int posArreglo = obtenerPosicionDisparo();

        if (posArreglo != -100) {
            listadoDisparos[posArreglo] = disparo;

        }

    }

    public void generarBonus() {
        bonusImagen = new ImageIcon(getClass().getResource("/img/Bonus.png")).getImage();
        Bonus_201700507 bonus = new Bonus_201700507();
        bonus.setBonus(bonusImagen);
        int posY = (int) (Math.random() * 540 + 40);

        bonus.setxB(20);
        bonus.setyB(posY);

        int posArreglo = obtenerPosicionBonus();

        if (posArreglo != -100) {
            listadoBonus[posArreglo] = bonus;
        }

    }

    public void colisionNave() {
        for (int i = 0; i < listadoEnemigos.length; i++) {
            if (listadoEnemigos[i] != null) {
                Enemigo_201700507 enemigoTemp = listadoEnemigos[i];

                if (((enemigoTemp.getxE() + 81) == navePrincipal.getxN()) && ((enemigoTemp.getyE() >= (navePrincipal.getyN() - 79) && (enemigoTemp.getyE() + 79) < (navePrincipal.getyN() + 80)) || (enemigoTemp.getyE() >= (navePrincipal.getyN()) && (enemigoTemp.getyE()) <= (navePrincipal.getyN() + 79)))) {
                    navePrincipal.setPuntos(navePrincipal.getPuntos() - 10);
                    navePrincipal.setVida(navePrincipal.getVida() - 10);
                    contadorEnemigoColision++;

                    if (bonusCantidad.getCantidad() == 3) {

                        if (navePrincipal.getBonus() != 0) {
                            navePrincipal.setBonus(navePrincipal.getBonus() - 1);
                            navePrincipal.setPuntos(navePrincipal.getPuntos() + 35);
                            navePrincipal.setVida(navePrincipal.getVida() + 10);
                            enemigoTemp.setyE(-125);
                            contadorEnemigoBonus++;
                            contadorEnemigoColision--;
                        }

                    }

                    if (navePrincipal.getVida() == 0) {
                        Estadisticas_201700507 estadisticas = new Estadisticas_201700507();
                        dispose();

                    }

//                    System.out.println("Colision " + i);
                }
            }
        }
    }

    public void colisionBonus() {
        for (int i = 0; i < listadoBonus.length; i++) {
            if (listadoBonus[i] != null) {
                Bonus_201700507 bonusTemp = listadoBonus[i];

                if (((bonusTemp.getxB() + 44) == navePrincipal.getxN()) && ((bonusTemp.getyB() >= (navePrincipal.getyN() - 79) && (bonusTemp.getyB() + 49) < (navePrincipal.getyN() + 80)) || (bonusTemp.getyB() >= (navePrincipal.getyN()) && (bonusTemp.getyB()) <= (navePrincipal.getyN() + 79)))) {
                    navePrincipal.setBonus(navePrincipal.getBonus() + 1);
                    bonusCantidad.setCantidad(bonusCantidad.getCantidad() + 1);
                    bonusTemp.setyB(-80);
                    System.out.println("Bonus " + navePrincipal.getBonus());
                }
            }
        }
    }

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintComponent(Graphics g) {
        Font fuente = new Font("Copperplate Gothic Light", 1, 21);
        g.setFont(fuente);
        g.setColor(Color.WHITE);
        g.drawImage(fondo, 0, 0, null);
        g.drawImage(navePrincipal.getNave(), navePrincipal.getxN(), navePrincipal.getyN(), this);
        g.drawString("Tiempo: " + tiempo + ":00", 30, 80);
        g.drawString("Vida: " + navePrincipal.getVida() + " %", 240, 80);
        g.drawString("Puntos: " + navePrincipal.getPuntos(), 405, 80);
        g.drawString("Bonus: " + navePrincipal.getBonus(), 580, 80);

        for (int i = 0; i < listadoEnemigos.length; i++) {
            if (listadoEnemigos[i] != null) {
                Enemigo_201700507 enemigoTemp = listadoEnemigos[i];
                g.drawImage(enemigoTemp.getEnemigo(), enemigoTemp.getxE(), enemigoTemp.getyE(), this);
            }
        }

        for (int i = 0; i < listadoDisparos.length; i++) {
            if (listadoDisparos[i] != null) {
                Disparo_201700507 disparoTem = listadoDisparos[i];
                g.drawImage(disparoTem.getShoot(), disparoTem.getxD(), disparoTem.getyD(), this);
            }
            if (listadoDisparos[i] == null) {

            }
        }

        for (int i = 0; i < listadoBonus.length; i++) {
            if (listadoBonus[i] != null) {
                Bonus_201700507 bonusTemp = listadoBonus[i];
                g.drawImage(bonusTemp.getBonus(), bonusTemp.getxB(), bonusTemp.getyB(), this);
            }
        }

    }

    public static void main(String[] args) {
        Login_201700507 login = new Login_201700507();
        login.Registro();
    }

}
