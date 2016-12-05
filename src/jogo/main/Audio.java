package jogo.main;

import java.applet.AudioClip;
import jogo.utilitarios.AudioID;
import jogo.utilitarios.Recursos;

public class Audio implements Runnable {
    private volatile int qtdPendencias = 0;
    private final int MAX_PENDENCIAS = 150;    
    private static Thread threadAudio; 
    private volatile static boolean executando;
    private volatile static boolean ativo;
    private static AudioClip[] eventosAudio;
    
    public Audio() {  
        inicializar();
    }
    
    public synchronized void registrarEvento(AudioID id) {  
        eventosAudio[qtdPendencias] = gerarAudioClip(id);
        qtdPendencias++; 
        acordar();
    }
    
    @Override
    public void run() {
        while(executando) {
            if(ativo) {
                for(int i = 0; i < qtdPendencias; i++) {
                    executarAudio(eventosAudio[i]);
                }
                qtdPendencias = 0;
                ativo = false;
            } else {
                esperar();
            }
        }
    }
    
    private synchronized void acordar() {     
        ativo = true;
        notify();
    }
    
    private synchronized void esperar() {
        try {
            wait();
        } catch (InterruptedException ex) {
            System.out.println("Erro: " + ex.toString());
        }
    }
    
    private AudioClip gerarAudioClip(AudioID id) {
        switch(id) {
            case MORDIDA_MACA: {
                return Recursos.getMordidaMaca();
            }
            case COLISAO_PARTES: {
                return Recursos.getColisaoPartes();
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
    
    private void executarAudio(AudioClip audio) {        
        try {
            audio.play();
        } catch(Exception ex) {
            System.out.println("Erro: " + ex.toString());
        } finally {
            try {
                Thread.sleep(1000); 
                audio.stop();
            } catch (InterruptedException ex) {
                 System.out.println("Erro: " + ex.toString());
            }            
        }
    }

    private void inicializar() {        
        try {
            executando = true;   
            ativo = false;            
            eventosAudio = new AudioClip[MAX_PENDENCIAS];
            threadAudio = new Thread(this, "Thread Audio");
            threadAudio.start();                  
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.toString());
        }                    
    }
    
}
