package biogenome.model;

public class Experiment {

    private String experimentId;
    private String experimentName;
    private String researcherId;
    private String genomeId;
    private String status;

    public Experiment(
            String experimentId,
            String experimentName,
            String researcherId,
            String genomeId,
            String status) {

        this.experimentId = experimentId;
        this.experimentName = experimentName;
        this.researcherId = researcherId;
        this.genomeId = genomeId;
        this.status = status;
    }

    public String getExperimentId() {
        return experimentId;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public String getResearcherId() {
        return researcherId;
    }

    public String getGenomeId() {
        return genomeId;
    }

    public String getStatus() {
        return status;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Experiment ID: " + experimentId
                + "\nExperiment Name: " + experimentName
                + "\nResearcher ID: " + researcherId
                + "\nGenome ID"