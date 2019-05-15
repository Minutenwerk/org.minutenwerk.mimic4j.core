package org.minutenwerk.mimic4j.impl.accessor;


import java.util.List;


public class Book {

  private String title;

  private Author author;

  private List<Chapter> chapters;

  public Book() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(final Author author) {
    this.author = author;
  }

  public List<Chapter> getChapters() {
    return chapters;
  }

  public void setChapters(final List<Chapter> chapters) {
    this.chapters = chapters;
  }
}
