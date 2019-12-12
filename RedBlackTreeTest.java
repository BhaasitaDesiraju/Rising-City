public class RedBlackTreeTest {
  // main function to run the program.
  public static void main(String[] args) {
    RedBlackTree rbt = new RedBlackTree();
    Building building = new Building(2, 5);
    rbt.insertNode(new Building(5, 4));
    rbt.insertNode(building);
    rbt.insertNode(new Building(7, 10));
    rbt.insertNode(new Building(1, 7));
    Building building1 = new Building(8, 12);
    rbt.insertNode(building1);
    rbt.insertNode(new Building(12, 9));
    rbt.insertNode(new Building(6, 1));
    rbt.insertNode(new Building(22, 8));
    rbt.insertNode(new Building(9, 6));
    rbt.insertNode(new Building(13, 5));
    rbt.displayRBTree();
    RedBlackNode node = new RedBlackNode(building, 'B');
    System.out.println(rbt.search(node, 2).getBuildingNum());
    System.out.println("Remove");
    rbt.deleteNode(building1);
    rbt.displayRBTree();
  }
}
