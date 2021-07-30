package java8.wxf.dataStructure;

public class BinaryTree {

    private Node rootNode;

    public BinaryTree () {

    }
    public BinaryTree (Object data) {
        rootNode = new Node(data);
    }


    private class Node {
        Node left;
        Node right;
        Object data;
        public Node () {
            this(0);
        }
        public Node (Object data) {
            this.data = data;
        }
    }

    public Node add (Object data) throws Exception {
        Node node = new Node(data);

       return this.add(rootNode, data, true);
    }
    public Node add (Node parent, Object data, Boolean isLeft) throws Exception {
        Node node = new Node(data);
        if (null == parent) {
           rootNode.data = data;
           return rootNode;
        }
        if (null == parent.left && isLeft) {
            parent.left = node;
        } else if (null == parent.right && !isLeft) {
            parent.right = node;
        } else {
            throw new Exception("节点不能添加");
        }
        return node;
    }

}
