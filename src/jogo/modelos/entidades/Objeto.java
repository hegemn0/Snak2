package jogo.modelos.entidades;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import jogo.utilitarios.DirecaoID;

public abstract class Objeto implements Atualizavel, Renderizavel {
    protected BufferedImage[] textura;
    protected Point posicao;
    protected Dimension resolucao;    
    protected DirecaoID direcao;
    protected DirecaoID direcaoAnterior;
    protected Point posicaoAnterior;
    protected boolean debug;
    protected Rectangle retangulo;    
    
    public void setPosicao(Point posicao) {
        this.posicao = new Point(posicao.x, posicao.y);
    }
    
    public Point getPosicao() {
        return posicao;
    };    
    
    public int getX() {
        return posicao.x;
    };
    
    public int getY() {
        return posicao.y;
    };    

    public int getLargura() {
        return resolucao.width;
    }

    public int getAltura() {
        return resolucao.height;
    }
    
    public Dimension getResolucao() {
        return resolucao;
    }
    
    public DirecaoID getDirecao() {
        return direcao;
    }

    public Point getPosicaoAnterior() {
        return posicaoAnterior;
    }

    public boolean isDebug() {
        return debug;
    }

    public DirecaoID getDirecaoAnterior() {
        return direcaoAnterior;
    }

    public Rectangle getRetangulo() {
        return retangulo;
    }    
}
