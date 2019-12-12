import java.util.ArrayList;

public class RisingCity {

  private static Building underConstruction;
  private static long workingTime = 0;
  private static int pos = 0;

  /** Helper functions */
  private static void workOnBuildings(ArrayList<Integer> days, ArrayList<String> operation, MinHeap minHeap, RedBlackTree redBlackTree) {
    if((pos < days.size()) && workingTime == days.get(pos)) {
      String op = operation.get(pos);
      if (op.contains("Insert")) {
        String parseTokens = op.substring(op.indexOf("(") + 1, op.indexOf(")"));
        String[] remainingTokens = parseTokens.split(",");
        Building building = new Building(Integer.parseInt(remainingTokens[0]), Integer.parseInt(remainingTokens[1]));
        //Insert Building into min heap and RBT
        if (!isUpdateSuccessful(building, minHeap, redBlackTree)) {
          return;
        }
      } else if (op.contains("PrintBuilding")) {
          String parseBuildings = op.substring(op.indexOf("(") + 1, op.indexOf(")"));
          if (parseBuildings.contains(",")) {
              String[] remainingBuildingTokens = parseBuildings.split(",");
            int limit1 = Integer.parseInt(remainingBuildingTokens[0]);
            int limit2 = Integer.parseInt(remainingBuildingTokens[1]);
            ArrayList<Building> buildingList = RedBlackTree
                .searchInRange(new ArrayList<>(), RedBlackTree.rootNode, limit1, limit2);
            if (buildingList.isEmpty()) {
              System.out.println("(0,0,0)");
            }
            else {
              StringBuilder sb = new StringBuilder();
              for (Building each : buildingList) {
                sb.append("(");
                sb.append(each.getBuildingNum());
                sb.append(",");
                sb.append(each.getExecutedTime());
                sb.append(",");
                sb.append(each.getTotalTime());
                sb.append(")");
                sb.append(",");
              }
              System.out.println(sb.substring(0, sb.length() - 1));
            }
          }
          else {
            Building building1 = redBlackTree.search(RedBlackTree.rootNode, Integer.parseInt(parseBuildings));
            if(building1 == null) {
              System.out.println("(0,0,0)");
            }
            else {
              System.out.println("(" + building1.getBuildingNum() + "," + building1.getExecutedTime() + "," + building1.getTotalTime()+")");
            }
          }
        }
        pos++;
    }
  }

  //Update MinHeap and Red black Tree
  private static boolean isUpdateSuccessful(Building building, MinHeap minHeap, RedBlackTree redBlackTree) {
    if(!redBlackTree.insertNode(building)) {
      return false;
    }
    minHeap.insert(building);
    return true;
  }


  private static void printOutput(Building building, long days) {
    System.out.println("(" + building.getBuildingNum() + "," + days + ")");
  }

  public static void main(String[] args) {

      final String FILENAME = "input.txt";
//    final String FILENAME = "input2.txt";
//    final String FILENAME = "input3.txt";

    MinHeap minHeap = new MinHeap(2000);
    RedBlackTree redBlackTree = new RedBlackTree();
    int numOfDays = 0;
    boolean flag = false;
    try {
      InputFileParser inputFileParser = new InputFileParser(FILENAME);
      ArrayList<Integer> days = inputFileParser.getDays();
      ArrayList<String> operation = inputFileParser.getOperations();
      while(true) {
        if(flag && minHeap.getNumOfBuildings() == 0 && pos >= days.size()) {
          break;
        }

        if(!flag) {
          workOnBuildings(days, operation, minHeap, redBlackTree);
          flag = true;
          underConstruction = minHeap.getFirstBuilding();
          numOfDays = 0;
        } else {
          workOnBuildings(days, operation, minHeap, redBlackTree);
          if(minHeap.getNumOfBuildings() == 1) {
            underConstruction = minHeap.getFirstBuilding();
          }
        }
        workingTime++;
        if(numOfDays == 0) {
          minHeap.minHeap();
          underConstruction = minHeap.getFirstBuilding();
        }
        if(underConstruction != null) {
          numOfDays++;
          underConstruction.setExecutedTime(underConstruction.getExecutedTime()+1);
          if(underConstruction.getExecutedTime() == underConstruction.getTotalTime()) {
            printOutput(underConstruction, workingTime);
            minHeap.removeMin();
            redBlackTree.deleteNode(underConstruction);
            minHeap.minHeap();
            underConstruction = minHeap.getFirstBuilding();
            numOfDays = 0;
          }
        }
        if(numOfDays == 5) {
          minHeap.minHeap();
          underConstruction = minHeap.getFirstBuilding();
          numOfDays = 0;
        }
      }
    } catch(Exception e) {
        e.printStackTrace();
    }
  }
}
