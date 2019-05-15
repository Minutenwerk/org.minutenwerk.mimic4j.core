package org.minutenwerk.mimic4j.impl.accessor;

public class AuthorAccessor extends MmComponentAccessor<Book, Author> {

  public AuthorAccessor(final MmModelAccessor<?, Book> bookAccessor) {
    super(bookAccessor, Book::getAuthor, Book::setAuthor);
  }

  public MmAttributeAccessor<Author, String> name() {
    return new MmAttributeAccessor<>(this, Author::getName, Author::setName);
  }
}
