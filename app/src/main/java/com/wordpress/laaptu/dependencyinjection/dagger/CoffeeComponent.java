package com.wordpress.laaptu.dependencyinjection.dagger;

import com.wordpress.laaptu.dependencyinjection.hotels.HotelB;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by laaptu on 4/28/17.
 */
@Singleton
@Component(modules = {CoffeeProvider.class})
public interface CoffeeComponent {
    void provideCoffee(HotelB hotelB);
}