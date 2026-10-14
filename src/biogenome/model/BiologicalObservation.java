package biogenome.model;

public class BiologicalObservation {

    private String observationId;
    private String genomeId;
    private String observationType;
    private String result;
    private String date;

    public BiologicalObservation(
            String observationId,
            String genomeId,
            String observationType,
            String result,
            String date) {

        this.observationId = observationId;
        this.genomeId = genomeId;
        this.observationType = observationType;
        this.result = result;
        this.date = date;
    }

    public String getObservationId() {
        return observationId;
    }

    public String getGenomeId() {
        return genomeId;
    }

    public String getObservationType() {
        return observationType;
    }

    public String getResult() {
        return result;
    }

    public String getDate() {
        return date;
    }

    public void setObservationType(String observationType) {
        this.observationType = observationType;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Observation ID: " + observationId
                + "\nGenome ID: " + genomeId
                + "\nObservation Type: " + observationType
                + "\nResult: " + result
                + "\nDate: " + date;
    }
}