import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public List<Aluno> list() {
        List<Aluno> alunos = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection()) {
            String sql = "Select * from aluno";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String nome = rs.getString("NOME");
                Integer idade = rs.getInt("IDADE");
                String estado = rs.getString("ESTADO");

                alunos.add(new Aluno(id, nome, idade, estado));
            }
        } catch (Exception e ) {
            System.out.println("Listagem de aluno FALHOU");
            e.printStackTrace();
        }
        return alunos;

    }

    public Aluno getById(int id) {
        Aluno aluno = new Aluno();

        try(Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM ALUNO WHERE ID = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                aluno.setId(rs.getInt("ID"));
                aluno.setNome(rs.getString("NOME"));
                aluno.setIdade(rs.getInt("IDADE"));
                aluno.setEstado(rs.getString("ESTADO"));

            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Aluno por id FALHOU!!!");
            e.printStackTrace();
        }
        return aluno;
    }

    public void create(Aluno aluno) {
        try(Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO ALUNO(ID, NOME, IDADE, ESTADO) VALUES (?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, aluno.id);
            pstm.setString(2, aluno.nome);
            pstm.setInt(3, aluno.idade);
            pstm.setString(4, aluno.estado);

            int rowAffected = pstm.executeUpdate();

            System.out.println("Insercao com SUCESSO foi adicionado " + rowAffected + "linha");
        } catch (SQLException | ClassNotFoundException e ){
            System.out.println("FALHA na insercao de aluno");
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Aluno aluno = new Aluno();

        try(Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM ALUNO WHERE ID = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            int rowAffected = pstm.executeUpdate();

            System.out.println("Delete bem SUCEDIDO!, Foi deletada " + rowAffected + " linha");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Delete FALHOU!!!");
            e.printStackTrace();
        }
    }

    public void atualizar(Aluno aluno) {

        try(Connection conn = ConnectionFactory.getConnection()) {

            //UPDATE
            //  ALUNO
            //SET
            //  NOME = :NOME,
            //  IDADE = :IDADE,
            //  ESTADO = :ESTADO
            //
            //WHERE
            //  ID = :ID
            //;
            String sql = "UPDATE ALUNO SET NOME = ?, IDADE = ?, ESTADO = ?  WHERE ID = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, aluno.nome);
            pstm.setInt(2, aluno.idade);
            pstm.setString(3, aluno.estado);
            pstm.setInt(4, aluno.id);

            int rowAffected = pstm.executeUpdate();

            System.out.println("alteração bem SUCEDIDA!, Foi alterado " + rowAffected + " linha");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("alteração FALHOU!!!");
            e.printStackTrace();
        }
    }

}
