package program;

import db.DB;
import java.sql.*; //usar o connection, ResultSet...

public class Program {

    public static void main(String[] args) {

        Connection conn = DB.getConnection(); //se conecta com o db(database: banco de dados)

        Statement st = null; //prepara para a consulta
        ResultSet rs = null; //resultado da tabela

        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from seller");

            while (rs.next()) {
                System.out.println(rs.getInt("Id" + " " + rs.getString("Name") + "\n" + rs.getString("Email") + "\n"));
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
        finally{
            DB.closeResultSet(rs);//njjnjnijnij
            DB.closeStatement(st);
            DB.closeConnection(); //rjejww
        }

    } //oi t

}
