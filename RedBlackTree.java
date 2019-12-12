import java.util.ArrayList;

public class RedBlackTree {

  // Tree's root
  static RedBlackNode rootNode;

  // Create node.
  public RedBlackNode createNode(Building data) {
    RedBlackNode node = new RedBlackNode(data, 'R');
    node.leftChild = new RedBlackNode(null, 'B');
    node.rightChild = new RedBlackNode(null, 'B');
    return node;
  }

  // Insertion
  public boolean insertNode(Building data) {
    RedBlackNode newNode = createNode(data);
    if (rootNode == null) {
      rootNode = newNode;
      rootNode.nodeColor = 'B';
      return true;
    }

    RedBlackNode tempNode = rootNode;
    while (tempNode != null) {
      if (tempNode.buildingNode.getBuildingNum() == data.getBuildingNum()) {
        return false;
      }
      if (tempNode.buildingNode.getBuildingNum() > data.getBuildingNum()) {
        if (tempNode.leftChild.buildingNode == null) {
          tempNode.leftChild = newNode;
          newNode.parentNode = tempNode;
          // balance tree
          balance(newNode);
          return true;
        }
        tempNode = tempNode.leftChild;
        continue;
      }
      if (tempNode.buildingNode.getBuildingNum() < data.getBuildingNum()) {
        if (tempNode.rightChild.buildingNode == null) {
          tempNode.rightChild = newNode;
          newNode.parentNode = tempNode;
          // balance tree
          balance(newNode);
          return true;
        }
        tempNode = tempNode.rightChild;
      }
    }
    return true;
  }

  // Balance tree after Insertion
  public static void balance(RedBlackNode node) {

    // if given node is root node.
    if (node.parentNode == null) {
      rootNode = node;
      rootNode.nodeColor = 'B';
      return;
    }

    // if node's parent color is black.
    if (node.parentNode.nodeColor == 'B') {
      return;
    }

    // get the node's parent's sibling node.
    RedBlackNode sibling = null;
    if (node.parentNode.parentNode.leftChild == node.parentNode) {
      sibling = node.parentNode.parentNode.rightChild;
    } else {
      sibling = node.parentNode.parentNode.leftChild;
    }

    // if sibling color is red.
    if (sibling.nodeColor == 'R') {
      node.parentNode.nodeColor = 'B';
      sibling.nodeColor = 'B';
      node.parentNode.parentNode.nodeColor = 'R';
      balance(node.parentNode.parentNode);
      return;
    }

    // if sibling color is black.
    else {
      if (node.parentNode.leftChild == node && node.parentNode.parentNode.leftChild == node.parentNode) {
        rightRotate(node.parentNode);
        balance(node.parentNode);
        return;
      }
      if (node.parentNode.rightChild == node && node.parentNode.parentNode.rightChild == node.parentNode) {
        rotateLeft(node.parentNode);
        balance(node.parentNode);
        return;
      }
      if (node.parentNode.rightChild == node && node.parentNode.parentNode.leftChild == node.parentNode) {
        rotateLeft(node);
        rightRotate(node);
        balance(node);
        return;
      }
      if (node.parentNode.leftChild == node && node.parentNode.parentNode.rightChild == node.parentNode) {
        rightRotate(node);
        rotateLeft(node);
        balance(node);
        return;
      }
    }
  }

  // Deletion
  public void deleteNode(Building data) {
    if (rootNode == null) {
      return;
    }

    // search for the given element in the tree.
    RedBlackNode temp = rootNode;
    while (temp.buildingNode != null) {
      if (temp.buildingNode == data) {
        break;
      }
      if (temp.buildingNode.getBuildingNum() >= data.getBuildingNum()) {
        temp = temp.leftChild;
        continue;
      }
      if (temp.buildingNode.getBuildingNum() < data.getBuildingNum()) {
        temp = temp.rightChild;
        continue;
      }
    }

    // if not found. then return.
    if (temp.buildingNode == null) {
      return;
    }

    // find the next greater number than the given data.
    RedBlackNode next = findNextNode(temp);

    // swap the data values of given node and next greater number.
    Building t = temp.buildingNode;
    temp.buildingNode = next.buildingNode;
    next.buildingNode = t;

    // remove the next node.
    RedBlackNode parent = next.parentNode;
    if (parent == null) {
      if (next.leftChild.buildingNode == null) {
        rootNode = null;
      } else {
        rootNode = next.leftChild;
        next.parentNode = null;
        rootNode.nodeColor = 'B';
      }
      return;
    }

    if (parent.rightChild == next) {
      parent.rightChild = next.leftChild;
    } else {
      parent.leftChild = next.leftChild;
    }
    next.leftChild.parentNode = parent;
    String color = Character.toString(next.leftChild.nodeColor) + Character.toString(next.nodeColor);
    // balance tree
    balance(next.leftChild, color);
  }

