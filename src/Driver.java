import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        ManhattanProject test = new ManhattanProject(new File("D:\\Manhattan Project CSV Version\\SWARM Roster.csv"));
        Employee[][] schedule = test.assign(13);
        for (Employee[] week : schedule) {
            System.out.println(Arrays.toString(week));
        }
        test.createFile(schedule, "schedule.csv");
    }
}
