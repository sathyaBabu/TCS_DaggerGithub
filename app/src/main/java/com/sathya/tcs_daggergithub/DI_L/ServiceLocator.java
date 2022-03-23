package com.sathya.tcs_daggergithub.DI_L;


// provider.
// SingleTon  can also be a factory to get any sort of engine..

public class ServiceLocator {

    private  static  ServiceLocator instance = null;

    private  ServiceLocator() {}


    public  static  ServiceLocator getInstance() {
        if( instance == null ){
            synchronized ( ServiceLocator.class) {
                instance = new ServiceLocator();
            }

        }
        return  instance ;


    }

    public Engine getEngine() { return  new Engine(); }



}
