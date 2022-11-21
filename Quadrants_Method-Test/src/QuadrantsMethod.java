import java.util.ArrayList;

public class QuadrantsMethod {


    private final User user;

    public QuadrantsMethod() {
        user = new User();
    }

    public void updateUserPreferences(ArrayList<Song> songs) {
        for (Song song : songs) {
            if (user.getValencePreference() > song.getValence())
                user.setValencePreference(user.getValencePreference() - song.getValence() * 0.2 * 1);
            else
                user.setValencePreference(user.getValencePreference() + song.getValence() * 0.2 * 1);

            if (user.getEnergyPreference() > song.getEnergy())
                user.setEnergyPreference(user.getEnergyPreference() - song.getEnergy() * 0.2 * 1);
            else user.setEnergyPreference(user.getEnergyPreference() + song.getEnergy() * 0.2 * 1);

            double verPos = 0;
            double hosPos = 0;

            switch (song.getQuadrant()) {
                case Energetic_Rhythmic -> {
                    verPos = user.getVerticalPosition() - 0.2 * 1;
                    hosPos = user.getHorizontalPosition() - 0.2 * 1;
                }
                case Upbeat_Conventional -> {
                    verPos = user.getVerticalPosition() + 0.2 * 1;
                    hosPos = user.getHorizontalPosition() - 0.2 * 1;
                }
                case Reflective_complex -> {
                    verPos = user.getVerticalPosition() + 0.2 * 1;
                    hosPos = user.getHorizontalPosition() + 0.2 * 1;

                }
                case Intense_Rebellious -> {
                    verPos = user.getVerticalPosition() - 0.2 * 1;
                    hosPos = user.getHorizontalPosition() + 0.2 * 1;
                }
                default -> System.out.println("Incorrect Genre");
            }

            getNewPosition(notZero(verPos, user.getVerticalPosition()), notZero(hosPos, user.getHorizontalPosition()), song.getQuadrant());
        }
    }

    private double notZero(double newPos, double oldPos) {
        if (newPos == 0) {
            return newPos + oldPos / 100;
        }
        return newPos;
    }

    public User getUser() {
        return user;
    }

    private void getNewPosition(double verPos, double hosPos, Quadrants songQuadrant) {
        Quadrants newQuadrant = Quadrants.getQuadrant(verPos, hosPos);
        Quadrants currentQuadrant = user.getCurrentQuadrant();

        if (newQuadrant == songQuadrant || newQuadrant == currentQuadrant) {
            user.setPosition(verPos, hosPos);
        } else {
            if (currentQuadrant == Quadrants.Intense_Rebellious && newQuadrant == Quadrants.Reflective_complex) {
                user.setPosition(-0.001, hosPos);
            } else if (currentQuadrant == Quadrants.Intense_Rebellious && newQuadrant == Quadrants.Energetic_Rhythmic) {
                user.setPosition(-0.001, hosPos);
            } else if (currentQuadrant == Quadrants.Energetic_Rhythmic && newQuadrant == Quadrants.Intense_Rebellious) {
                user.setPosition(verPos, -0.001);
            } else if (currentQuadrant == Quadrants.Energetic_Rhythmic && newQuadrant == Quadrants.Upbeat_Conventional) {
                user.setPosition(-0.001, hosPos);
            } else if (currentQuadrant == Quadrants.Upbeat_Conventional && newQuadrant == Quadrants.Energetic_Rhythmic) {
                user.setPosition(0.001, hosPos);
            } else if (currentQuadrant == Quadrants.Upbeat_Conventional && newQuadrant == Quadrants.Reflective_complex) {
                user.setPosition(verPos, -0.001);
            } else if (currentQuadrant == Quadrants.Reflective_complex && newQuadrant == Quadrants.Upbeat_Conventional) {
                user.setPosition(verPos, 0.001);
            } else {
                user.setPosition(0.001, hosPos);
            }
        }
    }
}
