package jogo.modelos.gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.BotaoID;

public class PainelBotoes extends Objeto {
    private ArrayList<Botao> botoes;
    
    public PainelBotoes() {
        this.posicao = new Point(50, 170);
        this.botoes = new ArrayList<Botao>();                
        Collections.addAll(botoes, new Botao[] {
            new Botao(BotaoID.INICIAR),
            new Botao(BotaoID.CONFIGURACOES),
            new Botao(BotaoID.RANKING),
            new Botao(BotaoID.AJUDA),
            new Botao(BotaoID.SOBRE),
            new Botao(BotaoID.SAIR)
        }); 
    }
    
    @Override
    public void atualizar() {
        for(Botao botao : botoes) {
            botao.atualizar();               
        }
    }

    @Override
    public void renderizar() {
        for(Botao botao : botoes) {
            botao.renderizar();
        }
    }    
    
    public ArrayList<Botao> getBotoes() {
        return botoes;
    }
    
}