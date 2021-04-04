package com.anverbogatov.text2audio;

import com.anverbogatov.text2audio.commands.CommandHandler;
import com.anverbogatov.text2audio.commands.CommandHandlerProvider;
import com.anverbogatov.text2audio.commands.impl.HelpCommandHandler;
import com.anverbogatov.text2audio.commands.impl.Text2AudioCommandHandler;
import com.anverbogatov.text2audio.files.impl.TxtAndMarkdownFileParserImpl;
import com.anverbogatov.text2audio.generator.impl.AiffAudioFileGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * Main entry point to the tool.
 */
public class Executor {

    /**
     * Set of ALL command handlers registered and available for usage.
     */
    private static final Set<CommandHandler> COMMAND_HANDLERS;

    static {
        COMMAND_HANDLERS = new HashSet<>(2);
        COMMAND_HANDLERS.add(new HelpCommandHandler());
        COMMAND_HANDLERS.add(new Text2AudioCommandHandler(new AiffAudioFileGenerator(), new TxtAndMarkdownFileParserImpl()));
    }

    public static void main(String[] args) {
        try {
            // Step 1 - Validate input
            validateInput(args);

            // Step 2 - Find appropriate command handler
            var handler = new CommandHandlerProvider(COMMAND_HANDLERS).get(args);

            // Step 3 - Do stuff
            handler.orElseThrow(() -> new IllegalStateException("Application could not handle given command.")).handle(args);
        } catch (Exception e) {
            System.err.printf("🚨 %s 🚨\n", e.getMessage());
        }
    }

    /**
     * Validate application's input arguments.
     * <p>
     * Throws {@link IllegalArgumentException} if any of requirements is not satisfied.
     *
     * @param args - application's input arguments
     */
    private static void validateInput(String[] args) {
        // todo: put this method to dedicated validator
        if (args == null) {
            throw new IllegalArgumentException("Input arguments can not be empty. Use '-help' command to see available options.");
        }

        if (args.length == 1 && "-help".equals(args[0])) {
            return;
        }

        if (args.length != 2) {
            throw new IllegalArgumentException("Some arguments are missing. Use '-help' command to see available options.");
        }

        // todo: add dedicated enum with list of supported commands and meta information that can be used by validator
    }
}
