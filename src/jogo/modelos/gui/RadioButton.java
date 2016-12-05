package jogo.modelos.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.DificuldadeID;
import jogo.utilitarios.Recursos;

public class RadioButton extends Objeto {

    private final BufferedImage[] texturaRadioButton;
    private boolean preenchido;
    private boolean selecionado;
    private final DificuldadeID tipo;

    public RadioButton(int x, int y, DificuldadeID id) {
        this.tipo = id;
        this.texturaRadioButton = Recursos.getTexturaRadioButton();
        this.posicao = new Point(x, y);
        this.retangulo = new Rectangle(posicao.x, posicao.y, texturaRadioButton[0].getWidth(), texturaRadioButton[0].getHeight());
        this.preenchido = false;
        this.selecionado = false;
    }    

    
    @Override
    public void atualizar() {}

    @Override
    public void renderizar() {
        if(selecionado & !preenchido) {
            Jogo.renderizar(texturaRadioButton[1], posicao);
        } else if(selecionado & preenchido) {
            Jogo.renderizar(texturaRadioButton[3], posicao);
        } else if(preenchido) {
            Jogo.renderizar(texturaRadioButton[2], posicao);
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

    public DificuldadeID getTipo() {
        return tipo;
    }
    
}
