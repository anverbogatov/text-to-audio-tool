package com.anverbogatov.text2audio.commands.impl;

import com.anverbogatov.text2audio.commands.CommandHandler;

/**
 * Handles `-help` command.
 */
public class HelpCommandHandler implements CommandHandler {
    @Override
    public boolean isSupported(String[] command) {
        return command.length == 1 && "-help".equals(command[0]);
    }

    @Override
    public void handle(String[] command) {
        System.out.println("🚀 text-to-audio-tool is simple console Java utility that creates audio files from " +
                "specified as application's input markdown files. Pay attention that structure of markdown files you use " +
                "must follow rules described in: https://github.com/anverbogatov/text-to-audio-tool/blob/main/README.md");
        System.out.println();
        System.out.println("⚠️ List of supported arguments");
        System.out.println("\t\t-f\t[path to file]\t\tSpecifies input text file. Example: `-f ./input/script.md`");
    }
}
