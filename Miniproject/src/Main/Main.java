package Main;

import City.City;
import org.w3c.dom.ls.LSOutput;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static CityUtils.CityUtils.parse;

public class Main {
    public static void main(String[] args) {

        //System.out.println("______________________________");
        List<City> cities=parse();

        sortByNameV2(cities); // Сортировка массива по наименованию городов, используя java.util.Comparator
        printSort(cities);

        System.out.println("______________________________");
        sortByDistrictAndName(cities);
        printSort(cities);

        System.out.println("______________________________");
        findMaxPopulation(cities);

        System.out.println("______________________________");

        findCountCity(cities);


    }


    /**
     * Сортировка массива городов по наименованию в алфавитном порядке по убыванию
     * без учета регистра
     *
     * @param cities массив городов
     */

    private static void sortByNameV2(List<City> cities) {
        cities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }


    /**
     * Сортировка массива городов по федеральному округу и наименованию города внутри каждого федерального округа
     * в алфавитном порядке по убыванию с учетом регистра
     *
     * @param cities массив городов
     */
    private static void sortByDistrictAndName(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    public static void printSort(List<City> cities) {

        cities.forEach(System.out::println);
    }


    /**
     * Поиск города с наибольшим количеством жителей в нем,используя lambda-выражения
     * @param cities
     */
    private static void findMaxPopulation(List<City> cities) {
        System.out.println(cities.stream().max(Comparator.comparing(City::getPopulation)));
    }


    private static void findCountCity(List<City> cities) {
       Map<String,Integer> region=new HashMap<>();
       for(City e:cities) {
           if(!region.containsKey(e.getRegion())) {
               region.put(e.getRegion(),1);
           }
           else {
               region.put(e.getRegion(),region.get(e.getRegion())+1);
           }
       }

        for (String key : region.keySet()) {
            System.out.println(MessageFormat.format(" {0} = {1}", key, region.get(key)));
        }

    }
}
