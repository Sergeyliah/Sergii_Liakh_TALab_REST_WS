import java.io.IOException;

public class Genre {
    public int genreId;
    public String genreDescription;
    public String genreName;

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public void setGenreDescription(String genreDescription) {
        this.genreDescription = genreDescription;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public static Genre setGenre() throws IOException {
        Genre genre = new Genre();
        genre.setGenreId(Integer.parseInt(PropertyReader.fetchPropertyValue("genreId").toString()));
        genre.setGenreName(PropertyReader.fetchPropertyValue("genreName").toString());
        genre.setGenreDescription(PropertyReader.fetchPropertyValue("genreDescription").toString());
        return genre;
    }
}
