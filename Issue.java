import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Issue extends AbstractVerticle
{
    private Logger logger = LoggerFactory.getLogger(Issue.class);

    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new Issue());
    }

    @Override
    public void start() throws Exception
    {

        logger.info("outside for loop");

        for(int i = 0; i < 3; i++)
        {

            logger.info("inside for loop - before setPeriodic: {}", i);

            vertx.setPeriodic(10000, timerID -> {

                logger.info("inside setPeriodic - before executeBlocking");

                vertx.executeBlocking(f -> {

                    logger.info("inside executeBlocking <--------");

                    // doing some blocking code....
                    for(int j = 0; j < 99999; j++)
                    {
                        for(int k = 0; k < 999999; k++)
                        {
                            int a = 9;

                            a += 10;
                        }
                    }

                }, res -> logger.info(res.result().toString()));
            });

            logger.info("outside for loop - after setPeriodic: {}", i);

        }
    }
}