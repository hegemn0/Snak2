package jogo.modelos.gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.AbaID;
import jogo.utilitarios.BotaoID;

public class PainelAbas extends Objeto {
    private ArrayList<Aba> abas;
    
    public PainelAbas() {
        this.posicao = new Point(220, 170);
        this.abas = new ArrayList<>();
        Collections.addAll(abas, new Aba[] {
            new Aba(AbaID.CONFIGURACOES, posicao),
            new Aba(AbaID.RANKING, posicao),
            new Aba(AbaID.AJUDA, posicao),
            new Aba(AbaID.SOBRE, posicao)
        });
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void renderizar() {        
    }
    
    public Aba getAba(BotaoID id) {
        switch(id) {
            case CONFIGURACOES:                
                return abas.get(0);
            case RANKING:
                return abas.get(1);
            case AJUDA:
                return abas.get(2);
            case SOBRE:
                return abas.get(3);
            default:
                return abas.get(1);
        }        
    }    

}
