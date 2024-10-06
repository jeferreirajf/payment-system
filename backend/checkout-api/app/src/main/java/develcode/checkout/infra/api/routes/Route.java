package develcode.checkout.infra.api.routes;

public interface Route<Request, Response> {

    Response handle(Request request);

}
