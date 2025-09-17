package program;

import db.DB;
import java.sql.*; //usar o connection, ResultSet...
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        Connection conn = DB.getConnection(); //se conecta com o db(database: banco de dados)

        Statement st = null; //prepara para a consulta
        ResultSet rs = null; //resultado da tabela

        try {
            conn = DB.getConnection(); //se conecta com a classe
            st = conn.createStatement(); //cria uma conecção com o banco
            rs = st.executeQuery("select * from seller"); //executa um comando sql (statment - instrução executada num bd)

            while (rs.next()) {
                System.out.println(rs.getInt("Id") + " " + rs.getString("Name") + "\n" + rs.getString("Email") + "\n");
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
        finally{ //pega os metodos do arquivo DB para fecha-los
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            //DB.closeConnection(); //comenta a conexão para poder fazer uma nova consulta abaixo
        }

        System.out.println("_____________________________________________");

        SimpleDateFormat niversario = new SimpleDateFormat("dd/MM/yyyy"); //instancia o formato da data de aniversario
        PreparedStatement ps = null;

        try{
            ps = conn.prepareStatement(
                    "INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?)", //uma interrogação para cada item (indices)
                    Statement.RETURN_GENERATED_KEYS //gera a chave primaria

            ); //comando sql para inserir dados (uma concatenação)

            ps.setString(1, "Alice"); //()
            ps.setString(2, "alicezinha123@gmail.com");
            ps.setDate(3, new java.sql.Date(niversario.parse("26/10/2004").getTime()));
            ps.setDouble(4, 5000.00);
            ps.setInt(5, 3);

            int rowsAffected = ps.executeUpdate(); //aparecer na tabela do banco de dados

            System.out.println("\nFinalizando!! linhas alternadas " + rowsAffected);

            if(rowsAffected > 0){
                rs = ps.getGeneratedKeys();

                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }

            } else{
                System.out.println("Nenhuma linha foi alterada");
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();

        }
        finally{
            DB.closeStatement(ps);
            DB.closeConnection();
        }

    }

}
