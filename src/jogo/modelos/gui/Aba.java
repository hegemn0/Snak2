package jogo.modelos.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.AbaID;
import jogo.utilitarios.Recursos;

public class Aba extends Objeto {
    private BufferedImage texturaAba;
    private AbaID tipo;
    private ComponenteAba componenteAba;

    public Aba(AbaID id, Point posicao) {
        this.tipo = id;
        this.texturaAba = selecionarTextura(id);
        this.posicao = posicao;
        this.componenteAba = new ComponenteAba(id);
    }
    
    @Override
    public void atualizar() {
        componenteAba.atualizar();
    }

    @Override
    public void renderizar() {
        Jogo.renderizar(texturaAba, posicao);
        componenteAba.renderizar();
    }
    
    private BufferedImage selecionarTextura(AbaID id) {
        switch(id) {
            case CONFIGURACOES:
                return Recursos.getTexturaAbaConfiguracoes();
            case RANKING:
                return Recursos.getTexturaAbaRanking();
            case AJUDA:
                return Recursos.getTexturaAbaAjuda();
            case SOBRE:
                return Recursos.getTexturaAbaSobre();
            default:
                return null;
        }
    }
    
    public void mouseMoved(MouseEvent e) {
        componenteAba.mouseMoved(e);
    }
    
    public void mouseClicked(MouseEvent e) {
        componenteAba.mouseClicked(e);
    }    

    public AbaID getTipo() {
        return tipo;
    }
    
}
