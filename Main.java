import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException {

        Wall wall1 = new Wall("Third floor north side", 3, 3);
        wall1.populate("Pothos", 0, 1, 0, 2);
        wall1.populate("Ivy", 2, 2, 0, 1);
        wall1.populate("Fern", 2, 2, 2, 2);
        System.out.println(wall1);
        System.out.println();
        Thread.sleep(8000);

        Wall wall2 = new AutoWall("Second floor east side", 2, 2);
        wall2.populate("Pothos", 0, 0, 0, 1);
        wall2.populate("Philodendron", 1, 1, 0, 1);
        System.out.println(wall2);
        System.out.println();
        Thread.sleep(8000);

        String toFind = "Philodendron";
        int[] tempArray = wall2.findPlant(toFind);
        System.out.println(toFind+" found in "+wall2.getName()+" at ("+tempArray[0]+", "+tempArray[1]+")");
        System.out.println();
        Thread.sleep(8000);

        School mySchool = new School();
        mySchool.addWall(wall1);
        mySchool.addWall(wall2);
        System.out.println(mySchool.alphPlantList());
        Thread.sleep(8000);

        for (int i=0; i<Integer.MAX_VALUE; i++) {
            mySchool.incDay();
            System.out.println("\n============================== Day "+i+" ==============================");
            System.out.println(wall1);
            System.out.println(wall2);
            System.out.println(mySchool.toWater());
            Thread.sleep(8000);
            mySchool.waterAll();
        }
    }
}