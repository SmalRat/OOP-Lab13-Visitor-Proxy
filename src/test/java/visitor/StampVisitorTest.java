package visitor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class StampVisitorTest {
    @Test
    public void testVisitor(){
        Group<Integer> nestedGroup = new Group<>();
        Signature<Integer> s1 = new Signature<>(System.out::println);
        nestedGroup.addTask(s1).addTask(new Signature<>(x -> System.out.println(x * x)));
        Group<Integer> group = new Group<>();
        Signature<Integer> s2 = new Signature<>(x -> System.out.println(x * x * x));
        group.addTask(nestedGroup).addTask(s2);
        group.apply_with_stamp(10, new StampVisitor<>(new LinkedList<String>()));

        Assertions.assertEquals(3, List.of(s1.getHeader("groups").split(",")).size());
        Assertions.assertEquals(2, List.of(s2.getHeader("groups").split(",")).size());
        Assertions.assertEquals(2, List.of(nestedGroup.getHeader("groups").split(",")).size());
        Assertions.assertEquals(1, List.of(group.getHeader("groups").split(",")).size());
    }
}
