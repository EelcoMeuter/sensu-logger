package eu.panic.dont.sensu;

/**
 * Sensu DTO that contains the name, output and status of the message. The type is defaulted to <em>metric</em> and
 * the handler to <em>flapjack</em>.
 */
public class Message {

    private String name;

    private String output;

    private Status status;

    private String handler;

    private String type;

    public Message(String name, String output, Status status) {
        this.name = name;
        this.output = output;
        this.status = status;
        this.type = "metric";
        this.handler = "flapjack";
    }

    public Message(String name, String message) {
        this(name, message, Status.CRITICAL);
    }

    public String getName() {
        return name;
    }

    public String getOutput() {
        return output;
    }

    public int getStatus() {
        return status.ordinal();
    }

    public String getHandler() {
        return handler;
    }

    public String getType() {
        return type;
    }
}
