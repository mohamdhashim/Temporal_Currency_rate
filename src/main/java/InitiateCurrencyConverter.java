import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class InitiateCurrencyConverter {

    public static void main(String[] args) throws Exception {
        System.out.println("here");

        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Shared.Currency_Converter_TASK_QUEUE)
                .build();
        CurrencyConverterWorkflow workflow = client.newWorkflowStub(CurrencyConverterWorkflow.class, options);
        CurrencyConverterWorkflow workflow2 = client.newWorkflowStub(CurrencyConverterWorkflow.class, options);

        // Synchronously execute the Workflow and wait for the response.
        workflow.currentRates();
        System.out.println("done");
        System.exit(0);
    }
}