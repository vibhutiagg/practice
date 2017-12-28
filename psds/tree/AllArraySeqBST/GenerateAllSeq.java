package psds.tree.AllArraySeqBST;

import java.util.ArrayList;
import java.util.List;


public class GenerateAllSeq {
  private List<List<Node>> sequences;

  public GenerateAllSeq(BinaryTree binaryTree) {
    sequences = new ArrayList<>();

    List<Node> ancestors = new ArrayList<>();
    List<Node> children = new ArrayList<>();

    generateSeq(ancestors, children, binaryTree.root);
  }

  public void generateSeq(List<Node> ancestors, List<Node> children, Node current){
    ancestors.add(current);

    if(current.left != null) {
      children.add(current.left);
    }

    if(current.right != null) {
      children.add(current.right);
    }

    if(children.isEmpty()){
      sequences.add(ancestors);
    }

    for(Node node : children) {
      List<Node> tempAncestors = clone(ancestors);
      List<Node> tempChildren = clone(children);

      tempChildren.remove(node);

      generateSeq(tempAncestors, tempChildren, node);
    }
  }

  public void printSeq() {
    for (List<Node> sequence : sequences){
      for(Node node : sequence) {
        System.out.print(node.data + "   ");
      }
      System.out.println();
    }
  }

  public List clone(List list1){
    List list2 = new ArrayList<>(list1.size());
    for(Object object : list1){
      list2.add(object);
    }
    return list2;
  }


  public static void main(String[] args) {
    BinaryTree binaryTree = new BinaryTree();
    binaryTree.root = new Node(20);
    binaryTree.root.left = new Node(10);
    binaryTree.root.right = new Node(30);
    binaryTree.root.right.left = new Node(25);
    binaryTree.root.right.right = new Node(35);

    new GenerateAllSeq(binaryTree).printSeq();
  }
}
