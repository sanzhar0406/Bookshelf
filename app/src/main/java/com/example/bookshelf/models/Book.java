package com.example.bookshelf.models;

public class Book {

    private String title;
    private String subTitle;
    private String isbn13;
    private String price;
    private String imageUrl;
    private String url;


    public String getTitle() {
        return this.title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getisbn13() {
        return this.isbn13;
    }

    public String getPrice() {
        return this.price;
    }

    public String getImage() {
        return this.imageUrl;
    }

    public String getUrl() {
        return this.url;
    }

    public static class Builder {
        private Book newBook;

        public Builder() {
            newBook = new Book();
        }

        public Builder withTitle(String title){
            newBook.title = title;
            return this;
        }

        public Builder withSubTitle(String subTitle){
            newBook.subTitle = subTitle;
            return this;
        }

        public Builder withUrl(String url){
            newBook.url = url;
            return this;
        }

        public Builder withisbn13(String isbn13){
            newBook.isbn13 = isbn13;
            return this;
        }

        public Builder withPrice(String price){
            newBook.price = price;
            return this;
        }

        public Builder withImage(String image){
            newBook.imageUrl = image;
            return this;
        }

        public Book build(){
            return newBook;
        }

    }
}
