package jogo.modelos.efeitos;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.Recursos;

public class Aviso extends Objeto {
    private BufferedImage texturaAviso;
    private boolean transparente;
    private int alfa;
    private int velocidade;
    
    public Aviso() {
        this.texturaAviso = Recursos.getTexturaAvisoGameOver();
        this.posicao = new Point(Jogo.getLargura()/2 - texturaAviso.getWidth()/2, Jogo.getAltura() - texturaAviso.getHeight());        
        this.transparente = false;
        this.velocidade = 5;
        this.alfa = 0;
    }
    
    @Override
    public void atualizar() {
        if(alfa >= 255) { 
            transparente = true;
        } else if(alfa <= 0) {                    
            transparente = false;
        } 

        if(transparente) {
            alfa -= velocidade;
        } else {
            alfa += velocidade;
        }        
    }

    @Override
    public void renderizar() {
        renderizarAviso();        
        renderizarEfeito();
    }
    
    private void renderizarEfeito() {
        Jogo.getTela().setColor(new Color(0, 0, 0, alfa));
        Jogo.getTela().fillRect(0, 0, Jogo.getLargura(), Jogo.getAltura());                  
    }
    
    private void renderizarAviso() {
        Jogo.getTela().drawImage(texturaAviso, posicao.x, posicao.y, null);  
    }    

}
