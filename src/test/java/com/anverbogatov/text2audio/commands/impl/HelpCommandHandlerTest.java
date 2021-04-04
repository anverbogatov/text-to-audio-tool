package com.anverbogatov.text2audio.commands.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Help Command Handler")
class HelpCommandHandlerTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private HelpCommandHandler handler;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        handler = new HelpCommandHandler();
    }

    @Test
    @DisplayName("should support only one specific command")
    void isSupported() {
        Assertions.assertTrue(handler.isSupported(new String[]{"-help"}));

        Assertions.assertFalse(handler.isSupported(new String[]{"help"}));
        Assertions.assertFalse(handler.isSupported(new String[]{"-", "help"}));
        Assertions.assertFalse(handler.isSupported(new String[]{"-help", "-f"}));
        Assertions.assertFalse(handler.isSupported(new String[]{"-f"}));
        Assertions.assertFalse(handler.isSupported(new String[]{"-f", "/tmp/file.txt"}));
    }

    @Test
    @DisplayName("should output help message specific for help command")
    void handle() {
        handler.handle(new String[]{"-help"});

        String output = outputStream.toString();
        Assertions.assertNotNull(output);
        Assertions.assertFalse(output.isEmpty());
        Assertions.assertEquals("\uD83D\uDE80 text-to-audio-tool is simple console Java utility that creates audio files from specified as application's input markdown files. Pay attention that structure of markdown files you use must follow rules described in: https://github.com/anverbogatov/text-to-audio-tool/blob/main/README.md\n" +
                "\n" +
                "⚠️ List of supported arguments\n" +
                "\t\t-f\t[path to file]\t\tSpecifies input text file. Only files with `*.txt` and `*.md` extensions are supported. Example: `-f ./input/script.md`\n", output);
    }
}