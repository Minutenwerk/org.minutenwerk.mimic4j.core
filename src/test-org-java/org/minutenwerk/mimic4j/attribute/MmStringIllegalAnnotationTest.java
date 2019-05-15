package org.minutenwerk.mimic4j.attribute;

import org.junit.Assert;
import org.junit.Test;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class MmStringIllegalAnnotationTest {

  public class TestCustomer {
  }
  
  public class MmTestTab extends MmTab<TestCustomer> {
    
    @MmBooleanAnnotation(id = "vn")
    public final MmString vorname = new MmString(this);
    
    public MmTestTab(MmBaseDeclaration<?, ?> pParent) {
      super(pParent);
    }

    @Override
    public void callbackMmSetModelFromModelside(TestCustomer pModel) {
    }

    @Override
    public void callbackMmSetModelsideFromModel(TestCustomer pModel) {
    }
  }
  
  public class MmTestRoot extends MmRoot {
    public final MmTestTab customer = new MmTestTab(this);
  }
  
  public MmTestRoot root = new MmTestRoot();

  /** MmString with illegal annotation. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAnnotation() {
    Assert.assertTrue("MmString must be visible:", root.customer.vorname.isMmVisible());
  }

}
