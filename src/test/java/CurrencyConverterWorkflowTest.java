import io.temporal.client.WorkflowOptions;
import io.temporal.testing.TestWorkflowRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CurrencyConverterWorkflowTest {

    @Rule
    public TestWorkflowRule testWorkflowRule =
            TestWorkflowRule.newBuilder()
                    .setWorkflowTypes(CurrencyConverterImpl.class)
                    .setDoNotStart(true)
                    .build();

    @Test
    public void testcurrentRates() {
        testWorkflowRule.getWorker().registerActivitiesImplementations(new RateActivityImpl());
        testWorkflowRule.getTestEnvironment().start();

        CurrencyConverterWorkflow workflow =
                testWorkflowRule
                        .getWorkflowClient()
                        .newWorkflowStub(
                                CurrencyConverterWorkflow.class,
                                WorkflowOptions.newBuilder().setTaskQueue(testWorkflowRule.getTaskQueue()).build());
        double rate = workflow.currentRates();
        assertEquals(15, ((int) rate)) ;
        testWorkflowRule.getTestEnvironment().shutdown();
    }

    @Test
    public void testMockedGetGreeting() {
        RateActivity rateActivites = mock(RateActivity.class, withSettings().withoutAnnotations());
        when(rateActivites.currentUsdToEgpRate());
        testWorkflowRule.getWorker().registerActivitiesImplementations(rateActivites);
        testWorkflowRule.getTestEnvironment().start();

        CurrencyConverterWorkflow workflow =
                testWorkflowRule
                        .getWorkflowClient()
                        .newWorkflowStub(
                                CurrencyConverterWorkflow.class,
                                WorkflowOptions.newBuilder().setTaskQueue(testWorkflowRule.getTaskQueue()).build());
        double rate = workflow.currentRates();
        assertEquals(15, ((int) rate)) ;
        testWorkflowRule.getTestEnvironment().shutdown();
    }
}