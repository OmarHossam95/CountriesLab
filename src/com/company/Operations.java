package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Operations {
    public static List<Integer> getCountriesPopulation(List<Country> countries){
        return countries.stream().map(Country::getPopulation).collect(Collectors.toList());
    }

    public static double getAverageCountriesPopulation(List<Country> countries){
        return countries.stream().flatMapToInt(c -> IntStream.of(c.getPopulation())).average().getAsDouble();
    }
    public static int getMaxCountryPopulation(List<Country> countries){
        return countries.stream().flatMapToInt(c -> IntStream.of(c.getPopulation())).max().getAsInt();
    }

    public static List<City> getMaxCityPopulation(Map<String, List<City>> countryCityMap){
        List<City> highestPopulation = new LinkedList<City>();
        for (Map.Entry<String, List<City>> entry: countryCityMap.entrySet()){
            if (entry.getValue().size() != 0) {
                City maxCity = entry.getValue().stream().max(Comparator.comparingInt(c -> c.getPopulation())).get();
                highestPopulation.add(maxCity);
            }
        }
        return highestPopulation;
    }

    public static City getMaxCapital(List<Country> countries, Map<String, List<City>> countryCityMap){
        List<City> capitals = new LinkedList<>();
        for (Country country: countries){
            List<City> cities = countryCityMap.get(country.getCode());
            if (cities.size() != 0){
                City city = cities.stream().filter(c -> c.getId() == country.getCapital()).findFirst().get();
                capitals.add(city);
            }
        }
        return capitals.stream().max(Comparator.comparingInt(c -> c.getPopulation())).get();
    }
}

