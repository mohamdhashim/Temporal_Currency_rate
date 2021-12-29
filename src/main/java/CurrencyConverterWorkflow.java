import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface CurrencyConverterWorkflow {

    @WorkflowMethod
    void currentRates();

}
