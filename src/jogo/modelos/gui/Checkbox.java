package jogo.modelos.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.Recursos;

public class Checkbox extends Objeto {
    private final BufferedImage[] texturaCheckbox;
    private boolean preenchido;
    private boolean selecionado;

    public Checkbox(int x, int y) {
        this.texturaCheckbox = Recursos.getTexturaCheckbox();
        this.posicao = new Point(x, y);
        this.retangulo = new Rectangle(posicao.x, posicao.y, texturaCheckbox[0].getWidth(), texturaCheckbox[0].getHeight());
        this.preenchido = false;
        this.selecionado = false;
    }    
    
    public Checkbox(Point posicao) {
        this.texturaCheckbox = Recursos.getTexturaCheckbox();
        this.posicao = posicao;
        this.retangulo = new Rectangle(posicao.x, posicao.y, texturaCheckbox[0].getWidth(), texturaCheckbox[0].getHeight());
        this.preenchido = false;
        this.selecionado = false;
    }
    
    @Override
    public void atualizar() {}

    @Override
    public void renderizar() {
        if(selecionado & !preenchido) {
            Jogo.renderizar(texturaCheckbox[1], posicao);
        } else if(selecionado & preenchido) {
            Jogo.renderizar(texturaCheckbox[3], posicao);
        } else if(preenchido) {
            Jogo.renderizar(texturaCheckbox[2], posicao);            
        }
    }

    public boolean isPreenchido() {
        return preenchido;
    }

    public void setPreenchido(boolean preenchido) {
        this.preenchido = preenchido;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
    
}
