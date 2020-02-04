package org.minutenwerk.mimic4j.api.accessor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Model accessor on root page, containing pagination information containing page number, size, sort. DO NOT use set(List<VALUE_MODEL>), but
 * set(org.springframework.data.domain.Page<VALUE_MODEL>).
 *
 * @param   <VALUE_MODEL>  Type of root model in page.
 *
 * @author  Olaf Kossak
 */
public class MmRootPageAccessor<VALUE_MODEL> extends MmRootListAccessor<VALUE_MODEL> {

  /** Pagination information containing page number, size, sort. */
  private Pageable pageable;

  /** Total amount of elements. */
  private long     totalElements;

  /** Total amount of pages. */
  private int      totalPages;

  /**
   * Constructor of this mutable class.
   */
  public MmRootPageAccessor() {
    super();
  }

  /**
   * Returns pagination information containing page number, size, sort.
   *
   * @return  pagination information containing page number, size, sort.
   */
  public Pageable getPageable() {
    return pageable;
  }

  /**
   * Returns total amount of elements.
   *
   * @return  total amount of elements.
   */
  public long getTotalElements() {
    return totalElements;
  }

  /**
   * Returns total amount of pages.
   *
   * @return  total amount of pages.
   */
  public int getTotalPages() {
    return totalPages;
  }

  /**
   * Returns true, if there is a next page.
   *
   * @return  true, if there is a next page.
   */
  public boolean hasNextPage() {
    return (pageable.getPageNumber() + 1) < totalPages;
  }

  /**
   * Sets the specified model value, may be null, and notifies model change listener.
   *
   * @param   value  The specified model value, may be null.
   *
   * @throws  UnsupportedOperationException  In case of calling this method.
   */
  @Override
  public void set(final List<VALUE_MODEL> value) {
    throw new UnsupportedOperationException("Please use MmRootPageAccessor.set(org.springframework.data.domain.Page)");
  }

  /**
   * Sets the specified model value, may be null, and notifies model change listener.
   *
   * @param  page  value The specified model value, may be null.
   */
  public void set(final Page<VALUE_MODEL> page) {
    super.set(page.getContent());
    pageable      = page.getPageable();
    totalElements = page.getTotalElements();
    totalPages    = page.getTotalPages();
  }

}
