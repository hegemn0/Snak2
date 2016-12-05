package jogo.modelos.entidades;

import java.awt.Dimension;
import java.awt.Point;
import jogo.modelos.gui.HUD;

public class Jogador extends Objeto {
    private HUD hud;
    private int qtdPontos;
    private int qtdVidas;
    private boolean gameOver;
    private boolean pausado;    
    
    public Jogador(HUD hud) {
        this.qtdPontos = 0;
        this.qtdVidas = 3;                
        this.posicao = new Point(5, 5);
        this.resolucao = new Dimension(100, 100);        
        this.gameOver = false;
        this.pausado = false;
        this.hud = hud;
    }         
    
    public void subtrairVida() {
        qtdVidas--;
        hud.setQtdVidas(qtdVidas);
    }
    
    public void adicionarPontos(int pontos) {
        qtdPontos += pontos;
        hud.setQtdPontos(qtdPontos);
    }
    
    @Override
    public void atualizar() {
        if(qtdVidas <= 0) {
            gameOver = true;
        }
    }
    
    @Override
    public void renderizar() {
    }    

    public int getQtdPontos() {
        return qtdPontos;
    }

    public int getQtdVidas() {
        return qtdVidas;
    }

    public boolean isPausado() {
        return pausado;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

}
