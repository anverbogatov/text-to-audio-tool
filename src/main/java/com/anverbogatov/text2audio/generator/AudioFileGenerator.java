package com.anverbogatov.text2audio.generator;

import com.anverbogatov.text2audio.files.AudibleTextPart;

import java.io.IOException;
import java.nio.file.Path;

public interface AudioFileGenerator {
    /**
     * Generate audio file from specified text part.
     *
     * @param path     - path in which result file must be saved
     * @param textPart - audible text part that must be transformed to audio
     * @param fileName - name of the result file
     */
    void generate(Path path, AudibleTextPart textPart, String fileName) throws IOException;
}
