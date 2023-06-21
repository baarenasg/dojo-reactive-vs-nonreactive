package com.example.demo;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;

public class DojoReactiveTest {

    @Test
    void converterData(){
        List<Player> list = CsvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void jugadoresMayoresA35() {
        List<Player> list = CsvUtilFile.getPlayers();
        Mono<List<Player>> mono = Mono.just(list);
        Flux<Player> observable = Flux.fromIterable(list);

        observable.filter(jugador -> jugador.getAge() > 35)
                .collectList()
                .map(jugadorList -> {
                    long size = jugadorList.size();
                    Player primer = jugadorList.get(0);
                    Player ultimo = jugadorList.get((int) (size - 1));
                    System.out.println("Size: " + size);
                    System.out.println("Primer jugador: \n"+ primer + "\nSegundo jugador\n" + ultimo);
                    return jugadorList;
                }).subscribe();
    }


@Test
    void jugadoresMayoresA35SegunClub(){
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list);

        observable.filter(player -> player.getAge() > 35)
                .groupBy(Player::getClub)
                .subscribe(group -> {
                    String key = group.key();
                    System.out.println("+++++++++++++++++++");
                    System.out.println(key);
                    group.map(player -> {
                        System.out.println(player);
                        return player;
                    }).subscribe();
                });

    }


    @Test
    void mejorJugadorConNacionalidadFrancia(){

    }

    @Test
    void clubsAgrupadosPorNacionalidad(){

    }

    @Test
    void clubConElMejorJugador(){

    }

    @Test
    void clubConElMejorJugador2() {

    }

    @Test
    void ElMejorJugador() {

    }

    @Test
    void mejorJugadorSegunNacionalidad(){

    }



}
