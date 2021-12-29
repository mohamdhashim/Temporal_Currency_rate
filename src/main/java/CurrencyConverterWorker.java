import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;


public class CurrencyConverterWorker {
    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(Shared.Currency_Converter_TASK_QUEUE);

        worker.registerWorkflowImplementationTypes(CurrencyConverterImpl.class);

        worker.registerActivitiesImplementations(new RateActivityImpl());

        // Start polling the Task Queue.
        factory.start();
    }
}
