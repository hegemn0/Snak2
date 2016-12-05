package jogo.modelos.gui;

import jogo.main.Jogo;
import java.awt.Color;
import jogo.main.Configuracoes;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.Recursos;

public class Grade extends Objeto {
    private Color corGrade;
    private boolean exibir;
    
    public Grade() {
        this.corGrade = Recursos.getCorEscura();
        this.exibir = Configuracoes.isExibirGrade();
    }
    
    @Override
    public void atualizar() {}
    
    @Override
    public void renderizar() {
        if(exibir) {
            if(!corGrade.equals(Jogo.getTela().getColor())) 
                Jogo.getTela().setColor(corGrade);

            for(int largura = Jogo.getTamanhoCelula(); largura < Jogo.getLargura(); largura += Jogo.getTamanhoCelula()) {            
                for(int altura = Jogo.getTamanhoCelula(); altura < Jogo.getAltura(); altura += Jogo.getTamanhoCelula()) {
                    Jogo.getTela().drawLine(0, altura, Jogo.getLargura(), altura);
                    Jogo.getTela().drawLine(largura, 0, largura, Jogo.getAltura());
                }
            }
        }
    }
    
}
