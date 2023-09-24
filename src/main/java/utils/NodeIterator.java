package utils;

import java.util.Iterator;

public class NodeIterator<N> implements Iterator<N> {
    public Node<N> currentNode;

    public NodeIterator(Node<N> node){
        currentNode = node;
    }

    public Node<N> name(){
        return currentNode;
    }

    @Override
    public boolean hasNext() {
        return currentNode!=null;
    }

    @Override
    public N next() {
        Node<N> temp = currentNode;
        currentNode =  currentNode.getNext();
        return temp.getContents();
    }
}