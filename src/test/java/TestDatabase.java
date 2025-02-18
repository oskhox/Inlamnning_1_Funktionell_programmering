import java.util.ArrayList;
import java.util.List;

public class TestDatabase {
    private final List<Movie> testMovies = new ArrayList<>();

    public TestDatabase() {
        //Första filmen
        Movie m1 = new Movie("573a1397f29313caabce62df", "End of the Game", 1975, List.of("Drama", "Mystery", "Crime"), List.of("Robert Shaw"),
                List.of("Jon Voight", "Jacqueline Bisset", "Martin Ritt"), 6.1, List.of("English"), 106);
        testMovies.add(m1);

        //Andra filmen
        Movie m2 = new Movie("573a1397f29313caabce640e", "Zaklete rewiry", 1975, List.of("Drama"),
                List.of("Janusz Majewski"), List.of("Marek Kondrat", "Roman Wilhelmi", "Roman Skamene", "Cestmèr Randa"), 7.4, List.of("Polish"), 94);
        testMovies.add(m2);

        //Tredje filmen
        Movie m3 = new Movie("573a1397f29313caabce6f49", "Train Ride to Hollywood", 1975, List.of("Comedy", "Musical", "Fantasy"),
                List.of("Charles R. Rondeau"), List.of("Willis Draffen Jr.", "Charles Love", "Charles McCormick", "Harry Williams"), 4.3, List.of("English"), 89);
        testMovies.add(m3);

        //Fjärde filmen
        Movie m4 = new Movie("573a1397f29313caabce7cd8", "Rasputin", 1975, List.of("Biography", "Drama", "History"),
                List.of("Elem Klimov"), List.of("Aleksey Petrenko", "Anatoliy Romashin", "Velta Line", "Alisa Freyndlikh"), 7.1, List.of("Russian"), 104);
        testMovies.add(m4);

        //Femte filmen
        Movie m5 = new Movie("573a139af29313caabcf1aed", "Pastorali", 1975, List.of("Drama"), List.of("Otar Iosseliani"),
                List.of("Nana Ioseliani", "Tamar Gabarashvili", "Mikhail Naneishvili", "Nukri Davitashvili"), 7.3, List.of("Georgian"), 95);
        testMovies.add(m5);

        //Sjätte filmen
        Movie m6 = new Movie("573a139bf29313caabcf25df", "Iracema - Uma Transa Amazènica", 1975, List.of("Drama"), List.of("Jorge Bodanzky", "Orlando Senna"),
                List.of("Paulo Cèsar Perèio", "Edna de Cèssia", "Lècio Dos Santos", "Elma Martins"), 7.0, List.of("Portuguese"), 90);
        testMovies.add(m6);

        //Sjunde filmen
        Movie m7 = new Movie("573a139bf29313caabcf43e5", "Pastorali", 1975, List.of("Drama"), List.of("Otar Iosseliani"),
                List.of("Nana Ioseliani", "Tamar Gabarashvili", "Mikhail Naneishvili", "Nukri Davitashvili"), 7.3, List.of("Georgian"), 95);
        testMovies.add(m7);

        //Åttonde filmen
        Movie m8 = new Movie("573a139bf29313caabcf4631", "Kaseki", 1975, List.of("Drama"), List.of("Masaki Kobayashi"),
                List.of("Shin Saburi", "Mayumi Ogawa", "Keiko Kishi", "Komaki Kurihara"), 7.7, List.of("Japanese"), 200);
        testMovies.add(m8);

        //Nionde filmen
        Movie m9 = new Movie("573a139cf29313caabcf7dd6", "Days of Hope", 1975, List.of("Drama"), List.of("null"),
                List.of("Paul Copley", "Pamela Brighton", "Nikolas Simmonds", "Gary Roberts"), 8.2, List.of("English"), 90);
        testMovies.add(m9);

        //Tionde filmen
        Movie m10 = new Movie("573a139ef29313caabcfc154", "Yuppi du", 1975, List.of("Comedy", "Drama"), List.of("Adriano Celentano", "Miky Del Prete"),
                List.of("Claudia Mori", "Charlotte Rampling", "Gino Santercole", "Adriano Celentano"), 6.2, List.of("Italian"), 120);
        testMovies.add(m10);
    }

    public List<Movie> getTestMovies() {
        return testMovies;
    }
}