package org.minutenwerk.mimic4j.impl.accessor;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.container.MmTab;


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
    new MmTab<>(new MmRoot(), controller.selectedBook());

    // let controller point to pojo
    // use accessors to set values and assert values
    assertTrue(controller.selectedBook().isNotPresent());
    controller.selectedBook().set(book);
    assertTrue(controller.selectedBook().isPresent());
    assertEquals(book, controller.selectedBook().get());

    assertTrue(controller.selectedBook().title().isNotPresent());
    controller.selectedBook().title().set("Some title");
    assertTrue(controller.selectedBook().title().isPresent());
    assertEquals("Some title", controller.selectedBook().title().get());

    assertTrue(controller.selectedBook().author().isNotPresent());
    controller.selectedBook().author().set(author);
    assertTrue(controller.selectedBook().author().isPresent());
    assertEquals(author, controller.selectedBook().author().get());

    assertTrue(controller.selectedBook().author().name().isNotPresent());
    controller.selectedBook().author().name().set("Some author name");
    assertTrue(controller.selectedBook().author().name().isPresent());
    assertEquals("Some author name", controller.selectedBook().author().name().get());

    assertTrue(controller.selectedBook().chapters().isNotPresent());
    controller.selectedBook().chapters().set(chapters);
    assertTrue(controller.selectedBook().chapters().isPresent());
    assertTrue(controller.selectedBook().chapters().isEmpty());
    assertEquals(0, controller.selectedBook().chapters().size());
    assertEquals(chapters, controller.selectedBook().chapters().get());

    controller.selectedBook().chapters().add(chapter1);
    assertEquals(1, controller.selectedBook().chapters().size());
    assertTrue(controller.selectedBook().chapters().contains(chapter1));
    assertEquals(chapter1, controller.selectedBook().chapters(0).get());

    assertTrue(controller.selectedBook().chapters(0).topic().isNotPresent());
    controller.selectedBook().chapters(0).topic().set("Some topic");
    assertTrue(controller.selectedBook().chapters(0).topic().isPresent());
    assertEquals("Some topic", controller.selectedBook().chapters(0).topic().get());

    controller.selectedBook().chapters().add(chapter2);
    assertEquals(2, controller.selectedBook().chapters().size());
    assertTrue(controller.selectedBook().chapters().contains(chapter2));

    // let controller point to null
    controller.selectedBook().set(null);
    assertTrue(controller.selectedBook().isNotPresent());
    assertTrue(controller.selectedBook().title().isNotPresent());
    assertTrue(controller.selectedBook().author().isNotPresent());
    assertTrue(controller.selectedBook().author().name().isNotPresent());
  }
}
