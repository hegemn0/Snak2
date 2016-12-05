package jogo.modelos.efeitos;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.Recursos;

public class Pausar extends Objeto {
    private boolean ativo;
    private BufferedImage texturaPausado;
    
    public Pausar() {
        this.texturaPausado = Recursos.getTexturaPausado();
        this.resolucao = new Dimension(texturaPausado.getWidth(), texturaPausado.getHeight());
        this.posicao = new Point(Jogo.getLargura()/2 - resolucao.width/2, Jogo.getAltura()/2 - resolucao.height/2);
        this.ativo = false;
    }    

    @Override
    public void atualizar() {}

    @Override
    public void renderizar() {
        if(ativo) {
            Jogo.renderizar(texturaPausado, posicao.x, posicao.y);
        }
    }
    
    public void ativar() {
        this.ativo = true;
    }
    
    public void desativar() {
        this.ativo = false;
    }    

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
      
}
