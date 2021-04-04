package com.anverbogatov.text2audio.generator.impl;

import com.anverbogatov.text2audio.files.AudibleTextPart;
import com.anverbogatov.text2audio.generator.AudioFileGenerator;

import java.io.IOException;
import java.nio.file.Path;

import static java.lang.String.format;

public class AiffAudioFileGenerator implements AudioFileGenerator {

    @Override
    public void generate(Path path, AudibleTextPart textPart, String fileName) throws IOException {
        Runtime.getRuntime().exec(format("say -o %s/%s '%s'", path.getParent().toAbsolutePath().toString(), fileName, textPart.getPartContent()));
    }
}
