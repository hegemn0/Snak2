package jogo.estados;

import jogo.utilitarios.Recursos;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Carregamento extends Estado {
    
    @Override
    public void inicializar() {
        Recursos.carregar();
    };

    @Override
    public void atualizar() {
        setEstado(new MenuPrincipal());
    }
    
    @Override
    public void renderizar() {};
    
    @Override
    public void onKeyPress(KeyEvent e) {};
    
    @Override
    public void onKeyRelease(KeyEvent e) {};     

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {      
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}
