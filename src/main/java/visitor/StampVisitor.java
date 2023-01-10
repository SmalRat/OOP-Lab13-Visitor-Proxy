package visitor;

import java.util.LinkedList;
import java.util.List;

public class StampVisitor<T> implements Visitor<T>{
    private final LinkedList<String> groups;
    public StampVisitor (LinkedList<String> groups){
        this.groups = groups;
    }
    @Override
    public List<String> onGroupStart(Group group) {
        groups.add(group.getGroupUuid());
        return new LinkedList<>(groups);
    }

    @Override
    public List<String> onGroupEnd(Group group) {
        groups.pop();
        return new LinkedList<>(groups);
    }

    @Override
    public List<String> onTask(Task task) {
        task.setHeader("groups", String.join(", ", groups));
        return new LinkedList<>(groups);
    }

    @Override
    public List<String> onSignature(Signature<T> signature) {
        groups.add(signature.getId());
        this.onTask(signature);
        groups.pop();
        return new LinkedList<>(groups);
    }
}
