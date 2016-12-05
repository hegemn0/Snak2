package jogo.modelos.efeitos;

import java.awt.Point;
import java.awt.image.BufferedImage;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.Recursos;

public class GameOver extends Objeto {
    private BufferedImage texturaGameOver;
    
    public GameOver() {
        this.texturaGameOver = Recursos.getTexturaGameOver();
        this.posicao = new Point(Jogo.getLargura()/2 - texturaGameOver.getWidth()/2, 100);
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void renderizar() {               
        renderizarTextoGameOver();
    }

    private void renderizarTextoGameOver() {
        Jogo.getTela().drawImage(texturaGameOver, posicao.x, posicao.y, null);                
    }
    
}
