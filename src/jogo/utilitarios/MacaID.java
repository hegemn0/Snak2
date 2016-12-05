package jogo.utilitarios;

public enum MacaID {
    VERDE(0), VERMELHA(1), DOURADA(2);
    
    private int numero;
    
    private MacaID(int numero) {
        this.numero = numero;
    }
      
    public int tipo() {
        return numero;
    }
    
    public int getValor() {
        switch(this) {
            case VERDE: {
                return 1;
            } 
            case VERMELHA: {
                return 5;
            }
            case DOURADA: {
                return 10;
            }
            default: {
                return 0;
            }
        }
    }
      
}
