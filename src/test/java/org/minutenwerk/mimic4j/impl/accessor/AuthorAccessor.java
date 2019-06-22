package org.minutenwerk.mimic4j.impl.accessor;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmComponentAccessor;

public class AuthorAccessor extends MmComponentAccessor<Book, Author> {

  public AuthorAccessor(final MmComponentAccessor<?, Book> bookAccessor) {
    super(bookAccessor, Book::getAuthor, Book::setAuthor);
  }

  public MmAttributeAccessor<Author, String> name() {
    return new MmAttributeAccessor<>(this, Author::getName, Author::setName);
  }
}
