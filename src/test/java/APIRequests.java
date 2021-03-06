import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.minidev.json.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class APIRequests {

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/library";
    }

    @Test(description = "Get all Authors and print authors second name")
    public void tc_01() {
        Response resp = given().
                when().
                get("/authors");
        List<Author> authors = resp.jsonPath().getList("$", Author.class);
        for (Author tmp : authors){
            System.out.println(tmp.authorName.second);
        }
        System.out.println(resp.asString());
        Assert.assertEquals(resp.getStatusCode(), 200);
        if (resp.getStatusCode() == 200){
            System.out.println("API is working fine");
        }
        else {
            System.out.println("API is not working fine");
        }

    }
    @Test(description = "Get Author object by 'authorId'")
    public void tc_02() {
        ValidatableResponse resp = given().
                when().
                get("/author/480").then().
                assertThat().
                statusCode(200).log().all();
    }

    @Test(description = "Get all Books, assert time of response")
    public void tc_03() {
        Response resp = given().
                when().
                get("/books");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
        given().when().get("/books")
                .then().and().time(lessThan(1000L));
    }

    @Test(description = "Get all genres")
    public void tc_04() {
        Response resp = given().
                when().
                get("/genres");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "Get Author of special Book. Negative test case")
    public void tc_05() {

        Response resp = given().
                when().
                get("/book/51/author");
        Assert.assertEquals(resp.getStatusCode(), 404);
        System.out.println(resp.asString());
    }

    @Test(description = "Get all Authors in special Genre. Negative test case")
    public void tc_06() {
        Response resp = given().
                when().
                get("/genre/227/authors");
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

    @Test(description = "Get all Books of special Author")
    public void tc_07() {
        Response resp = given().
                when().
                get("/author/480/books");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "Get all Books of special Author in special Genre")
    public void tc_08() {
        Response resp = given().
                when().
                get("/author/480/genre/227/books");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "Get Book object by 'bookId'")
    public void tc_09() {
        Response resp = given().
                when().
                get("/book/15");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "Get all Books of special Genre")
    public void tc_10() {
        Response resp = given().
                when().
                get("/genre/227/books");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "Get all Genres of special Author")
    public void tc_11() {
        Response resp = given().
                when().
                get("/author/480/genres");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "Get Genre of special Book")
    public void tc_12() {
        Response resp = given().
                when().
                get("/book/15/genre");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "Get Genre object by 'genreId'")
    public void tc_13() {
        Response resp = given().
                when().
                get("/genre/227");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "POST request - add new author")
    public void tc_14() throws ParseException {

        Response resp = given().
                when().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body((Author.setAuthorJsonObject()).toString()).
                post("/author");
        Assert.assertEquals(resp.getStatusCode(), 201);
        System.out.println(resp.asString());
    }

    @Test(description = "PUT request (Author - make changes)")
    public void tc_15() throws IOException {

        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(Author.setAutor()).
                put("/author");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "DELETE request - delete Author object")
    public void tc_16() throws IOException {
        Response resp = given().
                when().
                delete("/author/"+PropertyReader.fetchPropertyValue("authorIdForDeleteRequest"));
        System.out.println("Deleting response status code: "+resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 204);
    }

    @Test(description = "POST request - add new genre")
    public void tc_17() throws IOException {

        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(Genre.setGenre()).
                post("/genre");
        Assert.assertEquals(resp.getStatusCode(), 201);
        System.out.println(resp.asString());
    }

    @Test(description = "POST request - add new book")
    public void tc_18() throws IOException {

        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(Book.setBook()).
                post("/book/1/1");
        Assert.assertEquals(resp.getStatusCode(), 201);
        System.out.println(resp.asString());
    }

    @Test(description = "PUT request - make changes (book)")
    public void tc_19() throws IOException {

        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(Book.setBook()).
                put("/book");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "DELETE request - delete Book object")
    public void tc_20() throws IOException {
        Response resp = given().
                when().
                delete("/book/"+PropertyReader.fetchPropertyValue("bookIdForDeleteRequest"));
        System.out.println("Deleting response status code: "+resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 204);
    }

    @Test(description = "PUT request - make change (genre)")
    public void tc_21() throws IOException {

        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(Genre.setGenre()).
                put("/genre");
        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test(description = "DELETE request - delete Genre object")
    public void tc_22() throws IOException {
        Response resp = given().
                when().
                delete("/genre/"+PropertyReader.fetchPropertyValue("genreIdForDeleteRequest"));
        System.out.println("Deleting response status code: "+resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 204);
    }
}
