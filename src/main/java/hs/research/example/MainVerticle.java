package hs.research.example;

import java.util.Date;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        final Router router = Router.router(vertx);
        router.get("/api/time").handler(this::currentTime);
        router.route("/*").handler(StaticHandler.create());
        final HttpServerOptions options = new HttpServerOptions().setCompressionSupported(true);
        HttpServer httpServer = vertx.createHttpServer(options);
        httpServer.requestHandler(router::accept).listen(8080, "0.0.0.0", asyncResult -> {
            if (asyncResult.succeeded()) {
                System.out.println("Server started on port " + asyncResult.result().actualPort());
            } else {
                asyncResult.cause().printStackTrace();
            }
        });
    }

    private void currentTime(final RoutingContext routingContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("current_time", new Date().toString());
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json; charset=utf-8")
                .putHeader("cache-control", "no-cache, must-revalidate")
                .end(jsonObject.encode());
    }

}
