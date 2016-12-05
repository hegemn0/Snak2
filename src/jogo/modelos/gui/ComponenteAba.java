package jogo.modelos.gui;

import java.awt.event.MouseEvent;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.AbaID;

public class ComponenteAba extends Objeto {
    private AbaID tipo;
    private PainelRadioButton painelDificuldade;
    private PainelCheckbox painelExibirGrade; 
    private PainelRanking painelRanking;
    
    public ComponenteAba(AbaID id) {
        this.tipo = id;
        this.painelDificuldade = new PainelRadioButton();
        this.painelExibirGrade = new PainelCheckbox();
        this.painelRanking = new PainelRanking();
    }
    
    @Override
    public void atualizar() {
        if(tipo.equals(AbaID.CONFIGURACOES)) {
            painelDificuldade.atualizar();
            painelExibirGrade.atualizar();
        } else if(tipo.equals(AbaID.RANKING)) {
            painelRanking.atualizar();
        }
    }

    @Override
    public void renderizar() {
        if(tipo.equals(AbaID.CONFIGURACOES)) {        
            painelDificuldade.renderizar();
            painelExibirGrade.renderizar();
        } else if(tipo.equals(AbaID.RANKING)) {
            painelRanking.renderizar();
        }
    }    
    
    public void mouseMoved(MouseEvent e) {
        if(tipo.equals(AbaID.CONFIGURACOES)) {
            painelDificuldade.mouseMoved(e);
            painelExibirGrade.mouseMoved(e);
        }        
    }
    
    public void mouseClicked(MouseEvent e) {
        if(tipo.equals(AbaID.CONFIGURACOES)) {
            painelDificuldade.mouseClicked(e);
            painelExibirGrade.mouseClicked(e);
        }
    }
   
}
