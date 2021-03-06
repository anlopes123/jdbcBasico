import java.util.List;

public class QueriesExecution {
    public static void main(String[] args) {
         AlunoDAO alunoDAO= new AlunoDAO();
         List<Aluno> alunos = alunoDAO.list();
         alunos.stream().forEach(System.out::println);
         Aluno aluno = alunoDAO.getById(3);

         aluno.setNome("Jose Santos");
         aluno.setEstado("PR");
         aluno.setIdade(35);
         alunoDAO.atualizar(aluno);

         alunoDAO.list().stream().forEach(System.out::println);
    }
}
