import java.util.ArrayList;

public class QuadrantsMain {
    public static void main(String[] args) {
        QuadrantsMethod quadrantsMethod = new QuadrantsMethod();
        quadrantsMethod.getUser().setPosition(-1, 1);
        quadrantsMethod.getUser().setEnergyPreference(0.90);
        quadrantsMethod.getUser().setValencePreference(0.24);

        DBConnection r = new DBConnection();
        quadrantsMethod.updateUserPreferences(r.listener());
        System.out.println(quadrantsMethod.getUser());
        r.insertIntoRecommend(r.findSong(quadrantsMethod.getUser()));
    }
}