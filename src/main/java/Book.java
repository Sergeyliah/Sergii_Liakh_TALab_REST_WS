import java.io.IOException;

public class Book {
    public int bookId;
    public String bookDescription;
    public String bookLanguage;
    public String bookName;
    public int publicationYear;
    public Additional additional;

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setAdditional(Additional additional) {
        this.additional = additional;
    }

    public static class Additional {
        public int pageCount;
        public Size size;

        public static class Size {
            public int height;
            public int length;
            public int width;

            public void setHeight(int height) {
                this.height = height;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public void setSize(Size size) {
            this.size = size;
        }
    }
    public static Book setBook() throws IOException {
        Book book = new Book();
        book.setBookId(Integer.parseInt(PropertyReader.fetchPropertyValue("bookId").toString()));
        book.setBookLanguage(PropertyReader.fetchPropertyValue("bookLanguage").toString());
        book.setBookName(PropertyReader.fetchPropertyValue("bookName").toString());
        book.setBookDescription(PropertyReader.fetchPropertyValue("bookDescription").toString());
        book.setPublicationYear(Integer.parseInt(PropertyReader.fetchPropertyValue("publicationYear").toString()));
        Book.Additional additional = new Book.Additional();
        additional.setPageCount(Integer.parseInt(PropertyReader.fetchPropertyValue("pageCount").toString()));
        Book.Additional.Size size = new Book.Additional.Size();
        size.setHeight(Integer.parseInt(PropertyReader.fetchPropertyValue("height").toString()));
        size.setLength(Integer.parseInt(PropertyReader.fetchPropertyValue("length").toString()));
        size.setWidth(Integer.parseInt(PropertyReader.fetchPropertyValue("width").toString()));
        additional.setSize(size);
        book.setAdditional(additional);
        return book;
    }
}
