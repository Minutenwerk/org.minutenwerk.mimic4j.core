package org.minutenwerk.mimic4j.impl.accessor;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;

public class BookAccessor extends MmRootAccessor<Book> {

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
    return new ChapterAccessor(chapters(), () -> index);
  }
}
