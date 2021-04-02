package com.anverbogatov.text2audio.commands;

/**
 * Handler of in-app commands.
 *
 * <p>
 * Keep in mind that set of input arguments are treated as commands.
 * </p>
 */
public interface CommandHandler {

    /**
     * Check if specified command can be handled by command handler.
     *
     * @param command - basically it is application's argument unified by it's meaning. For example: `-f ./script/intro.md`
     *                is one command.
     * @return `true` if specified command can be handled by command handler
     */
    boolean isSupported(String[] command);

    /**
     * Handle specified command.
     *
     * @param command - basically it is application's argument unified by it's meaning. For example: `-f ./script/intro.md`
     *                is one command.
     */
    void handle(String[] command);
}
