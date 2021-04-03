package com.anverbogatov.text2audio.files.impl;

import com.anverbogatov.text2audio.files.AudibleTextPart;
import com.anverbogatov.text2audio.files.FileParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class TxtAndMarkdownFileParserImpl implements FileParser {

    @Override
    public List<AudibleTextPart> parse(Path filePath) throws IOException {
        var result = new LinkedList<AudibleTextPart>();

        try (var in = new BufferedReader(new FileReader(filePath.toFile()))) {
            while (in.ready()) {
                String line = in.readLine();
                if (line.startsWith("### ")) {
                    result.add(new AudibleTextPart(line.substring(4)));
                } else {
                    var currentPart = result.getLast();
                    currentPart.setPartContent(currentPart.getPartContent() + line);
                }
            }
        }
        return result;
    }
}
