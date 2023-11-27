import java.time.LocalDate;
import java.util.ArrayList;

public class Employee {
    private String name;
    private int shifts;
    private String roles;
    private ArrayList<LocalDate> vacation;

    public Employee(String name, String roles, ArrayList<LocalDate> vacation) {
        this.name = name;
        shifts = 0;
        this.roles = roles;
        this.vacation = vacation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShifts() {
        return shifts;
    }

    public void setShifts(int shifts) {
        this.shifts = shifts;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public ArrayList<LocalDate> getVacation() {
        return vacation;
    }

    public void setVacation(ArrayList<LocalDate> vacation) {
        this.vacation = vacation;
    }

    @Override
    public String toString() {
        return name + "(" + roles + ")" + "(" + shifts + ")";
    }
}
