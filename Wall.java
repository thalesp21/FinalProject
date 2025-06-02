import java.io.IOException;
import java.util.ArrayList;

public class Wall {
    private Plant[][] plantArray;
    private String name;

    public Wall(String wallName, int height, int width) {
        name = wallName;
        plantArray = new Plant[height][width];
    }

    public boolean populate(String plantType, int stRow, int endRow, int stCol, int endCol) { //Creates new plants of type plantType at every wall position within the range, inclusive
        boolean possible = true;
        for (int r=stRow;r<=endRow;r++) {
            for (int c=stCol;c<=endCol;c++) {
                if (plantArray[r][c]!=null) possible = false;
            }
        }
        if (possible) {
            for (int r=stRow;r<=endRow;r++) {
                for (int c=stCol;c<=endCol;c++) {
                    plantArray[r][c] = new Plant(plantType);
                }
            }
        }
        return possible;
    }

    public int[] findPlant(String type) {
        int[] coord = new int[2];
        for (int r=0; r<plantArray.length; r++) {
            for (int c=0; c<plantArray[0].length; c++) {
                if (plantArray[r][c].getType().equals(type)) {
                    coord[0] = r;
                    coord[1] = c;
                    return coord;
                }
            }
        }
        coord[0] = -1;
        coord[1] = -1;
        return coord;
    }

    public void incDay() { //Increments one day for the whole wall, updating water age for all plants
        for (int r=0;r<plantArray.length;r++) {
            for (int c=0;c<plantArray[r].length;c++) {
                plantArray[r][c].incDay();
            }
        }
    }

    public String toWater() throws IOException { //Returns a string describing the total water needed and the positions of all plants on the wall which need to be watered
        double totalWater = 0.0;
        String toRet = "";
        for (int r=0; r<plantArray.length; r++) {
            for (int c=0; c<plantArray[0].length; c++) {
                if (plantArray[r][c].getWaterAge()>=plantArray[r][c].getWaterInterval()) {
                    totalWater += plantArray[r][c].getWaterAmt();
                    toRet += "("+r+", "+c+"): "+plantArray[r][c].getWaterAmt()+"\n";
                }
            }
        }
        if (toRet.length()==0) return "Needs no water";
        return "Needs "+((double) Math.round(totalWater*100) / 100)+" liters\n"+toRet.substring(0,toRet.length()-1);
    }

    public ArrayList<int[]> needWater() throws IOException { //Returns an arraylist of coordinate pairs for all plants on the wall which need to be watered
        ArrayList<int[]> coords = new ArrayList<int[]>();
        for (int r=0; r<plantArray.length; r++) {
            for (int c=0; c<plantArray[0].length; c++) {
                if (plantArray[r][c].getWaterAge()>=plantArray[r][c].getWaterInterval()) {
                    int[] tempArray = new int[2];
                    tempArray[0] = r;
                    tempArray[1] = c;
                    coords.add(tempArray);
                }
            }
        }
        return coords;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return plantArray.length;
    }

    public int getWidth() {
        return plantArray[0].length;
    }

    public Plant getPlant(int row, int col) {
        return plantArray[row][col];
    }

    public String toString() {
        String ret = name+":\n";
        for (int r=0;r<plantArray.length;r++) {
            ret += "[";
            for (int c=0;c<plantArray[r].length;c++) {
                ret += plantArray[r][c]+", ";
            }
            ret = ret.substring(0,ret.length()-2) + "]\n";
        }
        return ret.substring(0,ret.length()-1);
    }
}