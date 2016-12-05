package jogo.modelos.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.Recursos;

public class Background extends Objeto {
    private BufferedImage texturaBackground;
    
    public Background() {
        this.texturaBackground = Recursos.getTexturaBackgroundJogo();
        this.posicao = new Point(0, 0);
        this.resolucao = new Dimension(Jogo.getLargura(), Jogo.getAltura());
    }
    
    public Background(BufferedImage imagem) {
        this.texturaBackground = imagem;
        this.posicao = new Point(0, 0);
        this.resolucao = new Dimension(Jogo.getLargura(), Jogo.getAltura());
    }    

    @Override
    public void atualizar() {}

    @Override
    public void renderizar() {
        Jogo.renderizar(texturaBackground, posicao);
    }

}
