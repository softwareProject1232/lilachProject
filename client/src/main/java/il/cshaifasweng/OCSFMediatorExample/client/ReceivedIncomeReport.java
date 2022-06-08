package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.HistogramData;
import il.cshaifasweng.OCSFMediatorExample.entities.IncomeHistogramData;

public class ReceivedIncomeReport {
    private IncomeHistogramData histogramData;

    public IncomeHistogramData getData() {
        return histogramData;
    }

    public ReceivedIncomeReport(IncomeHistogramData branchNames) {
        this.histogramData = branchNames;
    }
}
