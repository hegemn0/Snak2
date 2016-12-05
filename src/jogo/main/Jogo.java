package jogo.main;

import jogo.utilitarios.Controle;
import jogo.estados.Estado;
import jogo.estados.Carregamento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import jogo.utilitarios.AudioID;
import jogo.utilitarios.DificuldadeID;

@SuppressWarnings("serial")
public class Jogo extends JPanel implements Runnable {
    
    private static final int TAMANHO_CELULA = 32;    
    private static final int LARGURA_BASE = 800;
    private static final int ALTURA_BASE = 640;
    
    private static int tamanhoCelula = TAMANHO_CELULA; 
    private static int largura = LARGURA_BASE;
    private static int altura = ALTURA_BASE;
    private static float deltaTempo;
    private static volatile boolean executando;    

    private static Thread threadJogo;
    private static Audio audio;
    private static Configuracoes configuracoes;
    private static Ranking ranking;
    
    private static Graphics2D tela;
    private static Image imagem;       
        
    private static Controle controle;
    private static volatile Estado estado;
    
    private JFrame frame;
    
    public Jogo() {
        audio = new Audio();
        configuracoes = new Configuracoes();
        ranking = new Ranking();
        inicializarGUI();
    }
          
    @Override
    public void run() {
        long duracaoAtualizacao = 0;
        long duracaoRepouso = 0;
                
        while(executando) {            
            long antesAtualizacao = System.nanoTime();
            deltaTempo = (duracaoAtualizacao + duracaoRepouso) / 1000f; // millisegundo
            
            atualizarERenderizar();
            
            duracaoAtualizacao = (System.nanoTime() - antesAtualizacao) / 1000000L; //microsegundo
            duracaoRepouso = Math.max(2, 17 - duracaoAtualizacao);
            
            try {
                Thread.sleep(duracaoRepouso);     
            } catch (InterruptedException ex) {
                System.out.println("Erro: " + ex.toString());
            }
        }        
        System.exit(0);
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        inicializarControle();
        setEstado(new Carregamento());
        inicializarJogo();
    }

    public static void finalizar() {
        executando = false;
    }    
    
    public static void setEstado(Estado novoEstado) {
        System.gc();        
        novoEstado.inicializar();
        estado = novoEstado;
        controle.setEstado(estado);
    }     
    
    public static void executarAudio(AudioID id) {
        audio.registrarEvento(id);
    }
    
    public static void registrarPontuacao(int pontuacao) {
        ranking.registrarPontuacao(pontuacao);
    }
    
    public static void registrarNome(String nome) {
        ranking.registrarNome(nome);
    }    

    public static void renderizar(BufferedImage imagem, Point posicao) {
        tela.drawImage(imagem, posicao.x, posicao.y, null);
    } 
    
    public static void renderizar(BufferedImage imagem, int x, int y) {
        tela.drawImage(imagem, x, y, null);
    } 

    public static void renderizar(BufferedImage imagem, Point posicao, Dimension resolucao) {
        tela.drawImage(imagem, posicao.x, posicao.y, resolucao.width, resolucao.height, null);
    }
    
    public static void renderizar(BufferedImage imagem, int x, int y, int largura, int altura) {
        tela.drawImage(imagem, x, y, largura, altura, null);
    }
    
    public static void renderizarRetangulo(Color cor, Point posicao, Dimension resolucao) {
        Color corAnterior = tela.getColor();
        tela.setColor(cor);
        tela.fillRect(posicao.x, posicao.y, resolucao.width, resolucao.height);
        tela.setColor(corAnterior);
    }
    
    public static void renderizarRetangulo(Color cor, int x, int y, int largura, int altura) {
        Color corAnterior = tela.getColor();
        tela.setColor(cor);
        tela.fillRect(x, y, largura, altura);
        tela.setColor(corAnterior);
    }

    public static void renderizarString(Color cor, Font fonte, String s, boolean exibirSombra, Point posicao) {
        Color corAnterior = tela.getColor();        
        tela.setFont(fonte);
        if(exibirSombra) {
            tela.setColor(Color.BLACK);
            tela.drawString(s, posicao.x+2, posicao.y+2);
        }
        tela.setColor(cor);
        tela.drawString(s, posicao.x, posicao.y);
        tela.setColor(corAnterior);
    }
    
    public static void renderizarString(Color cor, Font fonte, String s, boolean exibirSombra, int x, int y) {
        Color corAnterior = tela.getColor();        
        tela.setFont(fonte);
        if(exibirSombra) {
            tela.setColor(Color.BLACK);
            tela.drawString(s, x+2, y+2);
        }
        tela.setColor(cor);
        tela.drawString(s, x, y);
        tela.setColor(corAnterior);
    }
    
    public static void renderizarString(Color cor, Font fonte, String s, boolean exibirSombra) {
        Color corAnterior = tela.getColor();        
        tela.setFont(fonte);
        FontMetrics medidasFonte = tela.getFontMetrics();
        int x = (largura - medidasFonte.stringWidth(s))/2;
        int y = (medidasFonte.getAscent() + (altura - (medidasFonte.getAscent() + medidasFonte.getDescent()))/2);        
        if(exibirSombra) {
            tela.setColor(Color.BLACK);
            tela.drawString(s, x+2, y+2);            
        }        
        tela.setColor(cor);
        tela.drawString(s, x, y);
        tela.setColor(corAnterior);
    }    
    
    public static float getDeltaTempo() {
        return deltaTempo;
    }

    public static Configuracoes getConfiguracoes() {
        return configuracoes;
    }  
    
    public static DificuldadeID getDificuldade() {
        return configuracoes.getDificuldade();
    } 

    public static float getVelocidade() {
        return configuracoes.getDificuldade().velocidade();
    }

    public static int getTamanhoCelula() {
        return tamanhoCelula;
    }       
    
    public static int getLargura() {
        return largura;
    }
    
    public static int getAltura() {
        return altura;
    }

    public static int getLarguraBase() {
        return LARGURA_BASE;
    }
    
    public static int getAlturaBase() {
        return ALTURA_BASE;
    } 
    
    public static Graphics2D getTela() {
        return tela;
    }    
    
    public static void setFonte(Font fonte) {
        tela.setFont(fonte);
    }
    
    public static Font getFonte() {
        return tela.getFont();
    }
    
    public static FontMetrics getMedidasFonte() {
        return tela.getFontMetrics();
    }
    
    public static ArrayList<Resultado> getRanking() {
        return ranking.getRanking();
    }    
    
    private void inicializarControle() {
        controle = new Controle();
        addKeyListener(controle);
        addMouseListener(controle);
        addMouseMotionListener(controle);
    }    
    
    private void inicializarJogo() {
        executando = true;
        threadJogo = new Thread(this, "Thread Jogo");
        threadJogo.start();
    }
    
    private void inicializarGUI() {          
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(largura, altura));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus(); 
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);      
        frame.add(this);
        frame.pack();
        frame.setVisible(true);        
    }      
    
    private void atualizarERenderizar() {
        estado.atualizar();
        prepararImagem();
        estado.renderizar();
        renderizarImagem();        
    }
        
    private void prepararImagem() {
        if(imagem == null) {
            imagem = createImage(largura, altura);
        }        
        tela = (Graphics2D)imagem.getGraphics();
        getTela().fillRect(0, 0, largura, altura);        
    }
    
    private void renderizarImagem() {
        if(imagem != null) {
            getGraphics().drawImage(imagem, 0, 0, null);
        }
        getGraphics().dispose();        
    }    
    
}
