package jogo.modelos.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import jogo.main.Jogo;
import jogo.modelos.entidades.Objeto;
import jogo.utilitarios.Recursos;

public class CaixaDeTexto extends Objeto {

    private final String UNDERSCORE = "_";
    private final int TEMPO_ANIMACAO = 30; 
    private int contador = TEMPO_ANIMACAO;  
    private boolean selecionado;
    private boolean animarUnderscore;
    private StringBuilder nome;
    private FontMetrics medidasFonte;
    private Font fonte;
    private Color textoSelecionado;
    private Color textoNormal;
    
    public CaixaDeTexto() {
        this.selecionado = true;
        this.animarUnderscore = false;
        this.nome = new StringBuilder("AAAA");
        this.fonte = new Font("Sans", Font.BOLD, 50);
        Jogo.setFonte(fonte);
        this.medidasFonte = Jogo.getMedidasFonte();
        this.textoNormal = Recursos.getCorSecundaria();
        this.textoSelecionado = Recursos.getCorPrimaria();
        this.posicao = new Point((Jogo.getLargura() - medidasFonte.stringWidth(nome.toString()))/2, (medidasFonte.getAscent() + (Jogo.getAltura() - (medidasFonte.getAscent() + medidasFonte.getDescent()))/2));
    }
    
    @Override
    public void atualizar() {               
        if(contador >= TEMPO_ANIMACAO) {        
            animarUnderscore = false;
        } else if(contador <= 0) {
            animarUnderscore = true;
        }
        
        if(animarUnderscore) {
            contador++;
        } else {
            contador--;
        }
        
       atualizarPosicao();
    }

    private void atualizarPosicao() {
        posicao.setLocation(new Point((Jogo.getLargura() - medidasFonte.stringWidth(nome.toString()))/2, (medidasFonte.getAscent() + (Jogo.getAltura() - (medidasFonte.getAscent() + medidasFonte.getDescent()))/2)));
    }
    
    @Override
    public void renderizar() {
        if(animarUnderscore & selecionado) {
            Jogo.renderizarString(textoSelecionado, fonte, UNDERSCORE, true, posicao.x + medidasFonte.stringWidth(nome.toString()), posicao.y);
        }
        
        if(selecionado) {
            Jogo.renderizarString(textoSelecionado, fonte, nome.toString(), true, posicao.x, posicao.y);        
        } else {
            Jogo.renderizarString(textoNormal, fonte, nome.toString(), true, posicao.x, posicao.y);        
        }
    }
    
    public void inserir(char caractere) {
        if(selecionado & Character.isLetterOrDigit(caractere) & nome.length() < 10) {
            nome.append(caractere);
        }
    }
    
    public void apagar() {
        if(selecionado & nome.length() > 0) {
            nome.deleteCharAt(nome.length()-1);
        }
    }
    
    public void confirmar() {
        selecionado = false;
    }    
    
    public void selecionar() {
        this.selecionado = !selecionado;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public String getNome() {
        return nome.toString();
    }
}
