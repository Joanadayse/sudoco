package br.com.dio;

import br.com.dio.ui.custom.screen.MainScreen;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class UIMain {

    public static void main(String[] args) {
        final var gameConfig = Stream.of(args)
                .map(arg -> arg.split(";"))
                .filter(parts -> parts.length == 2) // só pega os válidos
                .collect(toMap(
                        parts -> parts[0],   // posição (ex: "0,0")
                        parts -> parts[1]    // configuração (ex: "4,false")
                ));
        var mainsScreen = new MainScreen(gameConfig);
        mainsScreen.buildMainScreen();
    }

}