package jogo.utilitarios;

import java.awt.Point;
import java.util.Random;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;

public class Utilitarios {
    
    public static int numeroAleatorio(int minimo, int maximo) {        
        return new Random().nextInt(((maximo - minimo) + 1) + minimo);
    }
    
    public static Point gerarPosicao() {
        return new Point(
                new Random().nextInt(Jogo.getLargura() / Jogo.getTamanhoCelula() - 1) * Jogo.getTamanhoCelula(),
                new Random().nextInt(Jogo.getAltura() / Jogo.getTamanhoCelula() - 1) * Jogo.getTamanhoCelula()
        );        
    }
    
    public static Point gerarPosicaoAnterior(Point posicao, DirecaoID direcao) {
        switch(direcao) {
            case CIMA: {
                return new Point(posicao.x, posicao.y - Jogo.getTamanhoCelula());
            }
            case BAIXO: {
                return new Point(posicao.x, posicao.y + Jogo.getTamanhoCelula());
            }
            case ESQUERDA: {
                return new Point(posicao.x + Jogo.getTamanhoCelula(), posicao.y);
            }
            case DIREITA: {
                return new Point(posicao.x - Jogo.getTamanhoCelula(), posicao.y);
            }
            default: {
                return new Point(posicao.x, posicao.y - Jogo.getTamanhoCelula());
            }
        }        
    }
    
    public static DirecaoID gerarDirecao() {        
        switch(numeroAleatorio(0, 3)) {
            case 0: {
                return DirecaoID.CIMA;                
            }
            case 1: {
                return DirecaoID.BAIXO;
            }
            case 2: {
                return DirecaoID.ESQUERDA;
            }
            case 3: {
                return DirecaoID.DIREITA;
            }
            default: {
                return null;
            }
        }
    }
    
}
