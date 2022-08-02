package io.vepo.sintaxe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.vepo.java101.sintaxe.Area;
import io.vepo.java101.sintaxe.Media;

@DisplayName("Sintaxe")
class SintaxeExerciciosTest {

    @Test
    @DisplayName("Área do Circulo")
    void areaDoCirculoTest() {
        Assertions.assertEquals(12.5664, Area.circulo(2.00), 0.001);
        Assertions.assertEquals(31819.3371, Area.circulo(100.64), 0.001);
        Assertions.assertEquals(70685.8347, Area.circulo(150.00), 0.001);
    }

    @Test
    @DisplayName("Média")
    void calculoMediaTest() {
        Assertions.assertEquals(10.0, Media.artimetica(new int[] { 10, 20, 0 }), 0.01);
        Assertions.assertEquals(2.5, Media.artimetica(new int[] { 5, 0 }), 0.01);
        Assertions.assertEquals(50.14, Media.artimetica(new int[] {
                31, 21, 18, 35, 85, 100, 68, 30, 44, 40, 5, 77, 48, 77, 70, 81, 60, 30, 52, 1, 31, 79, 22, 24, 46, 64,
                49, 86, 14, 99, 46, 95, 9, 3, 73, 50, 65, 70, 74, 75, 36, 100, 12, 85, 27, 84, 56, 26, 72, 13, 72, 90,
                50, 24, 35, 85, 64, 9, 63, 63, 42, 35, 20, 66, 7, 52, 24, 75, 24, 94, 74, 2, 90, 30, 61, 5, 81, 14, 77,
                11, 82, 11, 50, 84, 35, 30, 56, 83, 30, 35, 68, 59, 41, 43, 33, 83, 74, 38, 65, 17 }), 0.01);
    }

    @Test
    @DisplayName("Mediana")
    void calculoMedianaTest() {
        Assertions.assertEquals(10, Media.mediana(new int[] { 10, 20, 0 }));
        Assertions.assertEquals(2, Media.mediana(new int[] { 5, 0 }));
        Assertions.assertEquals(50, Media.mediana(new int[] {
                31, 21, 18, 35, 85, 100, 68, 30, 44, 40, 5, 77, 48, 77, 70, 81, 60, 30, 52, 1, 31, 79, 22, 24, 46, 64,
                49, 86, 14, 99, 46, 95, 9, 3, 73, 50, 65, 70, 74, 75, 36, 100, 12, 85, 27, 84, 56, 26, 72, 13, 72, 90,
                50, 24, 35, 85, 64, 9, 63, 63, 42, 35, 20, 66, 7, 52, 24, 75, 24, 94, 74, 2, 90, 30, 61, 5, 81, 14, 77,
                11, 82, 11, 50, 84, 35, 30, 56, 83, 30, 35, 68, 59, 41, 43, 33, 83, 74, 38, 65, 17 }));
    }
}
