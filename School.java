import java.io.IOException;
import java.util.ArrayList;

public class School {
    private ArrayList<Wall> walls;

    public School() {
        walls = new ArrayList<Wall>();
    }

    public void addWall(Wall toAdd) {
        walls.add(toAdd);
    }

    public void incDay() { //Increments one day for the whole school, updating water age for all plants
        for (int i=0; i<walls.size(); i++) {
            walls.get(i).incDay();
        }
    }

    public String toWater() throws IOException { //Returns a string describing the total and amount of water needed for each wall in the school
        String toRet = "";
        double totalWater = 0.0;
        for (int i=0; i<walls.size(); i++) {
            toRet += walls.get(i).getName() + ":\n" + walls.get(i).toWater() + "\n";
            if (!walls.get(i).toWater().equals("Needs no water")&&!walls.get(i).toWater().equals("Automatic wall")) {
                String[] temp = walls.get(i).toWater().split(" ");
                totalWater += Double.parseDouble(temp[1]);
            }
        }
        return "=== Total water needed: "+((double) Math.round(totalWater*100) / 100)+" liters ===\n"+toRet.substring(0,toRet.length()-1);
    }

    public void waterAll() throws IOException { //Resets water age for all plants which need to be watered today
        for (int i=0; i<walls.size(); i++) {
            ArrayList<int[]> needWater = walls.get(i).needWater();
            for (int j=0; j<needWater.size(); j++) {
                walls.get(i).getPlant(needWater.get(j)[0], needWater.get(j)[1]).water();
            }
        }
    }

    public String alphPlantList() { //Returns a string describing every type of plant in the school and its quantity, in alphabetical order by plant type
        ArrayList<String> allPlants = new ArrayList<String>(); //one element for each plant, unsorted
        for (int i=0; i<walls.size(); i++) {
            for (int r=0; r<walls.get(i).getHeight(); r++) {
                for (int c=0; c<walls.get(i).getWidth(); c++) {
                    allPlants.add(walls.get(i).getPlant(r,c).getType());
                }
            }
        }

        ArrayList<String> plantTypes = new ArrayList<String>(); //one element for each type of plant, unsorted
        for (int i=0; i<allPlants.size(); i++) {
            String toCheck = allPlants.get(i);
            boolean exists = false;
            for (int j=0; j<plantTypes.size(); j++) {
                if (toCheck.equals(plantTypes.get(j))) exists = true;
            }
            if (!exists) {
                plantTypes.add(allPlants.get(i));
            }
        }

        ArrayList<String> plantNos = new ArrayList<String>(); //one element for each type of plant including the number of plants of that type, unsorted
        for (int i=0; i<plantTypes.size(); i++) {
            int count = 0;
            for (int j=0; j<allPlants.size(); j++) {
                if (allPlants.get(j).equals(plantTypes.get(i))) count++;
            }
            plantNos.add(plantTypes.get((i))+": "+count);
        }

        ArrayList<String> sorted = selectionSort(plantNos); //same as plantNos but sorted

        String toRet = "";
        for (int i=0; i<sorted.size(); i++) {
            toRet += sorted.get(i)+"\n";
        }
        return toRet.substring(0,toRet.length()-1);
    }

    private ArrayList<String> selectionSort(ArrayList<String> toSort) {
        for (int i=0; i<toSort.size()-1; i++) {
            int min = i;
            for (int j=i+1; j<toSort.size(); j++) {
                if (toSort.get(j).compareTo(toSort.get(min))<0) {
                    min = j;
                }
            }
            String temp = toSort.get(i);
            toSort.set(i,toSort.get(min));
            toSort.set(min,temp);
        }
        return toSort;
    }
}