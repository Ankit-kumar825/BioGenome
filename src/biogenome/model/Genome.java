package biogenome.model;

public class Genome {

    private String genomeId;
    private String species;
    private String sequence;
    private String description;

    public Genome(String genomeId, String species, String sequence, String description) {
        this.genomeId = genomeId;
        this.species = species;
        this.sequence = sequence;
        this.description = description;
    }

    public String getGenomeId() {
        return genomeId;
    }

    public String getSpecies() {
        return species;
    }

    public String getSequence() {
        return sequence;
    }

    public String getDescription() {
        return description;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return sequence.length();
    }

    @Override
    public String toString() {
        return "Genome ID: " + genomeId +
               "\nSpecies: " + species +
               "\nSequence Length: " + sequence.length() +
               "\nDescription: " + description;
    }
}