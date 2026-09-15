package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void main_prints_welcome_message_and_loop_values() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));

        try {
            Main.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        String output = outputStream.toString(StandardCharsets.UTF_8);
        String[] lines = output.split(System.lineSeparator());

        assertEquals("Hello and welcome!", lines[0].trim());
        assertEquals("i = 1", lines[1].trim());
        assertEquals("i = 2", lines[2].trim());
        assertEquals("i = 3", lines[3].trim());
        assertEquals("i = 4", lines[4].trim());
        assertEquals("i = 5", lines[5].trim());
    }
}
