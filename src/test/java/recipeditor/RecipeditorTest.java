package recipeditor;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class RecipeditorTest {
    @Test
    public void sampleTest() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("/exit".getBytes());
        System.setIn(in);
        String[] a = {"a","b"};
        Recipeditor.main(a);
        Assertions.assertTrue(true);
    }
}
