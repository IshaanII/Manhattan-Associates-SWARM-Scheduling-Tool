import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ManhattanProject {
    private ArrayList<Employee> seniorWMFunctional, juniorWMFunctional, wmTechnical, mps, componentTech, mawm, incidentCommander;

    public ManhattanProject(File swarmRoster) throws FileNotFoundException {
        seniorWMFunctional = new ArrayList<>();
        juniorWMFunctional = new ArrayList<>();
        wmTechnical = new ArrayList<>();
        mps = new ArrayList<>();
        componentTech = new ArrayList<>();
        mawm = new ArrayList<>();
        incidentCommander = new ArrayList<>();
        Scanner fileScan = new Scanner(swarmRoster);
        String line = null;
        String[] tokens = null;
        fileScan.nextLine();
        while (fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            tokens = line.split(",");
            ArrayList<LocalDate> vacation = new ArrayList<>();
            if (tokens.length > 13) {
                for (int i = 14; i < tokens.length; i++) {
                    vacation.add(LocalDate.parse(tokens[i]));
                }
                if (tokens[13].contains("S")) {
                    seniorWMFunctional.add(new Employee(tokens[0], tokens[13], vacation));
                }
                if (tokens[13].contains("J")) {
                    juniorWMFunctional.add(new Employee(tokens[0], tokens[13], vacation));
                }
                if (tokens[13].contains("T")) {
                    wmTechnical.add(new Employee(tokens[0], tokens[13], vacation));
                }
                if (tokens[13].contains("M")) {
                    mps.add(new Employee(tokens[0], tokens[13], vacation));
                }
                if (tokens[13].contains("C")) {
                    componentTech.add(new Employee(tokens[0], tokens[13], vacation));
                }
                if (tokens[13].contains("A")) {
                    mawm.add(new Employee(tokens[0], tokens[13], vacation));
                }
                if (tokens[13].contains("I")) {
                    incidentCommander.add(new Employee(tokens[0], tokens[13], vacation));
                }
            }
        }
    }

    public Employee[][] assign(int weeks) {
        Employee[][] schedule = new Employee[weeks][7];
        int random = 0;
        LocalDate today = LocalDate.parse("2022-07-11");
        for (int i = 0; i < weeks; i++) {
            random = (int) (Math.random() * seniorWMFunctional.size());
            while (seniorWMFunctional.get(random).getShifts() > averageShifts(seniorWMFunctional) ||
                    seniorWMFunctional.get(random).getVacation().contains(today)) {
                random = (int) (Math.random() * seniorWMFunctional.size());
            }
            incrementShifts(seniorWMFunctional.get(random).getRoles(), seniorWMFunctional.get(random).getName());
            schedule[i][0] = seniorWMFunctional.get(random);
            random = (int) (Math.random() * juniorWMFunctional.size());
            while (juniorWMFunctional.get(random).getShifts() > averageShifts(juniorWMFunctional) ||
                    juniorWMFunctional.get(random).getVacation().contains(today)) {
                random = (int) (Math.random() * juniorWMFunctional.size());
            }
            incrementShifts(juniorWMFunctional.get(random).getRoles(), juniorWMFunctional.get(random).getName());
            schedule[i][1] = juniorWMFunctional.get(random);
            random = (int) (Math.random() * wmTechnical.size());
            while (wmTechnical.get(random).getShifts() > averageShifts(wmTechnical) ||
                    wmTechnical.get(random).getVacation().contains(today)) {
                random = (int) (Math.random() * wmTechnical.size());
            }
            incrementShifts(wmTechnical.get(random).getRoles(), wmTechnical.get(random).getName());
            schedule[i][2] = wmTechnical.get(random);
            random = (int) (Math.random() * mps.size());
            while (mps.get(random).getShifts() > averageShifts(mps) || mps.get(random).getVacation().contains(today)) {
                random = (int) (Math.random() * mps.size());
            }
            incrementShifts(mps.get(random).getRoles(), mps.get(random).getName());
            schedule[i][3] = mps.get(random);
            random = (int) (Math.random() * componentTech.size());
            while (componentTech.get(random).getShifts() > averageShifts(componentTech) ||
                    componentTech.get(random).getVacation().contains(today)) {
                random = (int) (Math.random() * componentTech.size());
            }
            incrementShifts(componentTech.get(random).getRoles(), componentTech.get(random).getName());
            schedule[i][4] = componentTech.get(random);
            random = (int) (Math.random() * mawm.size());
            while (mawm.get(random).getShifts() > averageShifts(mawm) ||
                    mawm.get(random).getVacation().contains(today)) {
                random = (int) (Math.random() * componentTech.size());
            }
            incrementShifts(mawm.get(random).getRoles(), mawm.get(random).getName());
            schedule[i][5] = mawm.get(random);
            random = (int) (Math.random() * incidentCommander.size());
            while (incidentCommander.get(random).getShifts() > averageShifts(incidentCommander) ||
                    incidentCommander.get(random).getVacation().contains(today)) {
                random = (int) (Math.random() * incidentCommander.size());
            }
            incrementShifts(incidentCommander.get(random).getRoles(), incidentCommander.get(random).getName());
            schedule[i][6] = incidentCommander.get(random);
            today = today.plusWeeks(1);
        }
        return schedule;
    }

    private double averageShifts(ArrayList<Employee> employees) {
        double totalShifts = 0.0;
        for (Employee employee : employees) {
            totalShifts += employee.getShifts();
        }
        return totalShifts / employees.size();
    }

    private void incrementShifts(String role, String name) {
        if (role.contains("S")) {
            for (int i = 0; i < seniorWMFunctional.size(); i++) {
                if (seniorWMFunctional.get(i).getName().equals(name)) {
                    seniorWMFunctional.get(i).setShifts(seniorWMFunctional.get(i).getShifts() + 1);
                }
            }
        }
        if (role.contains("J")) {
            for (int i = 0; i < juniorWMFunctional.size(); i++) {
                if (juniorWMFunctional.get(i).getName().equals(name)) {
                    juniorWMFunctional.get(i).setShifts(juniorWMFunctional.get(i).getShifts() + 1);
                }
            }
        }
        if (role.contains("T")) {
            for (int i = 0; i < wmTechnical.size(); i++) {
                if (wmTechnical.get(i).getName().equals(name)) {
                    wmTechnical.get(i).setShifts(wmTechnical.get(i).getShifts() + 1);
                }
            }
        }
        if (role.contains("M")) {
            for (int i = 0; i < mps.size(); i++) {
                if (mps.get(i).getName().equals(name)) {
                    mps.get(i).setShifts(mps.get(i).getShifts() + 1);
                }
            }
        }
        if (role.contains("C")) {
            for (int i = 0; i < componentTech.size(); i++) {
                if (componentTech.get(i).getName().equals(name)) {
                    componentTech.get(i).setShifts(componentTech.get(i).getShifts() + 1);
                }
            }
        }
        if (role.contains("A")) {
            for (int i = 0; i < mawm.size(); i++) {
                if (mawm.get(i).getName().equals(name)) {
                    mawm.get(i).setShifts(mawm.get(i).getShifts() + 1);
                }
            }
        }
        if (role.contains("I")) {
            for (int i = 0; i < incidentCommander.size(); i++) {
                if (incidentCommander.get(i).getName().equals(name)) {
                    incidentCommander.get(i).setShifts(incidentCommander.get(i).getShifts() + 1);
                }
            }
        }
    }

    public void createFile(Employee[][] schedule, String scheduleDestination) throws FileNotFoundException {
        File fileOut = new File(scheduleDestination);
        PrintWriter filePrint = new PrintWriter(fileOut);
        LocalDate today = LocalDate.parse("2022-07-11");
        for (int i = 0; i < schedule.length; i++) {
            int week = i + 1;
            filePrint.print(today + ":,");
            for (int j = 0; j < schedule[i].length; j++) {
                if (j < 6) {
                    filePrint.print(schedule[i][j].getName() + ", ");
                } else {
                    filePrint.print(schedule[i][j].getName() + "\n");
                }
            }
            today = today.plusWeeks(1);
        }
        filePrint.close();
    }
    @Override
    public String toString() {
        return "seniorWMFunctional=" + seniorWMFunctional + "\n" +
                "juniorWMFunctional=" + juniorWMFunctional + "\n" +
                "wmTechnical=" + wmTechnical + "\n" +
                "mps=" + mps + "\n" +
                "componentTech=" + componentTech + "\n" +
                "mawm=" + mawm + "\n" +
                "incidentCommander=" + incidentCommander;
    }
}