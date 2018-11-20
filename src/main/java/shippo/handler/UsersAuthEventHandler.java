package shippo.handler;

import com.avaje.ebean.EbeanServer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import shippo.global.CRUD;
import shippo.global.DbzEventAbstractHandler;
import shippo.global.PostgressDbConf;
import shippo.global.entities.auth_service.UsersAuth;
import shippo.global.entities.rider_service.Rider;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UsersAuthEventHandler extends DbzEventAbstractHandler<UsersAuth> {

    protected BlockingQueue<ConsumerRecord<byte[], byte[]>> msgQueue = new LinkedBlockingQueue<>(1000);


    public UsersAuthEventHandler() {
        LOG = LoggerFactory.getLogger("UsersAuthEventHandling");
        typeClass = UsersAuth.class;
        startHandle();
    }

    @Override
    public void putMsg(ConsumerRecord<byte[], byte[]> record) {
        try {
            msgQueue.put(record);
            LOG.info("Put record {} to UsersAuthEventHandling", record);

        } catch (InterruptedException e) {
            LOG.error("Put record fail {}", e);
        }
    }

    public void parseEvent(ConsumerRecord<byte[], byte[]> record){
        super.parseEvent(record);
    }


    public void startHandle(){
        Thread thread = new Thread(){
            public void run(){
                ConsumerRecord<byte[], byte[]> record = null;
                while (true) {
                    try {
                        record = msgQueue.take();
                        LOG.info("Receive msg: {}",record);
                        parseEvent(record);
                    } catch (InterruptedException e) {
                        LOG.error("Error while get msg from queue: {}",e);
                    }

                }
            }
        };

        thread.start();
    }

    @Override
    protected void handleEvent() {
        if (!after.getSection().equals(UsersAuth.Section.RIDER)) return;

        EbeanServer server = PostgressDbConf.getRiderDb();

        Rider rider = server.find(Rider.class)
                .where()
                .eq("userName",after.getUsername())
                .eq("email",after.getEmail())
                .endAnd()
                .findUnique();
        if(rider != null){
            rider.setUserId(after.getId());
        }
        try{
            CRUD.update(rider,server);
            LOG.info("Update user_id rider successful! {}", rider.toString());
        }catch(Exception e){
            LOG.error("Can't update user_id for rider {}", e);
        }

    }
}
