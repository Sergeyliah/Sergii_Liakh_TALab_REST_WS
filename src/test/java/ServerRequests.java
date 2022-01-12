import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

public class ServerRequests {
    //Get all Authors
    //Print authors second name
    @Test
    public void tc_01() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/authors");
        List<Author> authors = resp.jsonPath().getList("$", Author.class);
        for (Author tmp : authors){
            System.out.println(tmp.authorName.second);
        }
        Assert.assertEquals(resp.getStatusCode(), 200);
        if (resp.getStatusCode() == 200){
            System.out.println("API is working fine");
        }
        else {
            System.out.println("API is not working fine");
        }

    }
    //Get Author object by 'authorId'
    @Test
    public void tc_02() {
        ValidatableResponse resp = given().
                when().
                get("http://localhost:8080/api/library/author/36").then().
                assertThat().
                statusCode(200);

        System.out.println(resp.extract().asString());
    }
    //Get all Books
    @Test
    public void tc_03() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/books");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //Get all genres
    @Test
    public void tc_04() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/genres");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //Get Author of special Book
    //Negative test case
    @Test
    public void tc_05() {

        Response resp = given().
                when().
                get("http://localhost:8080/api/library/book/51/author");
        Assert.assertEquals(resp.getStatusCode(), 404);
        System.out.println(resp.asString());
    }
    //Get all Authors in special Genre
    //Negative test case

    @Test
    public void tc_06() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/genre/25/authors");
        List<Author> authors = resp.jsonPath().getList("$", Author.class);
        boolean x = false;
        for (Author tmp : authors){
            if (tmp.authorName.second.equalsIgnoreCase("Shevchenko")){
                x = true;
                break;
            }
        }
        Assert.assertFalse(x);
        System.out.println(resp.asString());
    }
    //Get all Books of special Author
    @Test
    public void tc_07() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/author/36/books");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //Get all Books of special Author in special Genre
    @Test
    public void tc_08() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/author/75/genre/1334/books");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //Get Book object by 'bookId'
    @Test
    public void tc_09() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/book/51");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //Get all Books of special Genre
    @Test
    public void tc_10() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/genre/1334/books");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //Get all Genres of special Author
    @Test
    public void tc_11() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/author/75/genres");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //Get Genre of special Book
    @Test
    public void tc_12() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/book/51/genre");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //Get Genre object by 'genreId'
    @Test
    public void tc_13() {
        Response resp = given().
                when().
                get("http://localhost:8080/api/library/genre/1334");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //POST request - add new author
    @Test
    public void tc_14() {
        Author author = new Author();
        author.setAuthorId(1);
        author.setAuthorDescription("famous ukrainian writer");
        author.setNationality("ukrainian");
        Author.AuthorName authorName = new Author.AuthorName();
        Author.Birth birth = new Author.Birth();
        authorName.setFirst("Taras");
        authorName.setSecond("Shevchenko");
        birth.setCity("Morintsi");
        birth.setCountry("Ukraine");
        birth.setDate("1814-03-09");
        author.setBirth(birth);
        author.setAuthorName(authorName);
        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(author).
                post("http://localhost:8080/api/library/author");
        Assert.assertEquals(resp.getStatusCode(), 201);
        System.out.println(resp.asString());
    }
    //PUT request (Author - make changes)
    @Test
    public void tc_15() {
        Author author = new Author();
        author.setAuthorId(1);
        author.setAuthorDescription("famous ukrainian writer and painter");
        author.setNationality("ukrainian");
        Author.AuthorName authorName = new Author.AuthorName();
        Author.Birth birth = new Author.Birth();
        authorName.setFirst("Taras");
        authorName.setSecond("Shevchenko");
        birth.setCity("Morintsi");
        birth.setCountry("Ukraine");
        birth.setDate("1814-03-09");
        author.setBirth(birth);
        author.setAuthorName(authorName);
        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(author).
                put("http://localhost:8080/api/library/author");
        Assert.assertEquals(resp.getStatusCode(), 201);
        System.out.println(resp.asString());
    }
    //DELETE request - delete "Author" object
    @Test
    public void tc_16() {
        Response resp = given().
                when().
                delete("http://localhost:8080/api/library/author/1");
        System.out.println("Deleting response status code: "+resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 204);
    }
    //POST request - add new genre
    @Test
    public void tc_17() {
        Genre genre = new Genre();
        genre.setGenreId(1);
        genre.setGenreName("poetry");
        genre.setGenreDescription("genre of poetry");
        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(genre).
                post("http://localhost:8080/api/library/genre");
        Assert.assertEquals(resp.getStatusCode(), 201);
        System.out.println(resp.asString());
    }
    //POST request - add new book
    @Test
    public void tc_18() {
        Book book = new Book();
        book.setBookId(1);
        book.setBookLanguage("ukrainian");
        book.setBookName("Kobzar");
        book.setBookDescription("famous book of Taras Shevchenko");
        book.setPublicationYear(1840);
        Book.Additional additional = new Book.Additional();
        additional.setPageCount(352);
        Book.Additional.Size size = new Book.Additional.Size();
        size.setHeight(25);
        size.setLength(210);
        size.setWidth(145);
        additional.setSize(size);
        book.setAdditional(additional);
        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(book).
                post("http://localhost:8080/api/library/book/1/1");
        Assert.assertEquals(resp.getStatusCode(), 201);
        System.out.println(resp.asString());
    }
    //PUT request - make changes (book)
    @Test
    public void tc_19() {
        Book book = new Book();
        book.setBookId(1);
        book.setBookLanguage("ukrainian");
        book.setBookName("Kobzar");
        book.setBookDescription("Taras Shevchenko. Genre poetry");
        book.setPublicationYear(1840);
        Book.Additional additional = new Book.Additional();
        additional.setPageCount(352);
        Book.Additional.Size size = new Book.Additional.Size();
        size.setHeight(25);
        size.setLength(210);
        size.setWidth(145);
        additional.setSize(size);
        book.setAdditional(additional);
        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(book).
                put("http://localhost:8080/api/library/book");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //DELETE request - delete "Book" object
    @Test
    public void tc_20() {
        Response resp = given().
                when().
                delete("http://localhost:8080/api/library/book/1");
        System.out.println("Deleting response status code: "+resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 204);
    }
    //PUT request - make change (genre)
    @Test
    public void tc_21() {
        Genre genre = new Genre();
        genre.setGenreId(1);
        genre.setGenreName("poetry");
        genre.setGenreDescription("poetry");
        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(genre).
                put("http://localhost:8080/api/library/genre");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //DELETE request - delete "Genre" object
    @Test
    public void tc_22() {
        Response resp = given().
                when().
                delete("http://localhost:8080/api/library/genre/1");
        System.out.println("Deleting response status code: "+resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 204);
    }
}
