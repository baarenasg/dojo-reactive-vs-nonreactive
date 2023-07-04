package com.example.demo;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

public class DojoReactiveTest {

    @Test
    void converterData(){
        List<Player> list = CsvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void jugadoresMayoresA35() {
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list);

        observable.filter(jugador -> jugador.getAge() > 35)
                .subscribe(System.out::println);
    }


    @Test
    void jugadoresMayoresA35SegunClub(){
        List<Player> readCsv = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(readCsv);

        observable.filter(player -> player.getAge() > 35)
                .distinct()
                .groupBy(Player::getClub)
                .flatMap(groupedFlux -> groupedFlux
                        .collectList()
                        .map(list -> {
                            Map<String, List<Player>> map = new HashMap<>();
                            map.put(groupedFlux.key(), list);
                            return map;
                        }))
                .subscribe(map -> {
                    map.forEach((key, value) -> {
                        System.out.println("\n");
                        System.out.println(key + ": ");
                        value.forEach(System.out::println);
                    });
                });

    }


    @Test
    void mejorJugadorConNacionalidadFrancia(){
        System.out.println("Mejor jugador Frances:");
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> fluxPlayers = Flux.fromIterable(list);
        Mono<Player> result = fluxPlayers.filter(player -> player.getNational().equals("France"))
                .reduce((p1, p2) -> p1.getWinners() > p2.getWinners()?p1:p2);
        result.subscribe(System.out::println);
    }

    @Test
    void clubsAgrupadosPorNacionalidad(){
        List<Player> csv = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(csv);

        observable
                .groupBy(Player::getNational)
                .flatMap(groupedFlux -> groupedFlux
                        .collectList()
                        .map(list->{
                            List<String> clubs =new ArrayList<>();
                            list.forEach(element -> clubs.add(element.getClub()));
                            Map<String, List<String>> map =new HashMap<>();
                            map.put(groupedFlux.key(),clubs);
                            return   map;
                        })).subscribe(map-> {
                            map.forEach((key,value)->{
                                System.out.println("\n");
                                System.out.println(key + ": ");
                                value.forEach(System.out::println);

                            });
                });
    }

    @Test
    void clubConElMejorJugador(){
        System.out.println("Club con el mejor jugador:");
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> fluxPlayers = Flux.fromIterable(list);
        Mono<Player> result = fluxPlayers.reduce((p1, p2) -> p1.getWinners() > p2.getWinners()?p1:p2);
        result.subscribe(player -> System.out.println(player.getClub()));
    }



    @Test
    void ElMejorJugador() {
        System.out.println("El mejor jugador:");
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> fluxPlayers = Flux.fromIterable(list);
        Mono<Player> result = fluxPlayers.reduce((p1, p2) -> p1.getWinners() > p2.getWinners()?p1:p2);
        result.subscribe(player -> System.out.println(player.getName()));
    }

    @Test
    void mejorJugadorSegunNacionalidad(){
        List<Player> csv = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(csv);

        observable
                .groupBy(Player::getNational)
                .flatMap(groupedFlux -> groupedFlux
                        .collectList()
                        .map(list->{
                            Player best = list.stream().reduce((p1,p2)->((p1.getWinners()/p1.getGames())>=(p2.getWinners()/p2.getGames()))?p1:p2).get();
                            Map<String, Player> map =new HashMap<>();
                            map.put(groupedFlux.key(),best);
                            return   map;
                        })).subscribe(map-> {
                    map.forEach((key,value)->{
                        System.out.println("\n");
                        System.out.println(key + ": ");
                        System.out.println(value);

                    });
                });
    }
}
