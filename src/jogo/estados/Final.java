package jogo.estados;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import jogo.main.Jogo;
import jogo.modelos.efeitos.Aviso;
import jogo.modelos.efeitos.GameOver;
import jogo.modelos.gui.CaixaDeTexto;

public class Final extends Estado {

    private GameOver gameOver;
    private Aviso aviso;
    private CaixaDeTexto caixaTexto;
    
    @Override
    public void inicializar() {
        aviso = new Aviso();
        gameOver = new GameOver();       
        caixaTexto = new CaixaDeTexto();
    }

    @Override
    public void atualizar() {
        aviso.atualizar();
        gameOver.atualizar();        
        caixaTexto.atualizar();
    }

    @Override
    public void renderizar() {
        if(!caixaTexto.isSelecionado()) {
            aviso.renderizar();
        }        
        
        gameOver.renderizar();
        caixaTexto.renderizar(); 
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if(caixaTexto.isSelecionado()) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    caixaTexto.confirmar();
                    break;
                case KeyEvent.VK_BACK_SPACE:
                case KeyEvent.VK_DELETE:
                    caixaTexto.apagar();
                    break;
                default:
                    caixaTexto.inserir(e.getKeyChar());
            }                                                
        } else {        
            switch(e.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    Jogo.registrarNome(caixaTexto.getNome());
                    Jogo.setEstado(new Execucao());
                    break;            
                case KeyEvent.VK_ESCAPE:
                    Jogo.registrarNome(caixaTexto.getNome());
                    Jogo.setEstado(new MenuPrincipal());
                    break;
            }
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {             
    }

    @Override
    public void mousePressed(MouseEvent e) {        
    }

    @Override
    public void mouseReleased(MouseEvent e) {        
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
    
}
