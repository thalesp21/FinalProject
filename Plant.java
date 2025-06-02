import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Plant {
    private String type;
    private int waterAge;

    public Plant(String plantType) {
        type = plantType;
    }

    public String getType() {
        return type;
    }

    public int getWaterAge() {
        return waterAge;
    }

    public void incDay() {
        waterAge++;
    }

    public int getWaterInterval() throws IOException {
        int i=0;
        while (!read("dict.csv",i,0).equals(type)&&!read("dict.csv",i,0).equals("end")) {
            //System.out.println(read("dict.csv",i,0));
            i++;
        }
        if (read("dict.csv",i,0).equals("end")) return Integer.MAX_VALUE;
        return Integer.parseInt(read("dict.csv",i,1));
    }

    public double getWaterAmt() throws IOException {
        int i=0;
        while (!read("dict.csv",i,0).equals(type)) {
            i++;
        }
        return Double.parseDouble(read("dict.csv",i,2));
    }

    public void water() {
        waterAge = 0;
    }

    public String toString() {
        return type + " (" + waterAge + ")";
    }

    private static String read(String path, int row, int col) throws IOException { //Returns the string at column and row of the CSV file at path
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        int r = 0;
        for (int i=0; line!=null&&i<=row; i++) {
            line = br.readLine();
            r = i;
        }
        String[] data = line.split(",");
        if (data[0].equals("end")) return "end";
        if (r==row) return data[col];
        return "end";
    }
}