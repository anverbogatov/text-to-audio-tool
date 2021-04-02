package com.anverbogatov.text2audio.commands;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class CommandHandlerProvider {

    private final Set<CommandHandler> handlers;

    public Optional<CommandHandler> get(String[] command) {
        return handlers.stream()
                .filter(handler -> handler.isSupported(command))
                .findAny();
    }
}
