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
}
