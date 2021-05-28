package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class CountryCSVDAO {
    public List<Country> readCountriesFromCSV(String filename) throws Exception{
        List<Country> countries = new LinkedList<Country>();

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String countriesData = br.readLine();
        while (countriesData != null){
            String[] metadata = countriesData.split(",");
            countries.add(createCountry(metadata));
            countriesData = br.readLine();
        }
        br.close();
        return countries;
    }

    public Country createCountry(String[] metadata){
        double surfaceArea = metadata[4].length() != 0 ? Double.parseDouble(metadata[4]) : 0;
        int population = metadata[3].length() != 0 ? Integer.parseInt(metadata[3]) : 0;
        double gnp = metadata[5].length() != 0 ? Double.parseDouble(metadata[5]) : 0;
        int capital = metadata[6].length() != 0 ? Integer.parseInt(metadata[6]): 0;

        return new Country(metadata[0], metadata[1], metadata[2], surfaceArea, population, gnp, capital);
    }
}