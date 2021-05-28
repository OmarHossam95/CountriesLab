package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class CityCSVDAO {
    public List<City> readCitiesFromCSV(String filename) throws Exception{
        List<City> cities = new LinkedList<City>();

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String citiesData = br.readLine();
        while (citiesData != null){
            String metadata[] = citiesData.split(",");
            cities.add(createCity(metadata));
            citiesData = br.readLine();
        }
        br.close();
        return cities;
    }

    public City createCity(String[] metadata){
        int id = metadata[0].length() != 0 ? Integer.parseInt(metadata[0]) : 0;
        int population = metadata[2].length() != 0 ? Integer.parseInt(metadata[2]) : 0;
        return new City(id, metadata[1], population, metadata[3].substring(1));
    }
}
