package visitor;

import java.util.List;

public interface Visitor<T> {
    List<String> onGroupStart(Group<T> group);
    List<String> onGroupEnd(Group<T> group);
    List<String> onTask(Task<T> task);
    
}
