package biogenome.database;

import java.util.ArrayList;
import biogenome.model.Genome;

public class GenomeManager {

    private ArrayList<Genome> genomes;

    public GenomeManager() {
        genomes = new ArrayList<>();
    }

    public boolean addGenome(Genome genome) {

        if (findGenome(genome.getGenomeId()) != null) {
            return false;
        }

        genomes.add(genome);
        return true;
    }

    public ArrayList<Genome> getAllGenomes() {
        return genomes;
    }

    public Genome findGenome(String genomeId) {

        for (Genome genome : genomes) {

            if (genome.getGenomeId().equalsIgnoreCase(genomeId)) {
                return genome;
            }
        }

        return null;
    }

    public boolean deleteGenome(String genomeId) {

        Genome genome = findGenome(genomeId);

        if (genome != null) {
            genomes.remove(genome);
            return true;
        }

        return false;
    }

    public boolean updateGenome(
            String genomeId,
            String species,
            String sequence,
            String description) {

        Genome genome = findGenome(genomeId);

        if (genome == null) {
            return false;
        }

        genome.setSpecies(species);
        genome.setSequence(sequence);
        genome.setDescription(description);

        return true;
    }

    public int getGenomeCount() {
        return genomes.size();
    }
}