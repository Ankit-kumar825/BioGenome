package biogenome.model;

public class Researcher {

    private String researcherId;
    private String name;
    private String specialization;
    private String email;

    public Researcher(
            String researcherId,
            String name,
            String specialization,
            String email) {

        this.researcherId = researcherId;
        this.name = name;
        this.specialization = specialization;
        this.email = email;
    }

    public String getResearcherId() {
        return researcherId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Researcher ID: " + researcherId
                + "\nName: " + name
                + "\nSpecialization: " + specialization
                + "\nEmail: " + email;
    }
}