package com.anverbogatov.text2audio.commands.impl;

import com.anverbogatov.text2audio.commands.CommandHandler;
import com.anverbogatov.text2audio.files.AudibleTextPart;
import com.anverbogatov.text2audio.files.FileParser;
import com.anverbogatov.text2audio.generator.AudioFileGenerator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.file.Path;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Text to Audio Command Handler")
class Text2AudioCommandHandlerTest {

    private static final List<AudibleTextPart> PREDEFINED_TEST_AUDIBLE_PARTS = singletonList(
            new AudibleTextPart("Introduction", "Hello world!"));

    @Mock
    private AudioFileGenerator audioFileGenerator;

    @Mock
    private FileParser fileParser;

    private CommandHandler handler;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        handler = new Text2AudioCommandHandler(audioFileGenerator, fileParser);
        when(fileParser.parse(any(Path.class))).thenReturn(PREDEFINED_TEST_AUDIBLE_PARTS);
    }

    @Test
    @DisplayName("should support only one specific command")
    void isSupported() {
        Assertions.assertFalse(handler.isSupported(new String[]{""}));
        Assertions.assertFalse(handler.isSupported(new String[]{"-help"}));
        Assertions.assertFalse(handler.isSupported(new String[]{"-f"}));
        Assertions.assertFalse(handler.isSupported(new String[]{"/tmp/file.md", "-f"}));

        Assertions.assertTrue(handler.isSupported(new String[]{"-f", "/tmp/file.md"}));
    }

    @Test
    @DisplayName("should generate aiff file with specific file name")
    void handle() throws Exception {
        handler.handle(new String[]{"-f", "./README.md"});
        var captor = ArgumentCaptor.forClass(String.class);
        verify(audioFileGenerator, times(1)).generate(any(), any(), captor.capture());
        Assertions.assertNotNull(captor.getValue());
        Assertions.assertEquals("0-introduction.aiff", captor.getValue());
    }

    @Test
    @DisplayName("should fail on not supported file format")
    void failsOnNotSupported() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> handler.handle(new String[]{"-f", ".git/config"}));
    }
}