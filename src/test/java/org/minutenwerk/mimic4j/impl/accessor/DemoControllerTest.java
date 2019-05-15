package org.minutenwerk.mimic4j.impl.accessor;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class DemoControllerTest {

  @Test
  public void test() {
    // create pojos
    Book book = new Book();
    Author author = new Author();
    Chapter chapter1 = new Chapter();
    Chapter chapter2 = new Chapter();
    List<Chapter> chapters = new ArrayList<>();

    // create controller
    DemoController controller = new DemoController();

    // let controller point to pojo
    // use accessors to set values and assert values
    assertTrue(controller.book().isNotPresent());
    controller.setSelectedBook(book);
    assertTrue(controller.book().isPresent());
    assertEquals(book, controller.book().get());

    assertTrue(controller.book().title().isNotPresent());
    controller.book().title().set("Some title");
    assertTrue(controller.book().title().isPresent());
    assertEquals("Some title", controller.book().title().get());

    assertTrue(controller.book().author().isNotPresent());
    controller.book().author().set(author);
    assertTrue(controller.book().author().isPresent());
    assertEquals(author, controller.book().author().get());

    assertTrue(controller.book().author().name().isNotPresent());
    controller.book().author().name().set("Some author name");
    assertTrue(controller.book().author().name().isPresent());
    assertEquals("Some author name", controller.book().author().name().get());

    assertTrue(controller.book().chapters().isNotPresent());
    controller.book().chapters().set(chapters);
    assertTrue(controller.book().chapters().isPresent());
    assertTrue(controller.book().chapters().isEmpty());
    assertEquals(0, controller.book().chapters().size());
    assertEquals(chapters, controller.book().chapters().get());

    controller.book().chapters().add(chapter1);
    assertEquals(1, controller.book().chapters().size());
    assertTrue(controller.book().chapters().contains(chapter1));
    assertEquals(chapter1, controller.book().chapters(0).get());

    assertTrue(controller.book().chapters(0).topic().isNotPresent());
    controller.book().chapters(0).topic().set("Some topic");
    assertTrue(controller.book().chapters(0).topic().isPresent());
    assertEquals("Some topic", controller.book().chapters(0).topic().get());

    controller.book().chapters().add(chapter2);
    assertEquals(2, controller.book().chapters().size());
    assertTrue(controller.book().chapters().contains(chapter2));

    // let controller point to null
    controller.setSelectedBook(null);
    assertTrue(controller.book().isNotPresent());
    assertTrue(controller.book().title().isNotPresent());
    assertTrue(controller.book().author().isNotPresent());
    assertTrue(controller.book().author().name().isNotPresent());
  }
}
