package com.anverbogatov.text2audio.commands;

import com.anverbogatov.text2audio.commands.impl.Text2AudioCommandHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

@DisplayName("Command Handler Provider")
class CommandHandlerProviderTest {

    private CommandHandlerProvider provider;

    @BeforeEach
    void setUp() {
        provider = new CommandHandlerProvider(new HashSet<>() {{
            add(new Text2AudioCommandHandler(null, null));
        }});
    }

    @Test
    @DisplayName("should return empty Optional if handler not registered")
    void testEmpty() {
        var result = provider.get(new String[]{"-help"});
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("should return handler if registered")
    void testReturnsHandler() {
        var result = provider.get(new String[]{"-f", "/tmp/file.txt"});
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
    }
}