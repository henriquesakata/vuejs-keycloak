package hs.research.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        final Router router = Router.router(vertx);
        configureSecurity(router);
        router.mountSubRouter("/api/services", new RestServices(vertx).getRouter());
        router.route("/*").handler(StaticHandler.create());
        configureAndStartServer(router);
    }

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
