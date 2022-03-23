package com.sathya.tcs_daggergithub.DI_L;

import com.sathya.tcs_daggergithub.LooseCoupling.Car_L.*;

public class CarDi {

    // Engine -> Aircraft / Bum / Hel / Car Truck / Scooter / Grinder
    // here some one should give us an engine..
    // The guy who gives us the engine is called as a provider..

    // Engine is not a part of our Project..



    private Engine engine = ServiceLocator.getInstance().getEngine();

    public  void  start() { engine.start();}
}
