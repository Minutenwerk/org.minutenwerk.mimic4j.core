package org.minutenwerk.mimic4j.impl.accessor;

import java.util.List;

import org.minutenwerk.mimic4j.api.accessor.MmComponentAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmListAccessor;

public class ChaptersAccessor extends MmListAccessor<Book, List<Chapter>, Chapter> {

  public ChaptersAccessor(final MmComponentAccessor<?, Book> bookAccessor) {
    super(bookAccessor, Book::getChapters, Book::setChapters, Book::addChapter);
  }
}
