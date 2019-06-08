package org.minutenwerk.mimic4j.impl.accessor;

import java.util.List;

public class ChaptersAccessor extends MmListAccessor<Book, List<Chapter>, Chapter> {

  public ChaptersAccessor(final MmModelAccessor<?, Book> bookAccessor) {
    super(bookAccessor, Book::getChapters, Book::setChapters, Book::addChapter);
  }
}
