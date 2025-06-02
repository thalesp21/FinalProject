import java.util.ArrayList;

public class AutoWall extends Wall {

    public AutoWall(String wallName, int height, int width) {
        super(wallName, height, width);
    }

    public void incDay() { //Since this is an automatically watering wall, all plants are reset to zero daily so that they do not appear as needing to be watered
        for (int r=0;r<getHeight();r++) {
            for (int c=0;c<getWidth();c++) {
                getPlant(r,c).water();
            }
        }
    }

    public String toWater() {
        return "Automatic wall";
    }

    public String toString() {
        return "(Auto Wall) "+super.toString();
    }
}