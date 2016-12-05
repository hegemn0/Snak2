package jogo.utilitarios;

public enum DirecaoID {
    CIMA(0), BAIXO(1), ESQUERDA(2), DIREITA(3);
    
    private int direcao;
    
    DirecaoID(int direcao) {
        this.direcao = direcao;
    }
    
    public DirecaoID getTipo() {
        return this;
    }
    
    public DirecaoID getDirecaoOposta() {
        switch(direcao) {
            case 0: 
                return DirecaoID.BAIXO;
            case 1: 
                return DirecaoID.CIMA;
            case 2: 
                return DirecaoID.DIREITA;
            case 3: 
                return DirecaoID.ESQUERDA;
            default:
                return null;
        }
    }
    
    public boolean opostaDe(DirecaoID direcao) {
        return direcao.equals(getDirecaoOposta());
    }
    
    public boolean diferenteDe(DirecaoID direcao) {
        return !this.equals(direcao);
    } 
}
