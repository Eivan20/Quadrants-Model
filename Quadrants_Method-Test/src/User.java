public class User {
    private double verticalPosition;
    private double horizontalPosition;
    private double energyPreference;
    private double valencePreference;

    private Quadrants currentQuadrant;

    public User() {
        this.verticalPosition = 0;
        this.horizontalPosition = 0;
        this.energyPreference = 0.5;
        this.valencePreference = 0.5;
    }

    public void setPosition(double verticalPosition, double horizontalPosition) {
        if (horizontalPosition > 1) this.horizontalPosition = 1;
        else if (horizontalPosition < -1) this.horizontalPosition = -1;
        else this.horizontalPosition = round(horizontalPosition);

        if (verticalPosition > 1) this.verticalPosition = 1;
        else if (verticalPosition < -1) this.verticalPosition = -1;
        else this.verticalPosition = round(verticalPosition);

        updateCurrentQuadrant();
    }

    private double round(double number) {
        return Math.round(number * 1000.0) / 1000.0;
    }

    public double getHorizontalPosition() {
        return this.horizontalPosition;
    }

    public double getVerticalPosition() {
        return this.verticalPosition;
    }

    public double getEnergyPreference() {
        return this.energyPreference;
    }

    public double getValencePreference() {
        return this.valencePreference;
    }

    public void setEnergyPreference(double energyPreference) {
        if (energyPreference > 1) this.energyPreference = 1.0;
        else if (energyPreference < 0) this.energyPreference = 0.0;
        else this.energyPreference = round(energyPreference);
    }

    public void setValencePreference(double valencePreference) {
        if (valencePreference > 1) this.valencePreference = 1.0;
        else if (valencePreference < 0) this.valencePreference = 0.0;
        else this.valencePreference = round(valencePreference);
    }

    private void updateCurrentQuadrant() {
        this.currentQuadrant = Quadrants.getQuadrant(this.verticalPosition, this.horizontalPosition);
    }

    public Quadrants getCurrentQuadrant() {
        Quadrants temp = currentQuadrant;
        return temp;
    }

    @Override
    public String toString() {
        return "User{" +
                "verticalPosition=" + verticalPosition +
                ", horizontalPosition=" + horizontalPosition +
                ", energyPreference=" + energyPreference +
                ", valencePreference=" + valencePreference +
                ", currentQuadrant=" + currentQuadrant +
                '}';
    }
}
