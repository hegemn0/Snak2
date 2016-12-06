package jogo.utilitarios;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Recursos {
    private static final String DIR_IMAGENS = "/jogo/recursos/imagens/";
    private static final String DIR_AUDIO = "/jogo/recursos/audio/";    
    
    private static Font fontePadrao;
    private static Color[] paletaCores;
    private static BufferedImage texturaIcone;
    private static BufferedImage texturaAvisoGameOver;
    private static BufferedImage texturaPausado;
    private static BufferedImage texturaGameOver;
    private static BufferedImage texturaCoracao;
    private static BufferedImage texturaBackgroundMenu;
    private static BufferedImage texturaAbaConfiguracoes;
    private static BufferedImage texturaAbaRanking;
    private static BufferedImage texturaAbaAjuda;
    private static BufferedImage texturaAbaSobre;   
    private static BufferedImage texturaBackgroundJogo;
    private static BufferedImage[] texturaPontuacao;
    private static BufferedImage[] texturaBotoes;
    private static BufferedImage[] texturaBotaoIniciar;
    private static BufferedImage[] texturaBotaoConfiguracoes;
    private static BufferedImage[] texturaBotaoRanking;
    private static BufferedImage[] texturaBotaoAjuda;
    private static BufferedImage[] texturaBotaoSobre;
    private static BufferedImage[] texturaBotaoSair;
    private static BufferedImage[] texturaCheckbox;
    private static BufferedImage[] texturaRadioButton;
    private static BufferedImage[] texturaPaineis;
    private static BufferedImage[] texturaMaca;
    private static BufferedImage[] texturaCobra;  
    private static AudioClip audioClickBotao;
    private static AudioClip audioPausar;
    private static AudioClip audioGameOver;    
    private static AudioClip[] audioMordidaMaca;
    private static AudioClip[] audioColisaoPartes;
    
    public static void carregar() { 
        
        fontePadrao = new Font("DejaVu Sans Mono", Font.TRUETYPE_FONT, 20);
        
        paletaCores = new Color[] {
            new Color(27, 216, 94), 
            new Color(22, 178, 77),
            new Color(54, 53, 55)
        };
        
        texturaIcone = carregarImagem("icone_01.png");     
        texturaAvisoGameOver = carregarImagem("aviso_gameover_01.png");        
        texturaPausado = carregarImagem("pausado_01.png");   
        texturaAbaConfiguracoes = carregarImagem("aba_configuracoes_01.png");
        texturaAbaRanking = carregarImagem("aba_ranking_01.png");
        texturaAbaAjuda = carregarImagem("aba_ajuda_01.png");
        texturaAbaSobre = carregarImagem("aba_sobre_01.png");       
        texturaBackgroundMenu = carregarImagem("menu_principal_01.png");        
        texturaBackgroundJogo = carregarImagem("background_01.png");        
        texturaCoracao = carregarImagem("coracao_01.png");
        texturaGameOver = carregarImagem("gameover_01.png");
        
        texturaPontuacao = new BufferedImage[] {
            carregarImagem("pontuacao_01.png"),
            carregarImagem("pontuacao_02.png"),
            carregarImagem("pontuacao_03.png")
        };                        
        
        texturaBotaoIniciar = new BufferedImage[] {
            carregarImagem("botao_iniciar_01.png"),
            carregarImagem("botao_iniciar_02.png"),
            carregarImagem("botao_transparente_01.png")
        };
        
        texturaBotaoConfiguracoes = new BufferedImage[] {
            carregarImagem("botao_configuracoes_01.png"),
            carregarImagem("botao_configuracoes_02.png"),
            carregarImagem("botao_transparente_01.png")
        };
        
        texturaBotaoRanking = new BufferedImage[] {
            carregarImagem("botao_ranking_01.png"),
            carregarImagem("botao_ranking_02.png"),
            carregarImagem("botao_transparente_01.png")
        };
        
        texturaBotaoAjuda = new BufferedImage[] {
            carregarImagem("botao_ajuda_01.png"),
            carregarImagem("botao_ajuda_02.png"),
            carregarImagem("botao_transparente_01.png")
        };
        
        texturaBotaoSobre = new BufferedImage[] {
            carregarImagem("botao_sobre_01.png"),
            carregarImagem("botao_sobre_02.png"),
            carregarImagem("botao_transparente_01.png")
        };        
        
        texturaBotaoSair = new BufferedImage[] {
            carregarImagem("botao_sair_01.png"),
            carregarImagem("botao_sair_02.png"),
            carregarImagem("botao_transparente_01.png")
        };
        
        texturaRadioButton = new BufferedImage[] {
            carregarImagem("radio_button_01.png"),
            carregarImagem("radio_button_02.png"),
            carregarImagem("radio_button_03.png"),
            carregarImagem("radio_button_04.png")
        };

        texturaCheckbox = new BufferedImage[] {
            carregarImagem("checkbox_01.png"),
            carregarImagem("checkbox_02.png"),
            carregarImagem("checkbox_03.png"),
            carregarImagem("checkbox_04.png")
        };                
        
        texturaMaca = new BufferedImage[] {
            carregarImagem("maca_01.png"),
            carregarImagem("maca_02.png"),
            carregarImagem("maca_03.png"),
        };              
        
        texturaCobra = new BufferedImage[] {
            carregarImagem("cobra_cabeca_01.png"),
            carregarImagem("cobra_corpo_01.png")
        };
        
        audioClickBotao = carregarAudio("click_botao_01.wav");
        audioGameOver = carregarAudio("gameover_01.wav");
        audioPausar = carregarAudio("pause_01.wav");        
        
        audioColisaoPartes = new AudioClip[] {
            carregarAudio("colisao_partes_01.wav"),
            carregarAudio("colisao_partes_02.wav"),
            carregarAudio("colisao_partes_03.wav"),
            carregarAudio("colisao_partes_04.wav")
        };
        
        audioMordidaMaca = new AudioClip[] {
            carregarAudio("mordida_maca_01.wav"),
            carregarAudio("mordida_maca_02.wav"),
            carregarAudio("mordida_maca_03.wav"),
            carregarAudio("mordida_maca_04.wav")          
        };        
    }
    
    private static BufferedImage carregarImagem(String nome) {
        BufferedImage imagem = null;
        
        try {
            imagem = ImageIO.read(
                    Recursos.class.getResourceAsStream(DIR_IMAGENS + nome));
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro: " + ex.toString());
        }
        
        return imagem;
    }
    
    private static File carregarArquivo(String nomeArquivo) {
        return new File(Recursos.class.getResource(DIR_AUDIO + nomeArquivo).getFile());
    }
    
    private static AudioClip carregarAudio(String nome) {
        return Applet.newAudioClip(
                Recursos.class.getResource(DIR_AUDIO + nome));
    }
    
    public static void executarAudio(AudioClip[] audio) {
        audio[Utilitarios.numeroAleatorio(0, audio.length - 1)].play();
    }

    public static BufferedImage getTexturaIcone() {
        return texturaIcone;
    }   

    public static BufferedImage getTexturaBackgroundJogo() {
        return texturaBackgroundJogo;
    }
    
    public static BufferedImage[] getTexturaMaca() {
        return texturaMaca;
    }

    public static BufferedImage[] getTexturaCobra() {
        return texturaCobra;
    }    

    public static BufferedImage getBackgroundMenuPrincipal() {
        return texturaBackgroundMenu;
    }

    public static BufferedImage[] getTexturaBotoes() {
        return texturaBotoes;
    }

    public static BufferedImage[] getTexturaPaineis() {
        return texturaPaineis;
    }

    public static BufferedImage getTexturaCoracao() {
        return texturaCoracao;
    }

    public static BufferedImage[] getTexturaPontuacao() {
        return texturaPontuacao;
    }

    public static BufferedImage getTexturaGameOver() {
        return texturaGameOver;
    }

    public static BufferedImage getTexturaAvisoGameOver() {
        return texturaAvisoGameOver;
    }

    public static AudioClip getMordidaMaca() {
        return audioMordidaMaca[Utilitarios.numeroAleatorio(0, audioMordidaMaca.length - 1)];
    }

    public static AudioClip getColisaoPartes() {
        return audioColisaoPartes[Utilitarios.numeroAleatorio(0, audioColisaoPartes.length - 1)];
    }
    
    public static AudioClip getAudioClickBotao() {
        return audioClickBotao;
    }    

    public static AudioClip getAudioPausar() {
        return audioPausar;
    }

    public static AudioClip getAudioGameOver() {
        return audioGameOver;
    }

    public static BufferedImage getTexturaPausado() {
        return texturaPausado;
    }
    
    public static Color getCorPrimaria() {
        return paletaCores[0];
    }    
    
    public static Color getCorSecundaria() {
        return paletaCores[1];
    }        
    
    public static Color getCorEscura() {
        return paletaCores[2];
    }        

    public static BufferedImage[] getTexturaBotaoIniciar() {
        return texturaBotaoIniciar;
    }

    public static BufferedImage[] getTexturaBotaoConfiguracoes() {
        return texturaBotaoConfiguracoes;
    }

    public static BufferedImage[] getTexturaBotaoRanking() {
        return texturaBotaoRanking;
    }

    public static BufferedImage[] getTexturaBotaoAjuda() {
        return texturaBotaoAjuda;
    }

    public static BufferedImage[] getTexturaBotaoSobre() {
        return texturaBotaoSobre;
    }

    public static BufferedImage[] getTexturaBotaoSair() {
        return texturaBotaoSair;
    }

    public static BufferedImage getTexturaAbaConfiguracoes() {
        return texturaAbaConfiguracoes;
    }

    public static BufferedImage getTexturaAbaRanking() {
        return texturaAbaRanking;
    }

    public static BufferedImage getTexturaAbaAjuda() {
        return texturaAbaAjuda;
    }

    public static BufferedImage getTexturaAbaSobre() {
        return texturaAbaSobre;
    }

    public static BufferedImage[] getTexturaCheckbox() {
        return texturaCheckbox;
    }

    public static BufferedImage[] getTexturaRadioButton() {
        return texturaRadioButton;
    }

    public static Font getFontePadrao() {
        return fontePadrao;
    }

}
