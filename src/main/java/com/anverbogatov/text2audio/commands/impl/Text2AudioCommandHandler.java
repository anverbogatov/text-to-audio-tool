package com.anverbogatov.text2audio.commands.impl;

import com.anverbogatov.text2audio.commands.CommandHandler;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;

import static java.lang.String.format;

public class Text2AudioCommandHandler implements CommandHandler {

    /**
     * Max size of buffer which is used for reading data from files.
     */
    private static final int MAX_BUFFER_SIZE = 1024;

    @Override
    public boolean isSupported(String[] command) {
        return command.length == 2 && "-f".equals(command[0]) &&
                !command[1].trim().isEmpty();
    }

    @Override
    public void handle(String[] command) throws IOException {
        var path = Paths.get(command[1]);

        validateInput(path);

        var content = getFileContent(path);

        Runtime.getRuntime().exec(format("say -o %s/%s '%s'", path.getParent().toAbsolutePath().toString(), "result.aiff", content));
    }

    private void validateInput(Path path) {
        var file = path.toFile();
        if (!file.isFile()) {
            throw new IllegalArgumentException(format("`%s` is not a file. Please provide textual file instead.", path.toAbsolutePath()));
        }
        Arrays.stream(SupportedFileTypes.values()).filter(type -> file.getName().toLowerCase(Locale.ROOT).endsWith(type.fileExtension))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        format("File `%s` is not supported. Check `-help` for the list of supported files.", file.getName())));
    }

    private String getFileContent(Path path) throws IOException {
        try (var reader = new RandomAccessFile(path.toFile(), "r")) {
            var channel = reader.getChannel();
            var byteOut = new ByteArrayOutputStream();

            var bufferSize = channel.size() < MAX_BUFFER_SIZE ? (int) channel.size() : MAX_BUFFER_SIZE;
            var buffer = ByteBuffer.allocate(bufferSize);

            while (channel.read(buffer) > 0) {
                byteOut.write(buffer.array(), 0, buffer.position());
                buffer.clear();
            }

            return byteOut.toString();
        }
    }

    @RequiredArgsConstructor
    private enum SupportedFileTypes {
        TXT(".txt"),
        MD(".md");

        private final String fileExtension;
    }
}
