package com.example.barcodescanner;

public class MyData2 {
    private int id,isbn;
    private String name,image_link,bookauth,subject;

    public MyData2(int isbn ,String bookname, String imageLink,String bookauth,String subject) {
        this.image_link = imageLink;
        this.name=bookname;
        this.bookauth=bookauth;
        this.subject=subject;
        this.isbn=isbn;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int id) {
        this.isbn = isbn;
    }
    //
    public String getBookauth() {
        return bookauth;
    }

    public void setBookauth(String bookauth) {
        this.bookauth = bookauth;
    }
    public String getName() {
        return name;
    }
    public void setName(String Name) {
        this.name = Name;
    }
        public String getsubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
//    public String getEdition() {
//        return edition;
//    }
//    public void setEdition(String edition) {
//        this.edition = edition;
//    }
    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}
