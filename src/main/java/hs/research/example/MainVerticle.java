package hs.research.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.JWTAuthHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        final Router router = Router.router(vertx);
        // router.get("/api/time").handler(this::currentTime);
        // router.route("/*").handler(StaticHandler.create());
        // final HttpServerOptions options = new HttpServerOptions().setCompressionSupported(true);
        // HttpServer httpServer = vertx.createHttpServer(options);
        // httpServer.requestHandler(router::accept).listen(8080, "0.0.0.0", asyncResult -> {
        // if (asyncResult.succeeded()) {
        // System.out.println("Server started on port " + asyncResult.result().actualPort());
        // } else {
        // asyncResult.cause().printStackTrace();
        // }
        // });
        configureSecurity(router);
        router.mountSubRouter("/api/services", new RestServices(vertx).getRouter());
        configureAndStartServer(router);
    }

    // private void currentTime(final RoutingContext routingContext) {
    // JsonObject jsonObject = new JsonObject();
    // jsonObject.put("current_time", new Date().toString());
    // routingContext.response()
    // .setStatusCode(200)
    // .putHeader("content-type", "application/json; charset=utf-8")
    // .putHeader("cache-control", "no-cache, must-revalidate")
    // .end(jsonObject.encode());
    // }

    private void configureAndStartServer(final Router router) {
        final HttpServerOptions options = new HttpServerOptions().setCompressionSupported(true);
        HttpServer httpServer = vertx.createHttpServer(options);
        httpServer.requestHandler(router::accept).listen(Integer.getInteger("server.port", 8080), System.getProperty("server.host", "0.0.0.0"));
    }

    private void configureSecurity(final Router router) {
        JsonObject config = new JsonObject();
        config.put("public-key", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrVrCuTtArbgaZzL1hvh0xtL5mc7o0NqPVnYXkLvgcwiC3BjLGw1tGEGoJaXDuSaRllobm53JBhjx33UNv+5z/UMG4kytBWxheNVKnL6GgqlNabMaFfPLPCF8kAgKnsi79NMo+n6KnSY8YeUmec/p2vjO2NjsSAVcWEQMVhJ31LwIDAQAB");
        JWTAuth authProvider = JWTAuth.create(vertx, config);
        router.route("/api/*").handler(JWTAuthHandler.create(authProvider));
    }

}
