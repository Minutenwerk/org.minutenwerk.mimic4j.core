package org.minutenwerk.mimic4j.api.accessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmInformationable;
import org.minutenwerk.mimic4j.api.MmReferencableModel;

/**
 * TODOC MmRootListModel.
 */
public class MmRootListModel<MODEL extends MmReferencableModel> implements MmReferencableModel, MmInformationable {

  /** TODOC. */
  private List<MODEL> modelList;

  /**
   * Creates a new MmRootListModel instance.
   */
  public MmRootListModel() {
    modelList = new ArrayList<MODEL>();
  }

  /**
   * TODOC.
   *
   * @param   pModelItemItem  TODOC
   *
   * @return  TODOC
   */
  public MmRootListModel<MODEL> addModelItem(MODEL pModelItemItem) {
    modelList.add(pModelItemItem);
    return this;
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  @Override
  public Object[] getInfo() {
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
  public List<String> getMmReferenceValues() {
    return Collections.emptyList();
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  public List<MODEL> getModelItem() {
    return modelList;
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  public Long getModelItemCount() {
    return (long)modelList.size();
  }

  /**
   * TODOC.
   *
   * @param   pModelItem  TODOC
   *
   * @return  TODOC
   */
  public MmRootListModel<MODEL> setModelItem(List<MODEL> pModelItem) {
    modelList.clear();
    pModelItem.stream().forEach(this::addModelItem);
    return this;
  }
}
