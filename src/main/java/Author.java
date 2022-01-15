import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.IOException;

public class Author {
    public int authorId;
    public String authorDescription;
    public String nationality;
    public AuthorName authorName;
    public Birth birth;

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAuthorName(AuthorName authorName) {
        this.authorName = authorName;
    }

    public void setBirth(Birth birth) {
        this.birth = birth;
    }

    public static class AuthorName {
        public String first;
        public String second;

        public void setFirst(String first) {
            this.first = first;
        }

        public void setSecond(String second) {
            this.second = second;
        }
    }

    public static class Birth {
        public String city;
        public String country;
        public String date;

        public void setCity(String city) {
            this.city = city;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
    private final static String NEWAUTHOR = "{\n" +
            "\"authorId\": 1,\n" +
            "\"authorName\": {\n" +
            "\"first\": \"Taras\",\n" +
            "\"second\": \"Shevchenko\"\n" +
            "},\n" +
            "\"nationality\": \"ukrainian\",\n" +
            "\"birth\":{\n" +
            "\"date\": \"1814-03-09\",\n" +
            "\"country\": \"Ukraine\",\n" +
            "\"city\": \"Morintsi\"\n" +
            "},\n" +
            "\"authorDescription\": \"Famous ukrainian writer\"\n" +
            "}\n";

    public static JSONObject setAuthorJsonObject() throws ParseException {
        JSONObject json = (JSONObject)new JSONParser().parse(NEWAUTHOR);
        System.out.println("authorId = " + json.get("authorId"));
        System.out.println("authorName = " + json.get("authorName"));
        return json;
    }
    public static Author setAutor() throws IOException {
        Author author = new Author();
        author.setAuthorId(Integer.parseInt(PropertyReader.fetchPropertyValue ("authorId").toString()));
        author.setAuthorDescription(PropertyReader.fetchPropertyValue ("authorDescription").toString());
        author.setNationality(PropertyReader.fetchPropertyValue ("nationality").toString());
        Author.AuthorName authorName = new Author.AuthorName();
        Author.Birth birth = new Author.Birth();
        authorName.setFirst(PropertyReader.fetchPropertyValue ("authorFirstName").toString());
        authorName.setSecond(PropertyReader.fetchPropertyValue ("authorSecondName").toString());
        birth.setCity(PropertyReader.fetchPropertyValue ("city").toString());
        birth.setCountry(PropertyReader.fetchPropertyValue ("country").toString());
        birth.setDate(PropertyReader.fetchPropertyValue ("date").toString());
        author.setBirth(birth);
        author.setAuthorName(authorName);
        return author;
    }

}
