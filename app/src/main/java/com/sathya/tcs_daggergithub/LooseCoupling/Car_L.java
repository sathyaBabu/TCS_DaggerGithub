package com.sathya.tcs_daggergithub.LooseCoupling;

public class Car_L {
    // Tight coupling.. Bad practice...

    // Electric, Petrol
    // creating various engines..

    private final Engine engine ;


    // Constructor injection..
    public Car_L(Engine engine) {
        this.engine = engine;
    }

    public void start(){

        engine.start();
    }
}
