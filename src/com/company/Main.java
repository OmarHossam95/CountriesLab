package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            CityCSVDAO cityDAO = new CityCSVDAO();
            List<City> cities = cityDAO.readCitiesFromCSV("Cities.csv");

            CountryCSVDAO countryDAO = new CountryCSVDAO();
            List<Country> countries = countryDAO.readCountriesFromCSV("Countries.csv");

            Object[] countryCodesObject = countries.stream().map(Country::getCode).toArray();
            String[] countryCodes = Arrays.copyOf(countryCodesObject, countryCodesObject.length, String[].class);
            Map<String, List<City>> countryCityMap = CountryCityMapCreator.createCountryCityMap(cities, countryCodes);

            System.out.println("Printing country codes and their corresponding cities ordered by population number: ");
            for (Map.Entry<String, List<City>> entry: countryCityMap.entrySet()){
                System.out.println(entry.getKey() + ": ");
                for (City c: entry.getValue()){
                    System.out.println(c.getName() + ": " + c.getPopulation());
                }
                System.out.println("-----------------------------------------------------------------------");
            }
            System.out.println("-----------------------------------------------------------------------");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Country Code you want to get its cities: ");
            String inputcCode = br.readLine();
            if (countryCityMap.containsKey(inputcCode)){
                for (City c: countryCityMap.get(inputcCode)){
                    System.out.println(c.getName() + ": " + c.getPopulation());
                }
            }
            else{
                System.out.println("Country Code does not exist!");
            }
            br.close();
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //1- Get a List of Countries population
            System.out.println("1- Get a List of Countries population");
            List<Integer> population = Operations.getCountriesPopulation(countries);
            System.out.println(population);
            System.out.println("-----------------------------------------------------------------------");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //2- Get the average countries population
            System.out.println("2- Get the average countries population");
            double avgPopulation = Operations.getAverageCountriesPopulation(countries);
            System.out.println(avgPopulation);
            System.out.println("-----------------------------------------------------------------------");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //3- Get the maximum country population
            System.out.println("3- Get the maximum country population");
            int maxPopulation = Operations.getMaxCountryPopulation(countries);
            System.out.println(maxPopulation);
            System.out.println("-----------------------------------------------------------------------");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //4- Highest population city of each country
            System.out.println("4- Highest population city of each country");
            List<City> maxCities = Operations.getMaxCityPopulation(countryCityMap);
            for (City city: maxCities){
                System.out.println(city.getName() + ", " + city.getPopulation() + ", " + city.getCountryCode());
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //5- Highest population capital
            System.out.println("5- Highest population capital");
            City maxCapital = Operations.getMaxCapital(countries, countryCityMap);
            System.out.println("Capital with highest population: " + maxCapital.getName() + ", " + maxCapital.getPopulation() + ", " + maxCapital.getCountryCode());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
