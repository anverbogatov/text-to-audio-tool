package com.anverbogatov.text2audio.commands.impl;

import com.anverbogatov.text2audio.commands.CommandHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Paths;

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

        var content = getFileContent(path);

        Runtime.getRuntime().exec(String.format("say -o %s/%s '%s'", path.getParent().toAbsolutePath().toString(), "result.aiff", content));
    }

    private String getFileContent(java.nio.file.Path path) throws IOException {
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
}
