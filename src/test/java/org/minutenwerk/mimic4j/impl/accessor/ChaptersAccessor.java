package org.minutenwerk.mimic4j.impl.accessor;

import java.util.List;

import org.minutenwerk.mimic4j.api.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;

public class ChaptersAccessor extends MmListAccessor<Book, List<Chapter>, Chapter> {

  public ChaptersAccessor(final MmModelAccessor<?, Book> bookAccessor) {
    super(bookAccessor, Book::getChapters, Book::setChapters, Book::addChapter);
  }
}
