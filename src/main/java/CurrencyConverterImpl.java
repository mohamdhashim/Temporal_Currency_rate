import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;

public class CurrencyConverterImpl implements CurrencyConverterWorkflow {
    ActivityOptions options = ActivityOptions.newBuilder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(2))
            .build();

    private final RateActivity rate = Workflow.newActivityStub(RateActivity.class, options);

    @Override
    public void currentRates() {
        double Rate = rate.currentUsdToEgpRate();
        rate.EgpToUsdRate(Rate);
    }

//    @Override
//    public double EGP_TO_USD(double rate) {
//        System.out.println("Kimitsu");
//        return 1/rate;
//    }
}
