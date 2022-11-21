public class Song {
    private String songName;
    private String artist;
    private String genre;
    private String model = "QUADRANT";
    private double valence;
    private double energy;
    private Quadrants quadrant;

    public Song(String songName, String artist, String genre, double valence, double energy) {
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.valence = valence;
        this.energy = energy;
        determineQuadrant();
    }
    public Song(String songName, String artist) {
        this.songName = songName;
        this.artist = artist;
    }

    private void determineQuadrant(){
        quadrant = Quadrants.getQuadrant(this.genre);
    }

    public double getValence() {
        return valence;
    }

    public double getEnergy() {
        return energy;
    }

    public Quadrants getQuadrant() {
        return quadrant;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", model='" + model + '\'' +
                ", valence=" + valence +
                ", energy=" + energy +
                ", quadrant=" + quadrant +
                '}';
    }
}
