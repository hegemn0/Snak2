package jogo.main;

public class Resultado {
    private String nome;   
    private int pontuacao;
    private int posicao;
    
    public Resultado() {
        this.nome = "AAA";
        this.pontuacao = 0;
    } 
    
    public Resultado(int posicao, String nome, int pontuacao) {
        this.posicao = posicao;
        this.nome = nome;
        this.pontuacao = pontuacao;
    }  
    
    public String toString() {
        return posicao + "    " + pontuacao + "           " + nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(!nome.isEmpty()) {
            this.nome = nome;
        } else {
            this.nome = "AAA";            
        }
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getPosicao() {
        return posicao;
    }

}
