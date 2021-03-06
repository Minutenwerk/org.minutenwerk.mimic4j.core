package org.minutenwerk.mimic4j.impl.accessor;

public class BookAccessor extends MmComponentAccessor<DemoController, Book> {

  public BookAccessor(final DemoController demoController) {
    super(demoController, DemoController::getSelectedBook, DemoController::setSelectedBook);
  }

  public MmAttributeAccessor<Book, String> title() {
    return new MmAttributeAccessor<>(this, Book::getTitle, Book::setTitle);
  }

  public AuthorAccessor author() {
    return new AuthorAccessor(this);
  }

  public ChaptersAccessor chapters() {
    return new ChaptersAccessor(this);
  }

  public ChapterAccessor chapters(final int index) {
    return new ChapterAccessor(this.chapters(), () -> index);
  }
}
