package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountryCityMapCreator {
    public static Map<String, List<City>> createCountryCityMap(List<City> cities, String[] countryCodes){
        Map<String, List<City>> countryCityMap = new HashMap<String, List<City>>();
        for (String code: countryCodes){
            List<City> countryCities = cities.stream().filter(c -> c.getCountryCode().equals(code)).sorted(Comparator.comparing(c -> c.getPopulation())).collect(Collectors.toList());
            Collections.reverse(countryCities);
            countryCityMap.put(code, countryCities);
        }
        return countryCityMap;
    }
}
