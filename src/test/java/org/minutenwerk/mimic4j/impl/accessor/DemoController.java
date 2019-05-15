package org.minutenwerk.mimic4j.impl.accessor;

public class DemoController {

  private Book selectedBook;

  public Book getSelectedBook() {
    return selectedBook;
  }

  public void setSelectedBook(final Book pSelectedBook) {
    this.selectedBook = pSelectedBook;
  }

  public BookAccessor book() {
    return new BookAccessor(this);
  }
}
