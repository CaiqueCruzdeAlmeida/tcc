
package testes;

import controlador.AlunoDao;
import modelo.Aluno;


public class UsuarioBuscar {
       
    
    public static void main(String[] args) throws Exception {
        AlunoDao dao = new AlunoDao();
        Aluno u = dao.getAluno(1);
        
        System.out.println("Nome: "+u.getNome());
        //System.out.println("Email: "+u.getEmail());
    }
}
