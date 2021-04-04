package com.anverbogatov.text2audio.files.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

@DisplayName("Textual File Parser")
class TxtAndMarkdownFileParserImplTest {

    private static final String TESTS_FILES_ROOT_DIR = "/tmp";

    private static final String TESTS_TEXT_FILE_NAME = "input.txt";

    private static final String FORMATTED_TEXT_FOR_FILE = "### Introduction\n" +
            "Hello dear comrades! Let's talk about Soviet Russia today.\n" +
            "\n" +
            "### What is Soviet Russia?\n" +
            "Soviet Russia is the greatest country in the World.\n" +
            "\n" +
            "### Outro\n" +
            "Let the Great Bear be with you my dear comrade!";

    private TxtAndMarkdownFileParserImpl parser;

    private static void createFileWithData() throws IOException {
        var f = new File(TESTS_FILES_ROOT_DIR + "/" + TESTS_TEXT_FILE_NAME);
        Assertions.assertTrue(f.createNewFile());
        try (var fw = new FileWriter(f)) {
            fw.write(FORMATTED_TEXT_FOR_FILE);
        }
    }

    private static void cleanUpTestsFilesDir() {
        var f = new File(TESTS_FILES_ROOT_DIR + "/" + TESTS_TEXT_FILE_NAME);
        if (f.exists()) {
            Assertions.assertTrue(f.delete());
        }
    }

    @BeforeEach
    void setUp() throws IOException {
        parser = new TxtAndMarkdownFileParserImpl();
        createFileWithData();
    }

    @AfterEach
    void tearDown() {
        cleanUpTestsFilesDir();
    }

    @Test
    @DisplayName("should parse separate audible parts from file")
    void shouldParseFile() throws IOException {
        var result = parser.parse(Paths.get(TESTS_FILES_ROOT_DIR + "/" + TESTS_TEXT_FILE_NAME));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());

        Assertions.assertEquals("Introduction", result.get(0).getPartName());
        Assertions.assertEquals("Hello dear comrades! Let's talk about Soviet Russia today.", result.get(0).getPartContent());

        Assertions.assertEquals("What is Soviet Russia?", result.get(1).getPartName());
        Assertions.assertEquals("Soviet Russia is the greatest country in the World.", result.get(1).getPartContent());

        Assertions.assertEquals("Outro", result.get(2).getPartName());
        Assertions.assertEquals("Let the Great Bear be with you my dear comrade!", result.get(2).getPartContent());
    }
}