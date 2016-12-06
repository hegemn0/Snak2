package jogo.estados;

import jogo.main.Jogo;
import jogo.modelos.entidades.Cobra;
import jogo.modelos.entidades.Maca;
import jogo.modelos.gui.Grade;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import jogo.utilitarios.AudioID;
import jogo.modelos.efeitos.Pausar;
import jogo.modelos.entidades.Jogador;
import jogo.modelos.efeitos.Transicao;
import jogo.modelos.gui.Background;
import jogo.modelos.gui.HUD;
import jogo.utilitarios.DirecaoID;

public class Execucao extends Estado {
        
    private float contador;
    
    private Background background;
    private Grade grade;
    private Transicao transicao;  
    private Pausar pausar;
    private HUD hud;
    
    private Jogador jogador;    
    private Cobra cobra;
    private Maca maca;
    
    @Override
    public void inicializar() {  
        contador = Jogo.getVelocidade();
        background = new Background();
        grade = new Grade();        
        hud = new HUD();
        jogador = new Jogador(hud);   
        cobra = new Cobra();
        maca = new Maca();
        transicao = new Transicao();  
        pausar = new Pausar();
    } 
    
    @Override
    public void atualizar() {
        contador -= Jogo.getDeltaTempo();                
        if(contador <= 0) {        
            if(!jogador.isGameOver()) {
                if(!jogador.isPausado()) {            
                    reiniciarContador();                    
                    atualizarObjetos();         
                } else {
                    atualizarPausar();
                }
            } else {
                atualizarGameOver();
            }
        }
        detectarColisoes();
    } 
    
    @Override
    public void renderizar() {
        renderizarObjetos();
        
        if(jogador.isGameOver())
            renderizarGameOver();
        
        if(jogador.isPausado())
            renderizarPausar();
    }
    
//<editor-fold defaultstate="collapsed" desc="comment">
    
    @Override
    public void onKeyRelease(KeyEvent e) {};
    
    @Override
    public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseDragged(MouseEvent e) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    
    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
//</editor-fold>
    
    @Override
    public void onKeyPress(KeyEvent e) {
        switch(e.getKeyCode()) {          
            case KeyEvent.VK_SPACE: {
                pausar();
            } break;
            case KeyEvent.VK_UP: {
                mudarDirecao(DirecaoID.CIMA);   
            } break;
            case KeyEvent.VK_DOWN: {
                mudarDirecao(DirecaoID.BAIXO);  
            } break;
            case KeyEvent.VK_LEFT: {
                mudarDirecao(DirecaoID.ESQUERDA); 
            } break;                  
            case KeyEvent.VK_RIGHT: {
                mudarDirecao(DirecaoID.DIREITA); 
            } break;                                                          
        }
    };

    private void atualizarObjetos() {
        atualizarGUI();
        atualizarObjetosLogicos();         
        atualizarObjetosFuncionais();        
    }   
    
    private void atualizarObjetosFuncionais() {                      
        maca.atualizar();
        cobra.atualizar();         
    }    
    
    private void atualizarObjetosLogicos() {
        jogador.atualizar();
    }    
    
    private void atualizarGUI() {
        background.atualizar();
        grade.atualizar();
        hud.atualizar();
    }
    
    private void renderizarObjetos() {          
        renderizarGUIFundos(); 
        renderizarObjetosFuncionais();
        renderizarGUIFrente();        
    }    
    
    private void renderizarGUIFundos() {
        background.renderizar();
        grade.renderizar();
    }

    private void renderizarObjetosFuncionais() {        
        maca.renderizar();
        cobra.renderizar();
    }
    
    private void renderizarGUIFrente() {       
        hud.renderizar();        
    }    
    
    private void detectarColisoes() {                
        detectarColisaoCobraEMaca();
        detectarColisaoMacaEPartes();
        detectarColisaoCobraEPartes();              
        detectarColisaoCobraEBordas();        
    }

    private void detectarColisaoCobraEBordas() {
        if(cobra.getX() >= Jogo.getLargura()) {
            cobra.setPosicao(new Point(0, cobra.getY()));
        }

        if(cobra.getX() < 0) {
            cobra.setPosicao(new Point(Jogo.getLargura() - Jogo.getTamanhoCelula(), cobra.getY()));
        }
        
        if(cobra.getY() >= Jogo.getAltura()) {
            cobra.setPosicao(new Point(cobra.getX(), 0));
        }               
        
        if(cobra.getY() < 0) {
            cobra.setPosicao(new Point(cobra.getX(), Jogo.getAltura() - Jogo.getTamanhoCelula()));
        } 
    }

    private void detectarColisaoCobraEPartes() {
        if(cobra.getEmMovimento()) {
            for(Point parte : cobra.getPosicoesPartes()) {
                if(cobra.getPosicao().equals(parte)) {
                    jogador.subtrairVida();
                    cobra.colisaoParte(); 
                }
            }
        }
    }

    private void detectarColisaoCobraEMaca() {
        if(cobra.getEmMovimento()) {
            if(cobra.getPosicao().equals(maca.getPosicao())) {            
                jogador.adicionarPontos(maca.getValor());
                cobra.colisaoMaca(maca);
            }
        }
    }
    
    private void detectarColisaoMacaEPartes() {
        for(Point posicao : cobra.getPosicoes()) {
            if(posicao.equals(maca.getPosicao())) {
                maca.colisaoParte();
            }
        }
    }
    
    private void reiniciarContador() {
        contador = Jogo.getVelocidade();
    }
    
    private void pausar() {
        jogador.setPausado(!jogador.isPausado());
        pausar.setAtivo(!pausar.isAtivo());
        audioPausar();
    }  
    
    private void mudarDirecao(DirecaoID id) {
        cobra.setDirecao(id);
    }
    
    private void atualizarPausar() {
        if(jogador.isPausado()) {
            pausar.atualizar();
        }
    }
    
    private void renderizarPausar() {
        pausar.renderizar();
    }    
    
    private void atualizarGameOver() {                            
        if(!transicao.isAtivada()) {
            transicao.ativar();
            audioGameOver();
        } else if(transicao.isAtivada() & !transicao.isCompleta()) {
            transicao.atualizar();
        } else {
            Jogo.registrarPontuacao(jogador.getQtdPontos());
            Jogo.setEstado(new Final());
        }
    }
    
    private void renderizarGameOver() {
        transicao.renderizar();
    }    
    
    private void audioPausar() {
        Jogo.executarAudio(AudioID.PAUSAR);        
    }
    
    private void audioGameOver() {
        Jogo.executarAudio(AudioID.GAMEOVER);        
    }
    
}
