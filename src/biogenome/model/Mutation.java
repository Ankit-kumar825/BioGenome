package biogenome.model;

public class Mutation {

    private String mutationId;
    private String genomeId;
    private int position;
    private char originalNucleotide;
    private char mutatedNucleotide;
    private String mutationType;

    public Mutation(
            String mutationId,
            String genomeId,
            int position,
            char originalNucleotide,
            char mutatedNucleotide,
            String mutationType) {

        this.mutationId = mutationId;
        this.genomeId = genomeId;
        this.position = position;
        this.originalNucleotide = originalNucleotide;
        this.mutatedNucleotide = mutatedNucleotide;
        this.mutationType = mutationType;
    }

    public String getMutationId() {
        return mutationId;
    }

    public String getGenomeId() {
        return genomeId;
    }

    public int getPosition() {
        return position;
    }

    public char getOriginalNucleotide() {
        return originalNucleotide;
    }

    public char getMutatedNucleotide() {
        return mutatedNucleotide;
    }

    public String getMutationType() {
        return mutationType;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setMutatedNucleotide(char mutatedNucleotide) {
        this.mutatedNucleotide = mutatedNucleotide;
    }

    public void setMutationType(String mutationType) {
        this.mutationType = mutationType;
    }

    @Override
    public String toString() {
        return "Mutation ID: " + mutationId
                + "\nGenome ID: " + genomeId
                + "\nPosition: " + position
                + "\nOriginal Nucleotide: " + originalNucleotide
                + "\nMutated Nucleotide: " + mutatedNucleotide
                + "\nMutation Type: " + mutationType;
    }
}