  // Balance tree after Deletion
  private static void balance(RedBlackNode node, String color) {
    // if node is Red-Black.
    if (node.parentNode == null || color.equals("BR") || color.equals("RB")) {
      node.nodeColor = 'B';
      return;
    }

    RedBlackNode parent = node.parentNode;

    // get sibling of the given node.
    RedBlackNode sibling = null;
    if (parent.leftChild == node) {
      sibling = parent.rightChild;
    } else {
      sibling = parent.leftChild;
    }

    // sibling's left child
    RedBlackNode leftSibling = sibling.leftChild;
    // sibling's right child
    RedBlackNode rightSibling = sibling.rightChild;

    if(leftSibling==null && rightSibling==null) {
      return;
    }

    // left sibling left and right siblings all are balck.
    if (sibling.nodeColor == 'B' && leftSibling.nodeColor == 'B' && rightSibling.nodeColor == 'B') {
      sibling.nodeColor = 'R';
      node.nodeColor = 'B';
      String c = Character.toString(parent.nodeColor) + Character.toString('B');
      balance(parent, c);
      return;
    }

    // sibling is red.
    if (sibling.nodeColor == 'R') {
      if (parent.rightChild == sibling) {
        rotateLeft(sibling);
      } else {
        rightRotate(sibling);
      }
      balance(node, color);
      return;
    }

    // sibling is red but one of its children are red.
    if(leftSibling==null) {
      return;
    }
    if (parent.leftChild == sibling) {
      if (leftSibling.nodeColor == 'R') {
        rightRotate(sibling);
        leftSibling.nodeColor = 'B';
      } else {
        rotateLeft(rightSibling);
        rightRotate(rightSibling);
        rightSibling.leftChild.nodeColor = 'B';
      }
      return;
    } else {
      if (rightSibling.nodeColor == 'R') {
        rotateLeft(sibling);
        rightSibling.nodeColor = 'B';
      } else {
        rightRotate(leftSibling);
        rotateLeft(leftSibling);
        leftSibling.rightChild.nodeColor = 'B';
      }
      return;
    }
  }

  //Search
  public Building search(RedBlackNode node, int buildingNumber) {
    if(node == null || node.buildingNode == null) {
      return null;
    }
    if(node.buildingNode.getBuildingNum()==buildingNumber) {
      return node.buildingNode;
    }else if(node.buildingNode.getBuildingNum()<buildingNumber) {
      return search(node.rightChild, buildingNumber);
    }else if(node.buildingNode.getBuildingNum()>buildingNumber) {
      return search(node.leftChild, buildingNumber);
    }
    return null;
  }

  //Search between Range of buildings
  public static ArrayList<Building> searchInRange(ArrayList<Building> list, RedBlackNode node, int startBuilding, int endBuilding){
    if(node==null || node.buildingNode == null) {
      return list;
    }
    if(isBetween(node.buildingNode.getBuildingNum(), startBuilding, endBuilding)) {
      searchInRange(list, node.leftChild, startBuilding, endBuilding);
      list.add(node.buildingNode);
      searchInRange(list, node.rightChild, startBuilding, endBuilding);
    }else if(node.buildingNode.getBuildingNum() >= startBuilding) {
      searchInRange(list, node.leftChild, startBuilding, endBuilding);
    }else if(node.buildingNode.getBuildingNum() <= startBuilding) {
      searchInRange(list, node.rightChild, startBuilding, endBuilding);
    }
    return list;
  }

  /**Helper Functions*/

  private static RedBlackNode findNextNode(RedBlackNode node) {
    RedBlackNode next = node.rightChild;
    if (next.buildingNode == null) {
      return node;
    }
    while (next.leftChild.buildingNode != null) {
      next = next.leftChild;
    }
    return next;
  }

  //Left Rotation
  private static void rotateLeft(RedBlackNode node) {
    RedBlackNode parentNode = node.parentNode;
    RedBlackNode leftChild = node.leftChild;
    node.leftChild = parentNode;
    parentNode.rightChild = leftChild;
    if (leftChild != null) {
      leftChild.parentNode = parentNode;
    }
    char color = parentNode.nodeColor;
    parentNode.nodeColor = node.nodeColor;
    node.nodeColor = color;
    RedBlackNode gp = parentNode.parentNode;
    parentNode.parentNode = node;
    node.parentNode = gp;

    if (gp == null) {
      rootNode = node;
      return;
    } else {
      if (gp.leftChild == parentNode) {
        gp.leftChild = node;
      } else {
        gp.rightChild = node;
      }
    }
  }

  //Right Rotation.
  private static void rightRotate(RedBlackNode node) {
    RedBlackNode parentNode = node.parentNode;
    RedBlackNode rightChild = node.rightChild;
    node.rightChild = parentNode;
    parentNode.leftChild = rightChild;
    if (rightChild != null) {
      rightChild.parentNode = parentNode;
    }
    char c = parentNode.nodeColor;
    parentNode.nodeColor = node.nodeColor;
    node.nodeColor = c;
    RedBlackNode gp = parentNode.parentNode;
    parentNode.parentNode = node;
    node.parentNode = gp;

    if (gp == null) {
      rootNode = node;
      return;
    } else {
      if (gp.leftChild == parentNode) {
        gp.leftChild = node;
      } else {
        gp.rightChild = node;
      }
    }
  }

  // function for PreOrder Traversal of the tree.
  private static void preOrder(RedBlackNode node) {
    if (node.buildingNode == null) {
      return;
    }
    System.out.println("("+node.buildingNode.getBuildingNum()+","+node.buildingNode.getExecutedTime()+","+node.buildingNode.getTotalTime()+")" + node.nodeColor + " ");
    preOrder(node.leftChild);
    preOrder(node.rightChild);
  }

  //Find if a building is between two buildings
  private static boolean isBetween(int buildingNum, int startBuilding, int endBuilding) {
    if(buildingNum >= startBuilding && buildingNum<=endBuilding)
      return true;
    return false;
  }

  //Check if root is empty
  private boolean isEmpty() {
    //Check if rootNode is null
    if (rootNode == null) {
      System.out.println("Red Black Tree is empty!");
      return true;
    }
    return false;
  }

  // Display tree
  public void displayRBTree() {
    if (!isEmpty()) {
      System.out.print("Pre-Order tree traversal:\n");
      preOrder(rootNode);
      System.out.println();
    }
  }
}

