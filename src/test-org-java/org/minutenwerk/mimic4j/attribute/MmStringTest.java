package org.minutenwerk.mimic4j.attribute;

import org.junit.Assert;
import org.junit.Test;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class MmStringTest {

  public class TestCustomer {
    private String vorname;
    private String nachname;
    private String city;

    public String getVorname() {
      return this.vorname;
    }

    public void setVorname(String pVorname) {
      this.vorname = pVorname;
    }

    public String getNachname() {
      return this.nachname;
    }

    public void setNachname(String pNachname) {
      this.nachname = pNachname;
    }

    public final String getCity() {
      return this.city;
    }

    public final void setCity(String pCity) {
      this.city = pCity;
    }
  }
  
  public class MmTestTab extends MmTab<TestCustomer> {
    
    public final MmString vorname = new MmString(this);
    
    @MmStringAnnotation(id = "nn")
    public final MmString nachname = new MmString(this);
    
    @MmStringAnnotation(
      id = "ct", 
      cols = 20, 
      defaultValue = "New York", 
      formatMaxLength = 20, 
      enabled = false, 
      readOnly = true, 
      required = true, 
      visible = false, 
      rows = 5)
    public final MmString city = new MmString(this);
    
    public MmTestTab(MmBaseDeclaration<?, ?> pParent) {
      super(pParent);
    }

    @Override
    public void callbackMmSetModelFromModelside(TestCustomer pModel) {
      pModel.setVorname(this.vorname.getMmModelsideValue());
      pModel.setNachname(this.nachname.getMmModelsideValue());
      pModel.setCity(this.city.getMmModelsideValue());
    }

    @Override
    public void callbackMmSetModelsideFromModel(TestCustomer pModel) {
      this.vorname.setMmModelsideValue(pModel.getVorname());
      this.nachname.setMmModelsideValue(pModel.getNachname());
      this.city.setMmModelsideValue(pModel.getCity());
    }
  }
  
  public class MmTestRoot extends MmRoot {
    public final MmTestTab customer = new MmTestTab(this);    
  }
  
  public MmTestRoot root = new MmTestRoot();

  /** MmString without MmStringAnnotation, testing default configuration. */
  @Test
  public void testDefaultConfiguration() {
    Assert.assertTrue("MmString must be visible:", root.customer.vorname.isMmVisible());
    Assert.assertTrue("MmString must be enabled:", root.customer.vorname.isMmEnabled());
    Assert.assertFalse("MmString must be not readOnly:", root.customer.vorname.isMmReadOnly());
    Assert.assertFalse("MmString must be not required:", root.customer.vorname.isMmRequired());
    Assert.assertFalse("MmString must be not valid:", root.customer.vorname.isMmValid());
    Assert.assertTrue("MmString must be reset enabled:", root.customer.vorname.isMmResetEnabled());
    Assert.assertFalse("MmString must be not changed from viewside:", root.customer.vorname.isMmChangedFromViewside());
    Assert.assertEquals("Value must be String class:", String.class, root.customer.vorname.getMmModelsideType());
    Assert.assertEquals("Value must be empty String:", "", root.customer.vorname.getMmModelsideValue());
    Assert.assertEquals("Value must be 85:", 85, root.customer.vorname.getMmCols());
    Assert.assertEquals("Value must be empty String:", "", root.customer.vorname.getMmDefaultValue());
    Assert.assertEquals("Value must be 255:", 255, root.customer.vorname.getMmFormatMaxLength());
    Assert.assertEquals("Value must be empty string:", "", root.customer.vorname.getMmFormatPattern());
    Assert.assertEquals("Value must be String class:", String.class, root.customer.vorname.getMmViewsideType());
    Assert.assertEquals("Value must be empty String:", "", root.customer.vorname.getMmViewsideValue());
    Assert.assertEquals("Value must be customer.vorname:", "customer.vorname", root.customer.vorname.getMmFullName());
    Assert.assertEquals("Value must be customer-vorname:", "customer-vorname", root.customer.vorname.getMmId());
    Assert.assertEquals("Value must be empty String:", "", root.customer.vorname.getMmLongDescription());
    Assert.assertEquals("Value must be vorname:", "vorname", root.customer.vorname.getMmName());
    Assert.assertEquals("Value must be customer:", root.customer, MmRelationshipApi.getMmParent(root.customer.vorname));
    Assert.assertEquals("Value must be empty String:", "", root.customer.vorname.getMmResetValue());
    Assert.assertEquals("Value must be 3:", 3, root.customer.vorname.getMmRows());
    Assert.assertEquals("Value must be customer-vorname:", "customer-vorname", root.customer.vorname.getMmShortDescription());
    Assert.assertTrue("Value must be empty HashSet:", root.customer.vorname.getMmStyleClasses().isEmpty());
    Assert.assertEquals("Value must be root:", root, MmRelationshipApi.getMmRoot(root.customer.vorname));
  }

  /** MmString with MmStringAnnotation, testing default configuration of annotation. */
  @Test
  public void testDefaultConfigurationOfAnnotation() {
    Assert.assertTrue("MmString must be visible:", root.customer.nachname.isMmVisible());
    Assert.assertTrue("MmString must be enabled:", root.customer.nachname.isMmEnabled());
    Assert.assertFalse("MmString must be not readOnly:", root.customer.nachname.isMmReadOnly());
    Assert.assertFalse("MmString must be not required:", root.customer.nachname.isMmRequired());
    Assert.assertFalse("MmString must be not valid:", root.customer.nachname.isMmValid());
    Assert.assertTrue("MmString must be reset enabled:", root.customer.nachname.isMmResetEnabled());
    Assert.assertFalse("MmString must be not changed from viewside:", root.customer.nachname.isMmChangedFromViewside());
    Assert.assertEquals("Value must be String class:", String.class, root.customer.nachname.getMmModelsideType());
    Assert.assertEquals("Value must be empty String:", "", root.customer.nachname.getMmModelsideValue());
    Assert.assertEquals("Value must be 85:", 85, root.customer.nachname.getMmCols());
    Assert.assertEquals("Value must be empty String:", "", root.customer.nachname.getMmDefaultValue());
    Assert.assertEquals("Value must be 255:", 255, root.customer.nachname.getMmFormatMaxLength());
    Assert.assertEquals("Value must be empty string:", "", root.customer.nachname.getMmFormatPattern());
    Assert.assertEquals("Value must be String class:", String.class, root.customer.nachname.getMmViewsideType());
    Assert.assertEquals("Value must be empty String:", "", root.customer.nachname.getMmViewsideValue());
    Assert.assertEquals("Value must be customer.nachname:", "customer.nachname", root.customer.nachname.getMmFullName());    
    Assert.assertEquals("Value must be nn:", "nn", root.customer.nachname.getMmId());
    Assert.assertEquals("Value must be empty String:", "", root.customer.nachname.getMmLongDescription());
    Assert.assertEquals("Value must be nachname:", "nachname", root.customer.nachname.getMmName());
    Assert.assertEquals("Value must be customer:", root.customer, MmRelationshipApi.getMmParent(root.customer.nachname));
    Assert.assertEquals("Value must be empty String:", "", root.customer.nachname.getMmResetValue());
    Assert.assertEquals("Value must be 3:", 3, root.customer.nachname.getMmRows());
    Assert.assertEquals("Value must be empty String:", "nn", root.customer.nachname.getMmShortDescription());
    Assert.assertTrue("Value must be empty String:", root.customer.nachname.getMmStyleClasses().isEmpty());
    Assert.assertEquals("Value must be root:", root, MmRelationshipApi.getMmRoot(root.customer.nachname));
  }

  /** MmString with MmStringAnnotation, testing specific configuration of annotation. */
  @Test
  public void testSpecificConfigurationOfAnnotation() {
    Assert.assertFalse("MmString must be not visible:", root.customer.city.isMmVisible());
    Assert.assertFalse("MmString must be not enabled:", root.customer.city.isMmEnabled());
    Assert.assertTrue("MmString must be readOnly:", root.customer.city.isMmReadOnly());
    Assert.assertTrue("MmString must be required:", root.customer.city.isMmRequired());
    Assert.assertFalse("MmString must be not valid:", root.customer.city.isMmValid());
    Assert.assertTrue("MmString must be reset enabled:", root.customer.city.isMmResetEnabled());
    Assert.assertFalse("MmString must be not changed from viewside:", root.customer.city.isMmChangedFromViewside());
    Assert.assertEquals("Value must be String class:", String.class, root.customer.city.getMmModelsideType());
    Assert.assertEquals("Value must be New York:", "New York", root.customer.city.getMmModelsideValue());
    Assert.assertEquals("Value must be 20:", 20, root.customer.city.getMmCols());
    Assert.assertEquals("Value must be New York:", "New York", root.customer.city.getMmDefaultValue());
    Assert.assertEquals("Value must be 255:", 255, root.customer.city.getMmFormatMaxLength());
    Assert.assertEquals("Value must be empty string:", "", root.customer.city.getMmFormatPattern());
    Assert.assertEquals("Value must be String class:", String.class, root.customer.city.getMmViewsideType());
    Assert.assertEquals("Value must be New York:", "New York", root.customer.city.getMmViewsideValue());
    Assert.assertEquals("Value must be customer.city:", "customer.city", root.customer.city.getMmFullName());
    Assert.assertEquals("Value must be ct:", "ct", root.customer.city.getMmId());
    Assert.assertEquals("Value must be empty:", "", root.customer.city.getMmLongDescription());
    Assert.assertEquals("Value must be city:", "city", root.customer.city.getMmName());
    Assert.assertEquals("Value must be customer:", root.customer, MmRelationshipApi.getMmParent(root.customer.city));
    Assert.assertEquals("Value must be New York:", "New York", root.customer.city.getMmResetValue());
    Assert.assertEquals("Value must be 5:", 5, root.customer.city.getMmRows());
    Assert.assertEquals("Value must be empty:", "ct *", root.customer.city.getMmShortDescription());
    Assert.assertEquals("Value must be empty:", "", root.customer.city.getMmStyleClasses());
    Assert.assertEquals("Value must be root:", root, MmRelationshipApi.getMmRoot(root.customer.city));
  }

}
