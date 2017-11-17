package org.minutenwerk.mimic4j;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.junit.Assert;
import org.junit.Test;

public class MmMimicModelTest {

  public class TestRoot extends MmRoot {
    public final TestDiv testDiv = new TestDiv(this);
  }

  public final MmRoot root = new TestRoot();

  @Test
  public void test1() {
    Assert.assertTrue("Div must be visible", MmRelationshipApi.getMmChildByName(this.root, "testDiv").isMmVisible());
  }

  @Test
  public void test2() {
    Assert.assertTrue("Div must be enabled", MmRelationshipApi.getMmChildByName(this.root, "testDiv").isMmEnabled());
  }

  @Test
  public void test3() {
    Assert.assertFalse("Div must be NOT read only", MmRelationshipApi.getMmChildByName(this.root, "testDiv").isMmReadOnly());
  }

  @Test
  public void test4() {
    Assert.assertNotNull("Div parent must be NOT null", MmRelationshipApi.getMmParent(MmRelationshipApi.getMmChildByName(this.root, "testDiv")));
  }

  @Test
  public void test5() {
    Assert.assertEquals("Div must have one ancestor", 1, MmRelationshipApi.getMmAncestors(MmRelationshipApi.getMmChildByName(this.root, "testDiv")).size());
  }

  @Test
  public void test6() {
    Assert.assertEquals("Div must have 2 children", 2, MmRelationshipApi.getMmChildren(MmRelationshipApi.getMmChildByName(this.root, "testDiv")).size());
  }

  @Test
  public void test7() {
    Assert.assertEquals("Div must have 9 descendants", 9, MmRelationshipApi.getMmDescendants(MmRelationshipApi.getMmChildByName(this.root, "testDiv")).size());
  }

  @Test
  public void test8() {
    MmMimic child = MmRelationshipApi.getMmChildren(MmRelationshipApi.getMmChildByName(this.root, "testDiv")).get(0);
    Assert.assertNotNull("Div must have parent", MmRelationshipApi.getMmParent(child));
  }

  @Test
  public void test9() {
    MmMimic child = MmRelationshipApi.getMmChildren(MmRelationshipApi.getMmChildByName(this.root, "testDiv")).get(0);
    Assert.assertEquals("Div child[0] must have two ancestors", 2, MmRelationshipApi.getMmAncestors(child).size());
  }

  @Test
  public void test10() {
    MmMimic child = MmRelationshipApi.getMmChildren(MmRelationshipApi.getMmChildByName(this.root, "testDiv")).get(0);
    Assert.assertEquals("Div child[0] must have 4 descendants", 4, MmRelationshipApi.getMmDescendants(child).size());
  }

  @Test
  public void testTypes() {
    MmMimic mm = MmRelationshipApi.getMmDescendantByFullName(this.root, "testDiv.tab1.vorname");
    Assert.assertNotNull("", mm);
    MmAttributeMimic<?,?> ed = (MmAttributeMimic<?,?>)mm;
    Assert.assertEquals("", String.class, ed.getMmModelsideType());
    Assert.assertEquals("", String.class, ed.getMmViewsideType());
  }

}
