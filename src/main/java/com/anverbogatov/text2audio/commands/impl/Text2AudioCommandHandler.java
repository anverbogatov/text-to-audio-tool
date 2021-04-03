package com.anverbogatov.text2audio.commands.impl;

import com.anverbogatov.text2audio.commands.CommandHandler;
import com.anverbogatov.text2audio.files.FileParser;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;

import static java.lang.String.format;

@RequiredArgsConstructor
public class Text2AudioCommandHandler implements CommandHandler {

    private final FileParser fileParser;

    @Override
    public boolean isSupported(String[] command) {
        return command.length == 2 && "-f".equals(command[0]) &&
                !command[1].trim().isEmpty();
    }

    @Override
    public void handle(String[] command) throws IOException {
        var path = Paths.get(command[1]);

        validateInput(path);

        var audibleParts = fileParser.parse(path);

        for (int i = 0; i < audibleParts.size(); i++) {
            var part = audibleParts.get(i);
            var sectionName = part.getPartName() != null && !part.getPartName().trim().isEmpty() ?
                    String.format("%d-%s", i, part.getPartName()) :
                    "result";
            var fileName = format("%s.aiff", sectionName).toLowerCase(Locale.ROOT).replaceAll(" ", "-");

            Runtime.getRuntime().exec(format("say -o %s/%s '%s'", path.getParent().toAbsolutePath().toString(), fileName, part.getPartContent()));
        }
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

    @RequiredArgsConstructor
    private enum SupportedFileTypes {
        TXT(".txt"),
        MD(".md");

        private final String fileExtension;
    }
}
