package com.example.bookshelf.models;

public class DetailBook {

    private String title;
    private String subTitle;
    private String isbn10;
    private String price;
    private String imageUrl;
    private String url;
    private String desc;
    private String publisher;
    private String year;
    private String authors;
    private String language;
    private String pages;
    private String rating;

    public String getTitle() {
        return this.title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getisbn10() {
        return this.isbn10;
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

    public String getDesc(){
        return this.desc;
    }

    public String getPublisher(){
        return this.publisher;
    }

    public String getYear(){
        return this.year;
    }

    public String getAuthors(){
        return this.authors;
    }

    public String getLanguage(){
        return this.language;
    }

    public String getPages(){
        return this.pages;
    }

    public String getRating(){
        return this.rating;
    }

    public static class Builder {
        private DetailBook newDetailBook;

        public Builder() {
            newDetailBook = new DetailBook();
        }

        public Builder withTitle(String title){
            newDetailBook.title = title;
            return this;
        }

        public Builder withSubTitle(String subTitle){
            newDetailBook.subTitle = subTitle;
            return this;
        }

        public Builder withUrl(String url){
            newDetailBook.url = url;
            return this;
        }

        public Builder withisbn13(String isbn13){
            newDetailBook.isbn10 = isbn13;
            return this;
        }

        public Builder withPrice(String price){
            newDetailBook.price = price;
            return this;
        }

        public Builder withImage(String image){
            newDetailBook.imageUrl = image;
            return this;
        }

        public Builder withDesc(String desc){
            newDetailBook.desc = desc;
            return this;
        }

        public Builder withPublisher(String publisher){
            newDetailBook.publisher = publisher;
            return this;
        }

        public Builder withYear(String year){
            newDetailBook.year = year;
            return this;
        }

        public Builder withAuthors(String authors){
            newDetailBook.authors = authors;
            return this;
        }

        public Builder withLanguage(String language){
            newDetailBook.language = language;
            return this;
        }

        public Builder withPages(String pages){
            newDetailBook.pages = pages;
            return this;
        }

        public Builder withRating(String rating){
            newDetailBook.rating = rating;
            return this;
        }

        public DetailBook build(){
            return newDetailBook;
        }

    }
}
