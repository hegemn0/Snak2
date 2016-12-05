package jogo.modelos.efeitos;

import java.awt.Point;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.Recursos;

public class Pontuacao extends Objeto {
    private int pontos;
    private int tempo;
    private boolean ativo;
    
    public Pontuacao() {
        this.textura = Recursos.getTexturaPontuacao();
        this.ativo = false;
    }
    
    public void iniciar(Point posicao, int pontos) {
        this.posicao = new Point(posicao.x, posicao.y);
        this.pontos = pontos;
        this.tempo = 5;
        this.ativo = true;
    }
    
    @Override
    public void atualizar() {
        tempo--;
    }
    
    @Override
    public void renderizar() {
        if(tempo > 0) {
            switch(pontos) {
                case 1: {
                    Jogo.getTela().drawImage(textura[0], posicao.x, posicao.y--, null);
                } break;
                case 5: {
                    Jogo.getTela().drawImage(textura[1], posicao.x, posicao.y--, null);
                } break;
                case 10: {
                    Jogo.getTela().drawImage(textura[2], posicao.x, posicao.y--, null);
                } break;            
            }        
        } else {
            ativo = false;
        }
    }

    public boolean isAtivo() {
        return ativo;
    }
}
