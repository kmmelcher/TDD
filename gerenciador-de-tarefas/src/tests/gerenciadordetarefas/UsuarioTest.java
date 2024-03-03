package gerenciadordetarefas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void criaUsuarioTest(){
        Usuario usuario = new Usuario("Kilian", "kilian.melcher@ccc.ufcg.edu.br");

        assertEquals("Kilian", usuario.getNome());
        assertEquals("kilian.melcher@ccc.ufcg.edu.br", usuario.getEmail());
    }

}
