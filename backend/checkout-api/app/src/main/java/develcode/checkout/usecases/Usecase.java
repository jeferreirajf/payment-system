package develcode.checkout.usecases;

public interface Usecase<Input, Output> {

    Output execute(final Input input);

}
