public class MinHeapTest {
  // Driver code
  public static void main(String[] arg)
  {
    System.out.println("The Min Heap is:");
    MinHeap buildingHeap = new MinHeap(2000);
    buildingHeap.insert(new Building(5,3));
    buildingHeap.insert(new Building(3,5));
    buildingHeap.insert(new Building(17, 10));
    buildingHeap.insert(new Building(10, 7));
    buildingHeap.insert(new Building(84, 12));
    buildingHeap.insert(new Building(12, 19));
    buildingHeap.insert(new Building(6, 1));
    buildingHeap.insert(new Building(22, 8));
    buildingHeap.insert(new Building(9, 6));
    buildingHeap.insert(new Building(13, 5));
    buildingHeap.minHeap();
    buildingHeap.print();
    Building minVal = buildingHeap.removeMin();
    System.out.println("The Min val is " + minVal.getBuildingNum()+ ","+minVal.getExecutedTime()+","+minVal.getTotalTime());
    buildingHeap.print();
  }
}

