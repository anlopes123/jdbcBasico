import java.sql.*;

public class ConnectionJDBC {
    public static void main(String[] args) throws ClassNotFoundException {
        String driver = "firebirdsql";
        String databaseAdrees = "localhost/3050";
        String databaseName = "D:\\dioJava\\projetos\\ProjetoBD\\BD\\DIGITALINNOVATION.FDB";
        String user = "SYSDBA";
        String password = "masterkey";

        Class.forName("org.firebirdsql.jdbc.FBDriver");

        StringBuilder sb = new StringBuilder("jdbc")
                .append(":")
                .append(driver)
                .append(':')
                .append(databaseAdrees)
                .append(':')
                .append(databaseName);

        String connectionUrl = sb.toString();

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("SUCESSO ao se conectar no banco FIREBIRD");

            PreparedStatement pstm = conn.prepareStatement("Select * from aluno");

            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                System.out.println("Id : " + String.valueOf(rs.getInt("ID")));
                System.out.println("Nome: " +  rs.getString("NOME"));
                System.out.println("idade: " +  rs.getString("IDADE"));
                System.out.println("Estado: " +  rs.getString("ESTADO"));
            }
         } catch (SQLException e) {
            System.out.println("FALHA ao se conectar ao banco FIREBIRD");
            e.printStackTrace();
        }
    }
}
