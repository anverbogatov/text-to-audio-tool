package com.anverbogatov.text2audio.files;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Defines contract for different files parsers.
 */
public interface FileParser {

    /**
     * Parse given file to ordered list of {@link AudibleTextPart} instances.
     *
     * @param filePath - path descriptor that points to a textual file with textual script
     * @return ordered list of audible parts. Order of elements matches with their order in the file
     */
    List<AudibleTextPart> parse(Path filePath) throws IOException;
}
