package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.BranchNameData;
import il.cshaifasweng.OCSFMediatorExample.entities.HistogramData;

public class ReceivedComplaintsReport {
    private HistogramData histogramData;

    public HistogramData getData() {
        return histogramData;
    }

    public ReceivedComplaintsReport(HistogramData branchNames) {
        this.histogramData = branchNames;
    }
}
