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

    }


@Test
    void jugadoresMayoresA35SegunClub(){

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
