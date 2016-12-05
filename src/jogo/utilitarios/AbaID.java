package jogo.utilitarios;

public enum AbaID {
    CONFIGURACOES(0), RANKING(1), AJUDA(2), SOBRE(3);
    
    private int numero;
    
    private AbaID(int numero) {
        this.numero = numero;
    }
      
    public int tipo() {
        return numero;
    }
}
