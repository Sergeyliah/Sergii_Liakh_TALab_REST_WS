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
}
