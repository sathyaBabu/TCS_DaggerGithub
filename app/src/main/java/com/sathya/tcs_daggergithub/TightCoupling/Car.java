package com.sathya.tcs_daggergithub.TightCoupling;

public class Car {
    // Tight coupling.. Bad practice...

    // Electric, Petrol
    // creating various engines..

    private  Engine engine = new Engine();

    public void start(){

        engine.start();
    }
}
