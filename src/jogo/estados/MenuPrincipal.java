package jogo.estados;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import jogo.modelos.gui.Background;
import jogo.modelos.gui.Menu;
import jogo.modelos.gui.PainelBotoes;
import jogo.modelos.gui.PainelAbas;
import jogo.utilitarios.Recursos;

public class MenuPrincipal extends Estado {
    private Menu menu;
    private Background background;
    private PainelBotoes botoes;
    private PainelAbas abas;

    @Override
    public void inicializar() {
        background = new Background(Recursos.getBackgroundMenuPrincipal());
        botoes = new PainelBotoes();
        abas = new PainelAbas();
        menu = new Menu(botoes, abas);
    };

    @Override
    public void atualizar() {
        menu.atualizar();
    }
    
    @Override
    public void renderizar() {
        background.renderizar();
        menu.renderizar();
    };

    @Override
    public void mouseMoved(MouseEvent e) {   
        menu.mouseMoved(e);
    }     
    
    @Override
    public void mouseClicked(MouseEvent e) {
        menu.mouseClicked(e);
    }
    
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
    public void onKeyPress(KeyEvent e) {
    };
    
    @Override
    public void onKeyRelease(KeyEvent e) {
    };  
 
}
