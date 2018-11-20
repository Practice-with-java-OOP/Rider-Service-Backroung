package shippo.global.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GroupConsumer {
    static Logger LOG = LoggerFactory.getLogger(GroupConsumer.class);
    private String zookeeper;
    private String groupId;
    private int nOfThreads;
    List<SingleConsumer> consumers;

    public GroupConsumer(String zookeeper, String groupId, int nOfThreads){
        consumers = new ArrayList<SingleConsumer>();
        for(int i = 0; i < nOfThreads; i++){

        }
    }

}
