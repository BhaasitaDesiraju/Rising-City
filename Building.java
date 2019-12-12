public class Building {

  // buildingNum: unique integer identifier for each building.
  private int buildingNum;
  // executed_time: total number of days spent so far on this building.
  private int executedTime;
  // total_time: the total number of days needed to complete the construction of the building
  private int totalTime;

  public Building(int buildingNum, int totalTime) {
    this.buildingNum = buildingNum;
    this.executedTime = 0;
    this.totalTime = totalTime;
  }

  public int getBuildingNum() {
    return buildingNum;
  }

  public void setBuildingNum(int buildingNum) {
    this.buildingNum = buildingNum;
  }

  public int getExecutedTime() {
    return executedTime;
  }

  public void setExecutedTime(int executedTime) {
    this.executedTime = executedTime;
  }

  public int getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(int totalTime) {
    this.totalTime = totalTime;
  }

}
