package com.anverbogatov.text2audio.files;

import lombok.Data;

@Data
public class AudibleTextPart {

    private final String partName;

    private String partContent;

    public AudibleTextPart(String partName) {
        this(partName, "");
    }

    public AudibleTextPart(String partName, String partContent) {
        this.partName = partName;
        this.partContent = partContent;
    }
}
