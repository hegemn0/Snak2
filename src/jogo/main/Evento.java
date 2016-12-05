package jogo.main;

import jogo.utilitarios.AudioID;
import java.applet.AudioClip;
import jogo.utilitarios.Recursos;

public class Evento {
    private final AudioID id;
    private final AudioClip audio;
    private boolean pendente;

    public Evento(AudioID id) {
        this.id = id;
        this.pendente = true;
        this.audio = gerarAudioEvento(id);
    }    

    public AudioID getID() {
        return id;
    }
    
    public AudioClip getAudio() {
        return audio;
    }  
    
    private AudioClip gerarAudioEvento(AudioID id) {
        switch(id) {
            case COLISAO_PARTES: {
                return Recursos.getColisaoPartes();
            }
            case MORDIDA_MACA: {
                return Recursos.getMordidaMaca();
            }
            case BOTAO_PRESSIONADO: {
                 return Recursos.getAudioClickBotao();   
            }
            case PAUSAR: {                
                return Recursos.getAudioPausar();
            }
            case GAMEOVER: {               
                return Recursos.getAudioGameOver();
            }
            default:
                return null;
        }
    }

    public boolean isPendente() {
        return pendente;
    }

    public void setPendente(boolean pendente) {
        this.pendente = pendente;
    }
    
}
