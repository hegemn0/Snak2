package jogo.utilitarios;

import jogo.estados.Estado;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controle implements KeyListener, MouseListener, MouseMotionListener {
    private Estado estado;
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        estado.onKeyPress(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        estado.onKeyRelease(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        estado.mouseClicked(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        estado.mouseMoved(e);
    }    
    
    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
    
}
