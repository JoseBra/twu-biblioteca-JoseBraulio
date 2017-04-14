import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import twu.biblioteca.BibliotecaMain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BibliotecaMainTest {

    @Test
    public void testBibliotecaMainExecution() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        System.setIn(new ByteArrayInputStream("".getBytes()));
        BibliotecaMain.startBibliotecaSystem();

        assertTrue(outputStream.toString().contains("Welcome to the new brand Biblioteca System!"));
    }
}
