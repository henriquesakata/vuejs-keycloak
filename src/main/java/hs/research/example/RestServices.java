package hs.research.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class RestServices {

    private final Router router;

    public RestServices(final Vertx vertx) {
        this.router = Router.router(vertx);
        configure();
    }

    private void configure() {
        router.get("/v1/hello").handler(this::hello);
    }

    private void hello(final RoutingContext routingContext) {
        MultiMap headers = routingContext.request().headers();
        for (Entry<?, ?> entry : headers.entries()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Hello World - " + getCurrentDate());
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json; charset=utf-8")
                .putHeader("cache-control", "no-store, no-cache, must-revalidate")
                .end(new JsonObject(map).encode());
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public Router getRouter() {
        return router;
    }

}
