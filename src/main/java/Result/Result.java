package Result;

import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;


/**
 * Result Class
 * Class to process the result given by AnalysisResults from Watson to text, and extract the content.
 */
public class Result {
    private AnalysisResults results;

    public Result(AnalysisResults results) {
        this.results = results;
      }

    public AnalysisResults getResult() {
        return this.results;
    }

}
