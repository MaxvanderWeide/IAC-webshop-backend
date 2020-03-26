package com.iac.webshop.logical.controller;


import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class DefaultController {

    @OpenApi(
            summary = "Get all defaults",
            operationId = "testDefaults",
            path = "/defaults",
            method = HttpMethod.GET,
            tags = {"Default"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = String[].class)})
            }
    )
    public static void testDefaults(Context ctx) {
        List<String> defaults = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            defaults.add("Default" + i);
        }
        ctx.json(defaults);
    }
}
