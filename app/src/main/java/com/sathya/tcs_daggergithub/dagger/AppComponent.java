package com.sathya.tcs_daggergithub.dagger;


// Step 4


import com.sathya.tcs_daggergithub.MainActivity;
import com.sathya.tcs_daggergithub.module.AppModule;
import com.sathya.tcs_daggergithub.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;


/*
    By doing so we are creating a Dagger Graph table!

    Dagger components
Dagger can create a graph of the dependencies in your project that it can use to find
 out where it should get those dependencies when they are needed. To make Dagger do this,
  you need to create an interface and annotate it with @Component. Dagger creates a container
  as you would have done with manual dependency injection.

Inside the @Component interface, you can define functions that return instances of the classes
you need (i.e. UserRepository). @Component tells Dagger to generate a container with all the
dependencies required to satisfy the types it exposes. This is called a Dagger component;
it contains a graph that consists of the objects that Dagger knows how to provide and their
respective dependencies.


 */

@Singleton
@Component( modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);


}
