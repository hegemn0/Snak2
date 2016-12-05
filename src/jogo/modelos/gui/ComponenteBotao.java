package jogo.modelos.gui;

import jogo.estados.Execucao;
import jogo.main.Jogo;
import jogo.utilitarios.BotaoID;

class ComponenteBotao {
    private BotaoID tipo;
    
    public ComponenteBotao(BotaoID id) {
        this.tipo = id;
    }
    
    public void pressionado() {
        switch(tipo) {
            case INICIAR:
                Jogo.setEstado(new Execucao());
                break;
            case SAIR:
                Jogo.finalizar();
                break;
        }
    }
}
