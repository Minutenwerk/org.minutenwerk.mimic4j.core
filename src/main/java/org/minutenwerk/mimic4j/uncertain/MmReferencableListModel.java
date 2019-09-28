package org.minutenwerk.mimic4j.uncertain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmInformationableModel;
import org.minutenwerk.mimic4j.api.MmReferencableModel;

/**
 * TODOC MmReferencableListModel.
 */
public class MmReferencableListModel<ROW_MODEL> extends ArrayList<ROW_MODEL> implements MmReferencableModel, MmInformationableModel {

  /** TODOC. */
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new MmReferencableListModel instance.
   */
  public MmReferencableListModel() {
  }

  /**
   * Creates a new MmReferencableListModel instance.
   *
   * @param  pList  TODOC
   */
  public MmReferencableListModel(List<ROW_MODEL> pList) {
    pList.forEach(this::add);
  }

  /**
   * TODOC.
   *
   * @param   pModelItemItem  TODOC
   *
   * @return  TODOC
   */
  public MmReferencableListModel<ROW_MODEL> addModelItem(ROW_MODEL pModelItemItem) {
    add(pModelItemItem);
    return this;
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  @Override
  public Object[] getMmInfo() {
    final Object[] info = new Object[1];
    info[0] = getModelItemCount();
    return info;
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  @Override
  public List<String> getMmReferenceParams() {
    return Collections.emptyList();
  }

  /**
   * TODOC.
   *
   * @param   pIndex  TODOC
   *
   * @return  TODOC
   */
  public ROW_MODEL getModelItem(int pIndex) {
    return get(pIndex);
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  public Long getModelItemCount() {
    return (long)size();
  }

  /**
   * TODOC.
   *
   * @param   pModelList  TODOC
   *
   * @return  TODOC
   */
  public MmReferencableListModel<ROW_MODEL> setModelList(List<ROW_MODEL> pModelList) {
    clear();
    pModelList.stream().forEach(this::add);
    return this;
  }
}
