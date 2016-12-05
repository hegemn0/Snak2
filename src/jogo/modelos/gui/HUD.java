package jogo.modelos.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferedImage;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.Recursos;

public class HUD extends Objeto { 
    private BufferedImage texturaCoracao; 
    private int qtdPontos;
    private int tempoAnimacaoPontos;
    private boolean qtdPontosModificada;
    private Font fonte;
    private int qtdVidas;
    private Point posicaoVidas;
    private Point posicaoPontos;

    public HUD() {
        this.texturaCoracao = Recursos.getTexturaCoracao();
        this.qtdPontos = 0;
        this.qtdVidas = 3;
        this.tempoAnimacaoPontos = 0;
        this.qtdPontosModificada = false;        
        this.posicaoVidas = new Point(16, 16);   
        this.posicaoPontos = new Point(Jogo.getLargura() - 128, 40);        
        this.fonte = new Font("Sans", Font.BOLD, 32);
    }    
    
    @Override
    public void atualizar() {        
        if(qtdPontosModificada) {
            tempoAnimacaoPontos--;   
        }
    }

    @Override
    public void renderizar() {  
        if(!Jogo.getTela().getFont().equals(fonte)) {
            Jogo.getTela().setFont(fonte);
        }
                
        Jogo.getTela().setColor(new Color(47, 72, 88));
        Jogo.getTela().drawString(String.valueOf(qtdPontos), posicaoPontos.x + 2, posicaoPontos.y + 2);  
        
        if(tempoAnimacaoPontos > 0) {
            Jogo.getTela().setColor(new Color(27, 216, 94));
        } else {
            Jogo.getTela().setColor(new Color(22, 178, 77));
        }                 
        
        Jogo.getTela().drawString(String.valueOf(qtdPontos), posicaoPontos.x, posicaoPontos.y);
        
        if(qtdVidas > 0) {
            for(int i = 0; i < qtdVidas; i++) {
                Jogo.getTela().drawImage(texturaCoracao, posicaoVidas.x + texturaCoracao.getWidth() * i, posicaoVidas.y, null);
            }
        }        
    }
 
    public void setQtdPontos(int qtdPontos) {
        this.qtdPontos = qtdPontos;
        qtdPontosModificada = true;
        tempoAnimacaoPontos = 3;
    }

    public void setQtdVidas(int qtdVidas) {
        this.qtdVidas = qtdVidas;
    }
    
}