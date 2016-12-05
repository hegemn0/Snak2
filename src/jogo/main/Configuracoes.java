package jogo.main;

import jogo.utilitarios.DificuldadeID;

public class Configuracoes {
    private final static DificuldadeID DIFICULDADE_PADRAO = DificuldadeID.MODERADA;
    private final static boolean EXIBIR_GRADE_PADRAO = false;
    
    private static DificuldadeID dificuldade = DIFICULDADE_PADRAO;   
    private static boolean exibirGrade = EXIBIR_GRADE_PADRAO;

    public static DificuldadeID getDificuldade() {
        return dificuldade;
    }

    public static void setDificuldade(DificuldadeID dificuldade) {
        Configuracoes.dificuldade = dificuldade;
    }

    public static boolean isExibirGrade() {
        return exibirGrade;
    }

    public static void setExibirGrade(boolean exibirGrade) {
        Configuracoes.exibirGrade = exibirGrade;
    }
}
