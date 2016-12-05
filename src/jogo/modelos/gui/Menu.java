package jogo.modelos.gui;

import java.awt.event.MouseEvent;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.BotaoID;

public class Menu extends Objeto {
    private PainelBotoes botoes;
    private PainelAbas abas;
    private Botao botaoPressionado;
    private Aba abaAtiva;
        
    public Menu(PainelBotoes botoes, PainelAbas abas) {
        this.botoes = botoes;
        this.abas = abas;
        
        this.abaAtiva = abas.getAba(BotaoID.RANKING);
    }

    @Override
    public void atualizar() {
        for(Botao botao : botoes.getBotoes()) {
            botao.atualizar();
        }
        
        abaAtiva.atualizar();
    }

    @Override
    public void renderizar() {
        for(Botao botao : botoes.getBotoes()) {
            botao.renderizar();
        }
        
        abaAtiva.renderizar();
    }
    
    public void mouseMoved(MouseEvent e) {
        abaAtiva.mouseMoved(e);
        for(Botao botao : botoes.getBotoes()) {
            if(botao.getRetangulo().contains(e.getPoint())) {
                botao.setSelecionado(true);
            } else {
                botao.setSelecionado(false);
            }
        }        
    }
    
    public void mouseClicked(MouseEvent e) {
        abaAtiva.mouseClicked(e);
        for(Botao botao : botoes.getBotoes()) {
            if(botao.getRetangulo().contains(e.getPoint()) & !botao.isPressionado()) {
                if(botaoPressionado != null) 
                    botaoPressionado.setPressionado(false);
                botao.setPressionado(true);
                botaoPressionado = botao;
                abaAtiva = abas.getAba(botao.getTipo());
            }
        }        
    }
    
}
