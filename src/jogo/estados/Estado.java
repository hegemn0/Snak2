package jogo.estados;

import jogo.main.Jogo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Estado {
    
    public abstract void inicializar();
    
    public abstract void atualizar(); 
    
    public abstract void renderizar();
    
    public abstract void onKeyPress(KeyEvent e);
    
    public abstract void onKeyRelease(KeyEvent e);
    
    public abstract void mouseClicked(MouseEvent e);    
    
    public abstract void mousePressed(MouseEvent e);
    
    public abstract void mouseReleased(MouseEvent e);    
    
    public abstract void mouseDragged(MouseEvent e);
    
    public abstract void mouseMoved(MouseEvent e);
    
    public void setEstado(Estado novoEstado) {
        Jogo.setEstado(novoEstado);
    }
}
