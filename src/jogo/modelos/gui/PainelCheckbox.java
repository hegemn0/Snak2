package jogo.modelos.gui;

import java.awt.event.MouseEvent;
import jogo.main.Configuracoes;
import jogo.modelos.entidades.Objeto;

public class PainelCheckbox extends Objeto {
    private Checkbox exibirGrade;

    public PainelCheckbox() {
        this.exibirGrade = new Checkbox(351, 272);
        this.exibirGrade.setPreenchido(selecionarExibirGrade());
    }    
    
    @Override
    public void atualizar() {
        if(exibirGrade.isPreenchido()) {
            Configuracoes.setExibirGrade(true);
        } else {
            Configuracoes.setExibirGrade(false);
        }
    }

    @Override
    public void renderizar() {
        exibirGrade.renderizar();
    }
    
    public void mouseMoved(MouseEvent e) {
        if(exibirGrade.getRetangulo().contains(e.getPoint())) {
            exibirGrade.setSelecionado(true);
        } else {
            exibirGrade.setSelecionado(false);
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        if(exibirGrade.getRetangulo().contains(e.getPoint())) {
            if(!exibirGrade.isPreenchido()) {
                exibirGrade.setPreenchido(true);
            } else if(exibirGrade.isPreenchido()) {
                exibirGrade.setPreenchido(false);
            }            
        }
    }

    private boolean selecionarExibirGrade() {
        return Configuracoes.isExibirGrade();
    }
    
}
