package jogo.modelos.gui;

import java.awt.Point;
import java.util.ArrayList;
import jogo.main.Jogo;
import jogo.main.Resultado;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.Recursos;

class PainelRanking extends Objeto {
    private ArrayList<Resultado> ranking;
    private final int ESPACAMENTO = 25;
    
    private Point posicaoRank;
    private Point posicaoPontuacao;
    private Point posicaoNome;

    public PainelRanking() {
        this.posicaoRank = new Point(368, 287);
        this.posicaoPontuacao = new Point(432, 287);
        this.posicaoNome = new Point(576, 287);
        this.ranking = new ArrayList<>();
    }

    @Override
    public void atualizar() {
        if(!ranking.equals(Jogo.getRanking())) {
            ranking = Jogo.getRanking();                    
        }
    }

    @Override
    public void renderizar() {
        for(int i = 0; i < ranking.size()-1; i++) {
            Jogo.renderizarString(Recursos.getCorEscura(), Recursos.getFontePadrao(), String.valueOf(ranking.get(i).getPosicao()), false, posicaoRank.x, posicaoRank.y + ESPACAMENTO * i);
            Jogo.renderizarString(Recursos.getCorEscura(), Recursos.getFontePadrao(), String.valueOf(ranking.get(i).getPontuacao()), false, posicaoPontuacao.x, posicaoPontuacao.y + ESPACAMENTO * i);
            Jogo.renderizarString(Recursos.getCorEscura(), Recursos.getFontePadrao(), ranking.get(i).getNome(), false, posicaoNome.x, posicaoNome.y + ESPACAMENTO * i);
        }        
    }
    
}
