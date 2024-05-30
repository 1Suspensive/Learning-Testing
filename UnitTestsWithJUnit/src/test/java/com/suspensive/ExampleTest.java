package com.suspensive;

//Es buena practica poner está importacion como static

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class ExampleTest {

    //En las pruebas unitarias siempre se tiene un valor esperado y un valor actual.
    //POR DEFECTO TODOS LOS TEST UNITARIOS SON VALIDOS.


    //Para comparar el valor esperado con el actual se usan los assertions (Esperado,Actual)


    /*Tipos de assertions*/
    //assertEquals -> Compara un valor esperado con un valor actual
    //assertTrue -> Usado para test donde se requiere un valor esperado de tipo booleano
    //assertFalse -> Usado para test donde se requiere un valor esperado de tipo booleano
    //assertNotNull -> Usado para validar que el valor esperado no sea nulo
    //assertInstanceOf(Class.class,result) -> Valida que el resultado sea una instancia de la clase del valor esperado
    //assertThrows -> Valida excepciones

    //LOS THREADS NO SE TESTEAN


    /*Estructura a seguir para los metodos de las pruebas unitarias*/
    //LA ESTRUCTURA CAMBIA CUANDO SE TESTEAN ERRORES
    /*public void test() {
       GIVEN -> ¿Que necesito para ejecutar el metodo?
       Example example = new Example();
       int numberA = 1;
       int numberB = 2

       WHEN -> Se ejecuta el metodo
       int result = example.sumar(numberA,numberB);

       Then -> Se usan los assertions
    }*/

    //Las ramas(Brances) en los test unitarios hacen referencia a los caminos que puede tomar el metodo al ejecutarse, por ejemplo
    //Saltar una excepcion o devolver un true
    //Los bucles no son branches


    //Para visualizar el resultado de jacoco abrimos en la barra lateral maven -> lifecycle -> test, al hacer doble click en la carpeta target -> site -> index.html tenemos el reporte de cobertura
    //Amarillo -> Parcialmente cubierto, Verde -> cubierto, Rojo -> no cubierto

    //Codigo que optimiza las importaciones de los objetos para los tests
    private Example example;

    @BeforeEach
    public void init(){
        this.example = new Example();
    }

    @Test
    public void testSumar() {
        int numberA = 2;
        int numberB = 3;

        int result = example.sumar(numberA, numberB);

        assertEquals(5, result);
        assertInstanceOf(Integer.class, result);
    }

    @Test
    public void testCheckPositivo() {
        int number = 2;

        boolean result = example.checkPositivo(number);

        assertTrue(result);
    }

    @Test
    public void testCheckPositivoError(){
        int number = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            example.checkPositivo(number);
        });
    }

    @Test
    public void testContarLetrasA(){
        String word = "amazing";

        int result = example.contarLetrasA(word);

        assertEquals(2, result);
    }

    @Test
    public void testContieneElemento(){
        List<String> names = List.of("Jeferson","Santiago","Angela");
        String name = "Jeferson";

        boolean result = example.contieneElemento(names,name);

        assertTrue(result);
    }

    @Test
    public void testRevertirCadena(){
        String text = "car";

        String result = example.revertirCadena(text);

        assertEquals("rac", result);
    }

    @Test
    public void testFactorial(){
        int number = 4;

        long result = example.factorial(number);

        assertEquals(24,result);
    }

    @Test
    public void testFactorialError(){
        int number = -1;

        assertThrows(IllegalArgumentException.class,()->{
            example.factorial(number);
        });
    }

    @Test
    public void testEsPrimo(){
        int number = 3;

        boolean result = example.esPrimo(number);

        assertTrue(result);
    }

    @Test
    public void testEsPrimoLessOrEqualsThanOne(){
        int number = -1;

        boolean result = example.esPrimo(number);

        assertFalse(result);
    }

    @Test
    public void testNoEsPrimo(){
        int number = 9;

        boolean result = example.esPrimo(number);

        assertFalse(result);
    }

    @Test
    public void testMensajeConRetraso() throws InterruptedException {
        String result = this.example.mensajeConRetraso();

        assertEquals("Listo después de retraso",result);
    }

    @Test
    public void testConvertirAString(){
        List<Integer> numbers = List.of(1,2,3,4,5);

        List<String> result = example.convertirAString(numbers);

        assertEquals(List.of("1","2","3","4","5"),result);
    }

    @Test
    public void testCalcularMedia(){
        List<Integer> numbers = List.of(1,2,3,4);

        double result = example.calcularMedia(numbers);

        assertEquals(2.5,result);
    }

    @Test
    public void testCalcularMediaNull(){
        List<Integer> numbers = null;

        assertThrows(IllegalArgumentException.class,()->{
            example.calcularMedia(numbers);
        });
    }

    @Test
    public void testCalcularMediaEmpty(){
        List<Integer> numbers = Collections.emptyList();

        assertThrows(IllegalArgumentException.class,()->{
            example.calcularMedia(numbers);
        });
    }

    @Test
    public void testConvertirListaAString(){
        List<String> list = List.of("h","o","l","a");

        String result = example.convertirListaAString(list);

        assertEquals("H,O,L,A",result);
    }

}
