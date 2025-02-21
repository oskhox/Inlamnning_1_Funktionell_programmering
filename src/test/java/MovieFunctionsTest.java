import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MovieFunctionsTest {

    final public TestMovieDB tdb = new TestMovieDB();
    final List<Movie> testMovies = tdb.getTestMovies();
    final MovieFunctions mFunc = new MovieFunctions();

    @Test
    public void numberOfMoviesTest() {
        assertEquals(10, mFunc.allMovies(testMovies));
    }

    @Test
    public void longestMovieTest() {
        assertEquals(200, mFunc.longestMovie(testMovies));
    }

    @Test
    public void uniqueGenresTest() {
        assertEquals(8, mFunc.uniqueGenres(testMovies));
    }

    @Test
    public void highestRankedCastTest() {
        final List<String> expected = List.of("Gary Roberts", "Nikolas Simmonds", "Pamela Brighton", "Paul Copley");
        assertEquals(expected, mFunc.highestRankedCast(testMovies));
    }

    @Test
    public void movieFewestActorsTest() {
        assertEquals("End of the Game", mFunc.movieFewestActors(testMovies));
    }

    @Test
    public void actorsMultipleMoviesTest() {
        assertEquals(1, mFunc.actorsMultipleMovies(testMovies));
    }

    @Test
    public void actorMostMovies() {
        assertEquals("Jon Voight", mFunc.actorMostMovies(testMovies));
    }

    @Test
    public void uniqueLanguagesTest() {
        assertEquals(7, mFunc.uniqueLanguages(testMovies));
    }

    @Test
    public void sameTitleTest() {
        assertTrue(mFunc.sameTitle(testMovies));
    }

    @Test
    public void HOFtest(){
    final LongestMovieInterface longestM = (m) -> m.getRuntime();
    assertEquals(200, mFunc.longestMovieHOF(testMovies, longestM));
    }
}