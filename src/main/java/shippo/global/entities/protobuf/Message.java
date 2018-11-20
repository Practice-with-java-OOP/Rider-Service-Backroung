package shippo.global.entities.protobuf;

public class Message {
    private String type;

    private String action;

    private String state;

    private String data;

    public Message(String type, String action, String state, String data) {
        this.type = type;
        this.action = action;
        this.state = state;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", state='" + state + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
