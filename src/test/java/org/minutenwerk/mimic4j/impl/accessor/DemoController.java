package org.minutenwerk.mimic4j.impl.accessor;

public class DemoController {

  private final BookAccessor selectedBookAccessor = new BookAccessor();

  public BookAccessor selectedBook() {
    return selectedBookAccessor;
  }
}
