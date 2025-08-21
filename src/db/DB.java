package db;

import java.io.IOException;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null; //define a conexao inicial como nula, não conectada ainda

    public static Connection getConnection() {

        if (conn == null) { //se não estiver conectado, tente...

            try{
                Properties props = loadProperties(); //carregar as propriedades do banco
                String url = props.getProperty("dburl"); //cria uma variavel para a conexão(para não precisar repetir um textao, resumindo em "url")

                conn = DriverManager.getConnection(url, props);

            }
            catch(SQLException e){

                throw new DbException(e.getMessage());

            }
        }
        return conn;
    }

    public static void closeConnection() { //fecha a conexao com o banco, para evitar sobregarregar o sistema
        if (conn != null) {
            try{
                conn.close();

            }
            catch(SQLException e){
                throw new DbException(e.getMessage());

            }
        }
    }

    public static Properties loadProperties() { //"carregar as propriedades"

        try(FileInputStream fs = new FileInputStream("db.properties")){ //lê o arquivo "db.properties" (faz uma tentativa)
            Properties props = new Properties();

            props.load(fs);

            return props;
        }
        catch(IOException e){ //caso a tentativa dê errado, ele emite essa mensagem
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) { //metodos criados para não precisar ficar repetindo (st e rs)

        if (st != null) {
            try{
                st.close();
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try{
                rs.close();
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

}