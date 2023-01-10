package visitor;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Group<Integer> nestedGroup = new Group<>();
        nestedGroup.addTask(new Signature<>(System.out::println)).addTask(new Signature<>(x -> System.out.println(x * x)));
        Group<Integer> group = new Group<>();
        group.addTask(nestedGroup).addTask(new Signature<>(x -> System.out.println(x * x * x)));
        group.apply_with_stamp(10, new StampVisitor<>(new LinkedList<String>()));
        System.out.println(group.getHeader("groups"));
    }
}
