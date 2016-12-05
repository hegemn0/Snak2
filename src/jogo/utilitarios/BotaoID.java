package jogo.utilitarios;

public enum BotaoID {
    INICIAR(0), CONFIGURACOES(2), RANKING(4), AJUDA(6), SOBRE(8), SAIR(10), TRANSPARENTE(12);
    
    private int numero;
    
    private BotaoID(int numero) {
        this.numero = numero;
    }
      
    public int tipo() {
        return numero;
    }
      
    public int pressionado() {
        return numero+1;
    }
    
   public int selecionado() {
        return 12;
    }
      
}
