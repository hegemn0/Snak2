package jogo.modelos.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.AudioID;
import jogo.utilitarios.BotaoID;
import jogo.utilitarios.Recursos;

public class Botao extends Objeto {
    private BufferedImage texturaBotao[];
    private boolean pressionado;
    private boolean selecionado;
    private BotaoID tipo;
    private ComponenteBotao componente;
    
    public Botao(BotaoID id) {
        this.tipo = id;
        this.texturaBotao = selecionarTextura(id);
        this.posicao = selecionarPosicao(id);
        this.componente = new ComponenteBotao(id);
        this.retangulo = new Rectangle(posicao.x, posicao.y, texturaBotao[0].getWidth(), texturaBotao[0].getHeight());
        this.pressionado = false;
        this.selecionado = false;
    }
    
    @Override
    public void atualizar() {
        if(pressionado) {
            componente.pressionado();
        }
    }

    @Override
    public void renderizar() {
        if(pressionado) {
            Jogo.renderizar(texturaBotao[1], posicao);
        } else if(selecionado) {
            Jogo.renderizar(texturaBotao[0], posicao);
            Jogo.renderizar(texturaBotao[2], posicao);
        } else {
            Jogo.renderizar(texturaBotao[0], posicao);
        }
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }    
    
    public boolean isPressionado() {
        return pressionado;
    }

    public void setPressionado(boolean pressionado) {
        if(pressionado) { audioBotaoPressionado(); }
        this.pressionado = pressionado;
    }

    public BotaoID getTipo() {
        return tipo;
    }

    private BufferedImage[] selecionarTextura(BotaoID id) {
        switch(id) {
            case INICIAR:
                return Recursos.getTexturaBotaoIniciar();
            case CONFIGURACOES:
                return Recursos.getTexturaBotaoConfiguracoes();
            case RANKING:
                return Recursos.getTexturaBotaoRanking();
            case AJUDA:
                return Recursos.getTexturaBotaoAjuda();
            case SOBRE:
                return Recursos.getTexturaBotaoSobre();
            case SAIR:
                return Recursos.getTexturaBotaoSair();
            default:
                return null;
        }
    }    
    
    private Point selecionarPosicao(BotaoID id) {
        switch(id) {
            case INICIAR:
                return new Point(50, 170);
            case CONFIGURACOES:
                return new Point(50, 241);
            case RANKING:
                return new Point(50, 312);
            case AJUDA:
                return new Point(50, 383);
            case SOBRE:
                return new Point(50, 454);
            case SAIR:
                return new Point(50, 525);
            default:
                return null;
        }
    }   
    
    private void audioBotaoPressionado() {
        Jogo.executarAudio(AudioID.BOTAO_PRESSIONADO);
    }
        
}
