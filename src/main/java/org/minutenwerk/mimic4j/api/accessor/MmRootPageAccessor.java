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

  /** Number of elements on current page. */
  private int      numberOfElements;

  /**
   * Constructor of this mutable class.
   */
  public MmRootPageAccessor() {
    super();
  }

  /**
   * Returns total index of first element in current page.
   *
   * @return  Total index of first element in current page.
   */
  public long getElementIndexFrom() {
    return ((pageable.getPageNumber() * pageable.getPageSize()) + 1);
  }

  /**
   * Returns total index of last element in current page.
   *
   * @return  Total index of last element in current page.
   */
  public long getElementIndexUntil() {
    return ((pageable.getPageNumber() * pageable.getPageSize()) + numberOfElements);
  }

  /**
   * Returns number of elements on current page.
   *
   * @return  Number of elements on current page.
   */
  public int getNumberOfElements() {
    return numberOfElements;
  }

  /**
   * Returns pagination information containing page number, size, sort.
   *
   * @return  Pagination information containing page number, size, sort.
   */
  public Pageable getPageable() {
    return pageable;
  }

  /**
   * Returns number of page, starts by 0.
   *
   * @return  Number of page
   */
  public int getPageNumber() {
    return pageable.getPageNumber();
  }

  /**
   * Returns size of page.
   *
   * @return  Size of page
   */
  public int getPageSize() {
    return pageable.getPageSize();
  }

  /**
   * Returns total amount of elements.
   *
   * @return  Total amount of elements.
   */
  public long getTotalElements() {
    return totalElements;
  }

  /**
   * Returns total amount of pages.
   *
   * @return  Total amount of pages.
   */
  public int getTotalPages() {
    return totalPages;
  }

  /**
   * Returns true, if there is a next page.
   *
   * @return  True, if there is a next page.
   */
  public boolean hasNextPage() {
    return (pageable.getPageNumber() + 1) < totalPages;
  }

  /**
   * Returns true, if there is a previous page.
   *
   * @return  True, if there is a previous page.
   */
  public boolean hasPreviousPage() {
    return (pageable.getPageNumber() > 0);
  }

  /**
   * Resets model and any other state information to null.
   */
  @Override
  public void reset() {
    super.reset();
    pageable      = null;
    totalElements = 0;
    totalPages    = 0;
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
    pageable         = page.getPageable();
    totalElements    = page.getTotalElements();
    totalPages       = page.getTotalPages();
    numberOfElements = page.getNumberOfElements();
  }

}
