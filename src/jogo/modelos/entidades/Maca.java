package jogo.modelos.entidades;

import jogo.utilitarios.MacaID;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import jogo.main.Jogo;
import jogo.utilitarios.Recursos;
import jogo.utilitarios.Utilitarios;

public class Maca extends Objeto {  
    private MacaID tipo;
    private BufferedImage[] texturaMaca;
    
    public Maca() {
        this.texturaMaca = Recursos.getTexturaMaca();
        this.resolucao = new Dimension(texturaMaca[0].getWidth(), texturaMaca[0].getHeight());
        this.debug = true;
        this.posicaoAnterior = new Point();
        this.tipo = gerarMaca();
        this.posicao = gerarPosicao();
    }
    
    @Override
    public void atualizar() {}    
    
    @Override
    public void renderizar() {
        Jogo.getTela().drawImage(texturaMaca[tipo.ordinal()], posicao.x, posicao.y, null);        
    }
    
    public void colisaoCobra() {
        reset();
    }
    
    public void colisaoParte() {
        reset();
    }
    
    public void reset() {
        posicao = gerarPosicao();
        tipo = gerarMaca();
    }
    
    public String depuramento() {
        return "Tipo : Maça" + "\n" +
                "Tempo: " + LocalTime.now() + "\n" +
                "Posição Atual: " + "[" + posicao.x + ", " + posicao.y + "]" + "\n" +
                "Posição Anterior: " + "[" + posicaoAnterior.x + ", " + posicaoAnterior.y + "]" + "\n" +
                "Valor: " + tipo.getValor()  + "\n";       
    }       
      
    private Point gerarPosicao() {         
        if(posicao != null)
            posicaoAnterior = new Point(posicao.x, posicao.y);

        return Utilitarios.gerarPosicao();
    }
    
    private MacaID gerarMaca() {
        switch(Utilitarios.numeroAleatorio(0, 2)) {
            case 0: {
                return MacaID.VERDE;
            }
            case 1: {
                return MacaID.VERMELHA;
            }
            case 2: {
                return MacaID.DOURADA;
            }
            default: {
                return MacaID.VERMELHA;
            }
        }
    }

    public int getValor() {
        return tipo.getValor();
    }
    
}
