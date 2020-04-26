package com.iacweb.iacweb;

import com.iacweb.iacweb.controller.CategoryControllerTest;
import com.iacweb.iacweb.controller.CustomerControllerTest;
import com.iacweb.iacweb.controller.DiscountControllerTest;
import com.iacweb.iacweb.controller.ProductControllerTest;
import org.junit.internal.TextListener;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.Result;

@SpringBootTest
class IacwebApplicationTests {

    @Test
    void contextLoads() {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));

        Result result = junit.run(
                CategoryControllerTest.class,
                CustomerControllerTest.class,
                DiscountControllerTest.class,
                ProductControllerTest.class);

        resultReport(result);
    }

    public static void resultReport(Result result) {
        System.out.println("Finished. Result: Failures: " +
                result.getFailureCount() + ". Ignored: " +
                result.getIgnoreCount() + ". Tests run: " +
                result.getRunCount() + ". Time: " +
                result.getRunTime() + "ms.");
    }
}
