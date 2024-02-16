package Login;

import modelo.Usuario;

/**
 *
 * @author aluno
 */
public class DadosDeSessao {
    
    public static String nomeSistema = "CCSOFTWARE";
    
    private static Usuario usuario = null;

    
    
    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        DadosDeSessao.usuario = usuario;
    }
    
    
    
}
