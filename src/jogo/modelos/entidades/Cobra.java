package jogo.modelos.entidades;

import jogo.modelos.efeitos.Pontuacao;
import java.awt.Dimension;
import jogo.main.Jogo;
import jogo.utilitarios.Recursos;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.util.ArrayList;
import jogo.utilitarios.AudioID;
import jogo.utilitarios.DirecaoID;
import jogo.utilitarios.Utilitarios;

public class Cobra extends Objeto {
    private boolean direcaoModificada;
    private ArrayList<ParteCobra> partes;
    private ArrayList<Point> posicoes;
    private ArrayList<Point> posicoesPartes;
    private BufferedImage texturaCobra[];
    private Pontuacao pontuacao;
    
    public Cobra() {       
        this.texturaCobra = Recursos.getTexturaCobra();  
        this.pontuacao = new Pontuacao();
        this.resolucao = new Dimension(texturaCobra[0].getWidth(), texturaCobra[0].getHeight());
        this.partes = new ArrayList<>();                
        this.posicoes = new ArrayList<>();                   
        this.posicoesPartes = new ArrayList<>();
        this.debug = false;
        this.direcao = Utilitarios.gerarDirecao();
        this.direcaoAnterior = direcao;
        this.posicao = Utilitarios.gerarPosicao();
        this.posicaoAnterior = Utilitarios.gerarPosicaoAnterior(posicao, direcao);          
        
    }   

    @Override
    public void atualizar() {         
        moverCabeca();
        moverCorpo();
        atualizarEfeitos();
        atualizarPosicoes();
    }
    
    @Override
    public void renderizar() {
        renderizarCabeca();
        renderizarCorpo();
        renderizarPontuacao();
    }
    
    private void renderizarCabeca() {
        Jogo.renderizar(texturaCobra[0], posicao);        
    }
    
    private void renderizarCorpo() {
        for(int i = 0; i < partes.size(); i++) {
            Jogo.renderizar(texturaCobra[1], partes.get(i).getPosicao());
        }
    }
                        
    private void renderizarPontuacao() {
        if(pontuacao.isAtivo()) {
            pontuacao.renderizar();
        }
    }
    
    private void moverCabeca() {
        direcaoModificada = false;
        posicaoAnterior = new Point(posicao.x, posicao.y);
        switch(getDirecao()) {
            case CIMA: {
                posicao.y -= Jogo.getTamanhoCelula();
            } break;
            case BAIXO: {
                posicao.y += Jogo.getTamanhoCelula();
            } break;
            case ESQUERDA: {
                posicao.x -= Jogo.getTamanhoCelula();
            } break;
            case DIREITA: {
                posicao.x += Jogo.getTamanhoCelula();
            } break;
        }     
    }    
    
    public void setDirecao(DirecaoID novaDirecao) {    
        if(!direcaoModificada && getDirecao().diferenteDe(novaDirecao) & !direcao.opostaDe(novaDirecao)) {
            direcaoAnterior = getDirecao();
            direcaoModificada = true;
            switch(novaDirecao) {
                case CIMA: {
                    direcao = DirecaoID.CIMA;
                } break;
                case BAIXO: {
                    direcao = DirecaoID.BAIXO;
                } break;
                case ESQUERDA: {
                    direcao = DirecaoID.ESQUERDA;
                } break;
                case DIREITA: {
                    direcao = DirecaoID.DIREITA;
                } break;
            }
        }
    }
    
    public String depuramento() {
        return  "Tipo : Cobra" + "\n" +
                "Tempo: " + LocalTime.now() + "\n" +
                "Em movimento: " + isEmMovimento() + "\n" +
                "Direção Atual: " + direcao + "\n" +
                "Direção Anterior: " + direcaoAnterior + "\n" +
                "PosiçãoAnterior  " + "[" + posicao.x + ", " + posicao.y + "]" + "\n" +
                "Posição Anterior: " + "[" + posicaoAnterior.x + ", " + posicaoAnterior.y + "]" + "\n" +
                "Quantidade de Partes: " + partes.size() + "\n";  
    }

    private void moverCorpo() {
        if(partes.size() > 0) {        
            ParteCobra parte = partes.remove(0);
            parte.setPosicao(posicaoAnterior);
            parte.setDirecao(direcaoAnterior);
            partes.add(parte);           
        }
    }

    private void atualizarPosicoes() {
        posicoes = new ArrayList<>();
        posicoesPartes = new ArrayList<>();
        
        posicoes.add(posicao);
        
        if(partes.size() > 0) {
            for(ParteCobra parte : partes) {
                posicoes.add(parte.getPosicao());
                posicoesPartes.add(parte.getPosicao());
            } 
        }
    }    
    
    private void atualizarEfeitos() {
        if(pontuacao.isAtivo()) {
            pontuacao.atualizar();
        }
    }    
    
    public void reset() {
        partes = new ArrayList<>();;
        posicoes = new ArrayList<>();
        posicoesPartes = new ArrayList<>();
        gerarPosicao();
    }
    
    public void colisaoMaca(Maca maca) {
        audioMordida();        
        adicionarParte();
        exibirPontuacao(maca.getPosicao(), maca.getValor());    
        maca.reset();
    }    
    
    public void colisaoParte() {        
        audioColisao();
        reset();
    }
    
    private void audioMordida() {
        Jogo.executarAudio(AudioID.MORDIDA_MACA);
    }    
    
    private void audioColisao() {
        Jogo.executarAudio(AudioID.COLISAO_PARTES);
    }     
    
    public void adicionarParte() {        
        partes.add(0, new ParteCobra(posicaoAnterior, getDirecao()));
    }        
    
    public void adicionarParte(Point posicao, int pontos) {        
        partes.add(0, new ParteCobra(posicaoAnterior, direcaoAnterior));
        exibirPontuacao(posicao, pontos);
    }   
    
    private void gerarPosicao() {               
        direcao = Utilitarios.gerarDirecao();
        direcaoAnterior = direcao;
        posicao = Utilitarios.gerarPosicao();
        posicaoAnterior = Utilitarios.gerarPosicaoAnterior(posicao, direcao);      
    }      
    
    public void exibirPontuacao(Point posicao, int pontos) {
        pontuacao.iniciar(posicao, pontos);
    }
    
    private boolean isEmMovimento() {
        return !posicao.equals(posicaoAnterior);
    }
    
    public int getQtdPartes() {
        return partes.size();
    }

    public boolean getEmMovimento() {
        return isEmMovimento();
    }          
    
    public ArrayList<Point> getPosicoes() {
        return posicoes;
    }

    public ArrayList<Point> getPosicoesPartes() {
        return posicoesPartes;
    }
    
    private class ParteCobra extends Objeto {

        public ParteCobra(Point posicao, DirecaoID direcao) {
            this.posicao = new Point(posicao.x, posicao.y);
            this.direcao = direcao;
        }

        @Override
        public void atualizar() {}

        @Override
        public void renderizar() {}

        public void setDirecao(DirecaoID direcao) {
            this.direcao = direcao;
        }
    }
    
}
