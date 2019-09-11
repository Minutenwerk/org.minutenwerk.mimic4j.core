package org.minutenwerk.mimic4j.impl.accessor;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmComponentAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;

public class AuthorAccessor extends MmComponentAccessor<Book, Author> {

  public AuthorAccessor(final MmModelAccessor<?, Book> bookAccessor) {
    super(bookAccessor, Book::getAuthor, Book::setAuthor);
  }

  public MmAttributeAccessor<Author, String> name() {
    return new MmAttributeAccessor<>(this, Author::getName, Author::setName);
  }
}
