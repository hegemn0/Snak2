package jogo.modelos.efeitos;

import java.awt.Color;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;

public class Transicao extends Objeto {
    private final int CANALALFA = 0;
    private int canalAlfa;
    private int velocidade;    
    private boolean completa;
    private boolean ativada;

    public Transicao() {
        this.canalAlfa = CANALALFA;
        this.velocidade = 5;
        this.completa = false;
        this.ativada = false;
    }    
    
    public void ativar() {
        if(!ativada) {
            canalAlfa = CANALALFA;
            completa = false;
            ativada = true;
        }
    }
    
    @Override
    public void atualizar() {
        if(!completa) {
            if(ativada) {
                if(canalAlfa < 255) {
                    canalAlfa += velocidade;
                } else {
                    completa = true;
                }        
            }
        }
    }

    @Override
    public void renderizar() {
        if(ativada) {
            Jogo.getTela().setColor(new Color(0, 0, 0, canalAlfa));
            Jogo.getTela().fillRect(0, 0, Jogo.getLargura(), Jogo.getAltura());
        }
    }

    public boolean isCompleta() {
        return completa;
    }
    
    public boolean isAtivada() {
        return ativada;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getVelocidade() {
        return velocidade;
    }
    
    
}
