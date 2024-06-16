package entities;

/**
 *
 * @author andressa
 */
public class Doctor extends User {

    private String crm;
    private String specialty;
    private boolean medScheduling;
    private boolean exam;

    public String getCRM() {
        return crm;
    }

    public boolean getMedScheduling() {
        return medScheduling;
    }

    public boolean getExam() {
        return exam;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setCRM(String crm) {
        this.crm = crm;
    }

    public void setMedScheduling(Boolean medScheduling) {
        this.medScheduling = medScheduling;
    }

    public void setExam(Boolean exam) {
        this.exam = exam;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

}
