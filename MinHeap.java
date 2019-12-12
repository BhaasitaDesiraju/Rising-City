public class MinHeap {
  private Building[] buildings;
  private int size;
  private int maxsize;
  public static final int INITIAL = 1;

  public MinHeap(int maxsize) {
    this.size = 0;
    this.maxsize = maxsize;
    buildings = new Building[this.maxsize + 1];
    buildings[0] = new Building(Integer.MIN_VALUE, Integer.MIN_VALUE);
  }

  //Insertion
  public void insert(Building building) {
    if (size >= maxsize) {
      return;
    }
    buildings[++size] = building;
    int current = size;
  }

  //Remove Min
  public Building removeMin() {
    Building top = buildings[INITIAL];
    buildings[INITIAL] = buildings[size];
    buildings[size--] = null;
    minHeap();
    return top;
  }

  //Meld
  public void meldMinHeap(int pos) {

    // Node is a not a leaf node and it is greater than it's children
    if (!isLeaf(pos)) {

      if (buildings[getLeftChild(pos)] != null && (buildings[pos].getExecutedTime() > buildings[getLeftChild(pos)]
          .getExecutedTime())
          || (buildings[getRightChild(pos)] != null && (buildings[pos].getExecutedTime() > buildings[getRightChild(pos)]
          .getExecutedTime()))) {

        // Swap and meld with the left child
        if (buildings[getLeftChild(pos)] == null) {
          swapBuildings(pos, getRightChild(pos));
          meldMinHeap(getRightChild(pos));
        }
        else if (buildings[getRightChild(pos)] == null) {
          swapBuildings(pos, getLeftChild(pos));
          meldMinHeap(getLeftChild(pos));
        }
        else {
          if (buildings[getLeftChild(pos)].getExecutedTime() < buildings[getRightChild(pos)].getExecutedTime()) {
            swapBuildings(pos, getLeftChild(pos));
            meldMinHeap(getLeftChild(pos));
          }
          // Swap and meld with the right child
          else if (buildings[getLeftChild(pos)].getExecutedTime() > buildings[getRightChild(pos)].getExecutedTime()) {
            swapBuildings(pos, getRightChild(pos));
            meldMinHeap(getRightChild(pos));
          }
          else {
            int minPos = Math
                .min(buildings[getLeftChild(pos)].getBuildingNum(), buildings[getRightChild(pos)].getBuildingNum());
            if (buildings[getLeftChild(pos)].getBuildingNum() == minPos) {
              swapBuildings(pos, getLeftChild(pos));
              meldMinHeap(getLeftChild(pos));
            }
            else {
              swapBuildings(pos, getRightChild(pos));
              meldMinHeap(getRightChild(pos));
            }
          }
        }
      }
      else {
        if (buildings[getLeftChild(pos)] != null && buildings[pos].getExecutedTime() == buildings[getLeftChild(pos)]
            .getExecutedTime()) {
          if (buildings[pos].getBuildingNum() > buildings[getLeftChild(pos)].getBuildingNum()) {
            swapBuildings(pos, getLeftChild(pos));
            meldMinHeap(getLeftChild(pos));
          }
        }

        if (buildings[getRightChild(pos)] != null && buildings[pos].getExecutedTime() == buildings[getRightChild(pos)]
            .getExecutedTime()) {
          if (buildings[pos].getBuildingNum() > buildings[getRightChild(pos)].getBuildingNum()) {
            swapBuildings(pos, getRightChild(pos));
            meldMinHeap(getRightChild(pos));
          }
        }
      }
    }
  }

  //Build min heap
  public void minHeap() {
    for (int pos = (size / 2); pos >= 1; pos--) {
      meldMinHeap(pos);
    }
  }

  //Swap
  private void swapBuildings(int position1, int position2) {
    Building tmp;
    tmp = buildings[position1];
    buildings[position1] = buildings[position2];
    buildings[position2] = tmp;
  }

  //Helper Functions
//    Get Parent
  private int getParent(int position) {
    return position / 2;
  }

  // Get left child
  private int getLeftChild(int position) {
    return (2 * position);
  }

  // Get right child
  private int getRightChild(int position) {
    return (2 * position) + 1;
  }

  //Check if a node is a leaf node or not
  private boolean isLeaf(int pos) {
    if (2 * pos > size && 2 * pos + 1 > size && pos <= size) {
      return true;
    }
    return false;
  }


  //Return first building
  public Building getFirstBuilding() {
    return buildings[INITIAL];
  }

  //Return num of buildings
  public int getNumOfBuildings() {
    return size;
  }

  // Display the contents of the heap
  public void print() {
    for (int k = 1; k <= size - 1; k++) {
      System.out.print(buildings[k].getBuildingNum() + "," + buildings[k].getExecutedTime() + "," + buildings[k]
          .getTotalTime() + "   ");
    }
    System.out.println();
  }

}
