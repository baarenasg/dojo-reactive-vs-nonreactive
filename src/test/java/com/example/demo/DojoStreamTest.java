package com.example.demo;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DojoStreamTest {

    @Test
    void converterData(){
        List<Player> list = CsvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void jugadoresMayoresA35(){
        List<Player> list = CsvUtilFile.getPlayers();
        Set<Player> result = list.stream()
                .filter(jugador -> jugador.getAge() > 35)
                .collect(Collectors.toSet());
        result.forEach(System.out::println);
    }

    @Test
    void jugadoresMayoresA35SegunClub(){
        List<Player> list = CsvUtilFile.getPlayers();
        Map<String, List<Player>> result = list.stream()
                .filter(player -> player.getAge() > 35)
                .distinct()
                .collect(Collectors.groupingBy(Player::getClub));

        result.forEach((key, jugadores) -> {
            System.out.println("\n");
            System.out.println(key + ": ");
            jugadores.forEach(System.out::println);
        });

    }

    @Test
    void mejorJugadorConNacionalidadFrancia(){

        Predicate<Player> nacionalidadFrancia = player -> player.getNational().equals("France");
        Optional<Player> result = CsvUtilFile.getPlayers().stream()
                .filter(nacionalidadFrancia)
                .reduce((p1, p2) -> p1.getWinners() > p2.getWinners()?p1:p2);
        result.ifPresent(System.out::println);
    }


    @Test
    void clubsAgrupadosPorNacionalidad(){
        Map<String, Set<String>> result = CsvUtilFile.getPlayers().stream()
                .collect(Collectors.groupingBy(
                        Player::getNational,
                        HashMap::new,
                        Collectors.mapping(
                                Player::getClub,
                                Collectors.toSet()
                        )
                ));
        result.forEach((key, value) -> {
            System.out.println(key + ": \n");
            System.out.println(value);
            System.out.println("\n");
        });
    }

    @Test
    void clubConElMejorJugador(){
        Optional<Player> result = CsvUtilFile.getPlayers().stream()
                .reduce(((p1, p2) -> p1.getWinners() > p2.getWinners()?p1:p2));
        String club = result.get().getClub();
        System.out.println("Club con el mejor jugador:");
        System.out.println(club);
    }

    @Test
    void ElMejorJugador(){
        Optional<Player> result = CsvUtilFile.getPlayers().stream()
                .reduce(((p1, p2) -> p1.getWinners() > p2.getWinners()?p1:p2));
        String jugador = result.get().getName();
        System.out.println("Mejor jugador:");
        System.out.println(jugador);
    }

    @Test
    void mejorJugadorSegunNacionalidad(){
        Map<String, Optional<Player>> result = CsvUtilFile.getPlayers().stream()
                .collect(Collectors.groupingBy(
                        Player::getNational,
                        Collectors.reducing((p1, p2) -> p1.getWinners() > p2.getWinners()?p1:p2)
                ));
        result.forEach((key, value) -> {
            System.out.println(key + ": \n");
            value.ifPresent(System.out::println);
            System.out.println("\n");
        });
    }


}
