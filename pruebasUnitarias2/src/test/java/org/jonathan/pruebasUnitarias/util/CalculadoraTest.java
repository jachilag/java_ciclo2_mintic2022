package org.jonathan.pruebasUnitarias.util;


import org.junit.Test;

import junit.framework.TestCase;

public class CalculadoraTest extends TestCase{
    private Calculadora calculadora = new Calculadora();
    
    @Test
    public void testSumar(){
        assertTrue("paso la prueba", calculadora.suma(3, 3)==(3+3));
    }

    @Test
    public void testRestar() {
        assertFalse("paso la prueba", calculadora.resta(5, 2)!=3);
    }

    @Test
    public void testMultiplicacion() {
        
    }

    @Test
    public void testResta() {
        
    }

    @Test
    public void testSuma() {
        
    }

    @Test
    public void testMultiplicacion2() {
        
    }

    @Test
    public void testResta2() {
        
    }

    @Test
    public void testSuma2() {
        
    }

    
   
}