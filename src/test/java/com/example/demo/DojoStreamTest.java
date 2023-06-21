package com.example.demo;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DojoStreamTest {

    @Test
    void converterData(){
        List<Player> list = CsvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void jugadoresMayoresA35(){

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
    void ElMejorJugador(){

    }

    @Test
    void mejorJugadorSegunNacionalidad(){

    }


}
