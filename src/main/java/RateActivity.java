import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface RateActivity {

    @ActivityMethod
    double currentUsdToEgpRate();

    @ActivityMethod
    double EgpToUsdRate(double EGP);
}


