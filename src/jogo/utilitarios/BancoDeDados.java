package jogo.utilitarios;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jogo.main.Resultado;

public class BancoDeDados {

    private static Connection conexao;    
    private static final String NOME_BANCODEDADOS = "ranking.db";
    private static final String DIR_BANCODEDADOS = "bancodedados";
    private static final String LOCAL_BANCODEDADOS = DIR_BANCODEDADOS + "/" + NOME_BANCODEDADOS;
    private static final String CRIAR_TB_RANKING = "CREATE TABLE `tb_ranking` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `nome` TEXT NOT NULL, `pontos` INTEGER NOT NULL );";
    private static final String PREENCHER_TB_RANKING = "INSERT INTO tb_ranking (nome, pontos) VALUES ('BOLSONARO', 2018), ('TRUMP', 2016), ('FARAGE', 2015), ('LEPEN', 100), ('WILDERS', 90), ('PUTIN', 80), ('MEMELORD92', 70), ('DUTERTE', 30), ('MALAKOI', 20), ('PEPE', 10);";
    private static final String SELECIONAR_TB_RANKING = "SELECT * FROM `tb_ranking` ORDER BY `pontos` DESC LIMIT 0, 10;";
    private static final String INSERIR_TB_RANKING = "INSERT INTO `tb_ranking` (`nome`, `pontos`) VALUES(?, ?);";
    private static final String VERIFICAR_TB_RANKING = "SELECT name FROM sqlite_master WHERE type='table' AND name='tb_ranking'";
    
    public static ArrayList<Resultado> select() {
        try {
            conectar();

            Statement instrucaoSQL = conexao.createStatement();
            ResultSet resultado = instrucaoSQL.executeQuery(SELECIONAR_TB_RANKING);
            ArrayList<Resultado> lista = new ArrayList<>();
            
            while (resultado.next()) {
                lista.add(new Resultado(resultado.getRow(), resultado.getString(2), resultado.getInt(3)));
            }

            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.toString());
        } finally {
            finalizarConexao();
        }
        return null;
    }

    public static void insert(Resultado resultado) {
        try {
            conectar();
            PreparedStatement instrucaoSQL = conexao.prepareStatement(INSERIR_TB_RANKING);           

            instrucaoSQL.setString(1, resultado.getNome());
            instrucaoSQL.setInt(2, resultado.getPontuacao());
            instrucaoSQL.execute();            
            
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.toString());
        } finally {
            finalizarConexao();
        }
    }    
    
    private static boolean conectado() {
        if (conexao != null) {
            try {
                if (!conexao.isClosed()) {
                    return true;
                } else if (conexao.isValid(0)) {
                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.toString());
            }
        }
        return false;
    }

    private static void conectar() {
        try {
            if(!diretorioBancoDeDadosCriado()) {
                criarDiretorioBancoDeDados();
            }
            
            if(!conectado()) {
                Class.forName("org.sqlite.JDBC");
                conexao = DriverManager.getConnection("jdbc:sqlite:" + LOCAL_BANCODEDADOS);    
                
                if(!tabelaRankingExiste()) {
                    criarTabelaRanking(); 
                    conectar();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.toString());
        }
    }
    
    private static boolean tabelaRankingExiste() {
        try {
            Statement instrucaoSQL = conexao.createStatement();
            ResultSet resultado = instrucaoSQL.executeQuery(VERIFICAR_TB_RANKING);
            if (!resultado.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.toString());
        }
        return false;
    }

    private static void criarTabelaRanking() {
        try {                 
            Statement instrucaoSQL = conexao.createStatement();
            instrucaoSQL.execute(CRIAR_TB_RANKING);
            instrucaoSQL.execute(PREENCHER_TB_RANKING);
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.toString());
        } finally {
            finalizarConexao();
        }
    }    
    
    private static void finalizarConexao() {
        try {            
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.toString());
        }
    }

    private static boolean diretorioBancoDeDadosCriado() {
        File diretorioBanco = new File(DIR_BANCODEDADOS);
        if(diretorioBanco.exists()) {
            return true;
        } else {
            return false;
        }
    }

    private static void criarDiretorioBancoDeDados() {
        File diretorioBancoDeDados = new File(DIR_BANCODEDADOS);
        diretorioBancoDeDados.mkdir();      
    }
    
}
