package pl.filipiak.jakub.training.springtestcontainers.utils;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object searchParam) {
        super(prepareMessage(searchParam));
    }

    private static String prepareMessage(Object searchParam) {
        return "Could not find any resource with a parameter value " +
                "'" + searchParam.toString() + "'";
    }
}
