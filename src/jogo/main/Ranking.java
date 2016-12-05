package jogo.main;

import java.util.ArrayList;
import jogo.utilitarios.BancoDeDados;

public class Ranking implements Runnable {
    private volatile static boolean executando;
    private volatile static boolean ativo;      
    private static Thread threadRanking; 
    private ArrayList<Resultado> ranking;
    private Resultado recente;
    
    public Ranking() {        
        inicializar();
    }
    
    public synchronized void registrarPontuacao(int pontuacao)  {
        recente.setPontuacao(pontuacao);      
    }
    
    public synchronized void registrarNome(String nome) {
        recente.setNome(nome);
        acordar();
    }
    
    private void inicializar()  {
        executando = true;
        ativo = false;
        recente = new Resultado();
        ranking = new ArrayList<>(10);         
        threadRanking = new Thread(this, "Thread Ranking");
        threadRanking.start();         
        atualizarRanking();        
    }    
    
    private void atualizarRanking() {
        ranking = BancoDeDados.select();
    }
      
    @Override
    public void run() {
        while(executando) {
            if(ativo) {
                inserir(recente);
                atualizarLista();
                ativo = false;
            } else {
                esperar();
            }
        }
    }
    
    private synchronized void acordar() {     
        ativo = true;
        notify();
    }
    
    private synchronized void esperar() {
        try {
            wait();
        } catch (InterruptedException ex) {
            System.out.println("Erro: " + ex.toString());
        }
    }    

    private void inserir(Resultado recente) {
        BancoDeDados.insert(recente);
    }

    private void atualizarLista() {
        ranking = BancoDeDados.select();
    }

    public ArrayList<Resultado> getRanking() {
        return ranking;
    }

}
