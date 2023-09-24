package utils;

import java.util.Objects;

public class Node<N>{

    public Node<N> next;

    public N contents;

    public Node(Node<N> next, N contents) {
        setNext(next);
        setContents(contents);
    }

    public String getString(){
        return contents.toString();
    }

    public Node<N> getNext() {
        return next;
    }

    public void setNext(Node<N> next) {
        this.next = next;
    }

    public N getContents() {
        return contents;
    }

    public void setContents(N contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(next, node.next) && Objects.equals(contents, node.contents);
    }
}
