package io.vepo.sintaxe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.vepo.java101.sintaxe.Area;

@DisplayName("Sintaxe")
class SintaxeExerciciosTest {

    @Test
    @DisplayName("Área do Circulo")
    void areaDoCirculoTest() {
        Assertions.assertEquals(12.5664, Area.circulo(2.00), 0.001);
        Assertions.assertEquals(31819.3371, Area.circulo(100.64), 0.001);
        Assertions.assertEquals(70685.8347, Area.circulo(150.00), 0.001);
    }
}
