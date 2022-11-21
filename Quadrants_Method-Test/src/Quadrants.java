import org.w3c.dom.ranges.Range;

import java.util.stream.IntStream;

public enum Quadrants {
    Upbeat_Conventional, Reflective_complex, Energetic_Rhythmic, Intense_Rebellious;

    public static Quadrants getQuadrant(double y, double x) {
        if (x > 0 & y > 0) return Quadrants.Reflective_complex; //top right quadrant
        else if (x > 0 & y < 0) return Quadrants.Intense_Rebellious; //bottom right quadrant
        else if (x < 0 & y > 0) return Quadrants.Upbeat_Conventional; //top left quadrant
        else return Quadrants.Energetic_Rhythmic; //bottom left quadrant
    }

    public static String[] getSubGenre(Quadrants quadrants) {
        if (quadrants == Quadrants.Reflective_complex) {
            return new String[]{"classical", "jazz", "blue", "folk", "mellow", "cappella"};
        } else if (quadrants == Quadrants.Intense_Rebellious) {
            return new String[]{"alternative", "rock", "metal", "heavy"};
        } else if (quadrants == Quadrants.Upbeat_Conventional) {
            return new String[]{"country", "pop", "religious", "sound tracks", "forro"};
        } else {
            return new String[]{"rap", "hip hop", "trap", "dance", "soul", "funk", "electronic", "phonk", "edm", "house", "drill", "bass"};
        }
    }

    public static Quadrants getQuadrant(String genre) {
        if (checkGenres(new String[]{"classical", "jazz", "blue", "folk", "mellow", "cappella"}, genre)) {
            return Quadrants.Reflective_complex;
        } else if (checkGenres(new String[]{"alternative", "rock", "metal", "heavy"}, genre)) {
            return Quadrants.Intense_Rebellious;
        } else if (checkGenres(new String[]{"country", "pop", "religious", "sound tracks", "forro"}, genre)) {
            return Quadrants.Upbeat_Conventional;
        } else {
            return Quadrants.Energetic_Rhythmic;
        }
    }

    private static boolean checkGenres(String[] Genres, String songGenre) {
        for (String genre : Genres) {
            if (songGenre.contains(genre)) return true;
        }
        return false;
    }
}
