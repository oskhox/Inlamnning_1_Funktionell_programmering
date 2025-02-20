import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MovieAppTest {

    final public TestMovieDB tdb = new TestMovieDB();
    final List<Movie> testMovies = tdb.getTestMovies();
    final MovieApp app = new MovieApp();

    @Test
    public void numberOfMoviesTest() {
        assertEquals(10, app.allMovies(testMovies));
    }

    @Test
    public void longestMovieTest() {
        assertEquals(200, app.longestMovie(testMovies));
    }

    @Test
    public void uniqueGenresTest() {
        assertEquals(8, app.uniqueGenres(testMovies));
    }

    @Test
    public void highestRankedCastTest() {
        final List<String> expected = List.of("Gary Roberts", "Nikolas Simmonds", "Pamela Brighton", "Paul Copley");
        assertEquals(expected, app.highestRankedCast(testMovies));
    }

    @Test
    public void movieFewestActorsTest() {
        assertEquals("End of the Game", app.movieFewestActors(testMovies));
    }

    @Test
    public void actorsMultipleMoviesTest() {
        assertEquals(1, app.actorsMultipleMovies(testMovies));
    }

    @Test
    public void actorMostMovies() {
        assertEquals("Jon Voight", app.actorMostMovies(testMovies));
    }

    @Test
    public void uniqueLanguagesTest() {
        assertEquals(7, app.uniqueLanguages(testMovies));
    }

    @Test
    public void sameTitleTest() {
        assertTrue(app.sameTitle(testMovies));
    }

    @Test
    public void HOFtest(){
    LongestMovieInterface longestM = (m) -> m.getRuntime();
    assertEquals(200, app.longestMovieHOF(testMovies, longestM));
    }
}