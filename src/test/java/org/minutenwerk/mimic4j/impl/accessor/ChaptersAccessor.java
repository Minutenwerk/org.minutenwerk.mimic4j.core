package org.minutenwerk.mimic4j.impl.accessor;

import org.minutenwerk.mimic4j.api.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;

public class ChaptersAccessor extends MmListAccessor<Book, Chapter> {

  public ChaptersAccessor(final MmModelAccessor<?, Book> bookAccessor) {
    super(bookAccessor, Book::getChapters, Book::setChapters, Book::addChapter);
  }
}
