package com.iacweb.iacweb;

import com.iacweb.iacweb.controller.CategoryControllerTest;
import com.iacweb.iacweb.controller.CustomerControllerTest;
import com.iacweb.iacweb.controller.DiscountControllerTest;
import com.iacweb.iacweb.controller.ProductControllerTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.Result;

@SpringBootTest
class IacwebApplicationTests {

	@Test
	void contextLoads() {
        Result categoryResult = JUnitCore.runClasses(CategoryControllerTest.class);
        Result customerResult = JUnitCore.runClasses(CustomerControllerTest.class);
        Result discountResult = JUnitCore.runClasses(DiscountControllerTest.class);
        Result productResult = JUnitCore.runClasses(ProductControllerTest.class);

        for (Failure failure : categoryResult.getFailures()) {
            System.out.println(failure.toString());
        }
        for (Failure failure : customerResult.getFailures()) {
            System.out.println(failure.toString());
        }
        for (Failure failure : discountResult.getFailures()) {
            System.out.println(failure.toString());
        }
        for (Failure failure : productResult.getFailures()) {
            System.out.println(failure.toString());
        }
            System.out.println(categoryResult.wasSuccessful());
            System.out.println(customerResult.wasSuccessful());
            System.out.println(discountResult.wasSuccessful());
            System.out.println(productResult.wasSuccessful());
        }
	}
 