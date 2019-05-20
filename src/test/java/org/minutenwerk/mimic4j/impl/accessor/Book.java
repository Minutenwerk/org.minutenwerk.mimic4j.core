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

  public void setTitle(final String pTitle) {
    title = pTitle;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(final Author pAuthor) {
    author = pAuthor;
  }

  public List<Chapter> getChapters() {
    return chapters;
  }

  public void setChapters(final List<Chapter> pChapters) {
    chapters = pChapters;
  }
}
