public class RedBlackNode {
    Building buildingNode;
    RedBlackNode leftChild;
    RedBlackNode rightChild;
    RedBlackNode parentNode;
    char nodeColor; // node color - 'R': red, 'B': black

    public RedBlackNode(Building data, char nodeColor) {
      this.buildingNode = data;
      this.leftChild = null;
      this.rightChild = null;
      this.parentNode = null;
      this.nodeColor = nodeColor;
    }
}
