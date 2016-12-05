package jogo.modelos.gui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import jogo.main.Configuracoes;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.DificuldadeID;

public class PainelRadioButton extends Objeto {
    private ArrayList<RadioButton> radioButtons;
    private RadioButton dificuldadeEscolhida;

    public PainelRadioButton() {
        this.radioButtons = new ArrayList<>();
        Collections.addAll(radioButtons, new RadioButton[] {
            new RadioButton(351, 357, DificuldadeID.FACIL),
            new RadioButton(351, 397, DificuldadeID.MODERADA),
            new RadioButton(351, 437, DificuldadeID.DIFICIL)
        });   
        
        dificuldadeEscolhida = selecionarDificuldade(Configuracoes.getDificuldade());
        dificuldadeEscolhida.setPreenchido(true);
    }
    
    @Override
    public void atualizar() {
        if(!Configuracoes.getDificuldade().equals(dificuldadeEscolhida.getTipo())) {
            Configuracoes.setDificuldade(dificuldadeEscolhida.getTipo());
        }
    }

    @Override
    public void renderizar() {
        for(RadioButton radioButton : radioButtons) {
            radioButton.renderizar();
        }
    }
    
    public void mouseMoved(MouseEvent e) {
        for(RadioButton radioButton : radioButtons) {
            if(radioButton.getRetangulo().contains(e.getPoint())) {
                radioButton.setSelecionado(true);
            } else {
                radioButton.setSelecionado(false);
            }
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        for(RadioButton radioButton : radioButtons) {
            if(radioButton.getRetangulo().contains(e.getPoint())) {
                dificuldadeEscolhida.setPreenchido(false);
                radioButton.setPreenchido(true);
                dificuldadeEscolhida = radioButton;                
            }
        }
    }
    
    private RadioButton selecionarDificuldade(DificuldadeID dificuldade) {
        switch(dificuldade) {
            case FACIL: 
                return radioButtons.get(0);
            case MODERADA:
                return radioButtons.get(1);
            case DIFICIL:
                return radioButtons.get(2);
            default:
                return radioButtons.get(1);                                
        }
    }    
}
