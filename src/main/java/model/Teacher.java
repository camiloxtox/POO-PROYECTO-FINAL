package model;

public class Teacher {
    private int id;
    private String name;
    private String email;
    private String subject; // Materia que enseña
    private int experienceYears; // Años de experiencia

    public Teacher() {}

    public Teacher(int id, String name, String email, String subject, int experienceYears) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.experienceYears = experienceYears;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }
}
