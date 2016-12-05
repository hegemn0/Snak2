package jogo.utilitarios;

public enum DificuldadeID {
    FACIL(0.30f), MODERADA(0.25f), DIFICIL(0.15f);
    
    private float velocidade;
    
    DificuldadeID(float velocidade) {
        this.velocidade = velocidade;
    }
    
    public float velocidade() {
        return velocidade;
    }
}
