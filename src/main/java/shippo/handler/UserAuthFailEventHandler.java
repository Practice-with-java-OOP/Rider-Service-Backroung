package shippo.handler;

import com.avaje.ebean.EbeanServer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import shippo.global.CRUD;
import shippo.global.PostgressDbConf;
import shippo.global.ProtobufEventAbstractHandler;
import shippo.global.entities.auth_service.UsersAuth;
import shippo.global.entities.rider_service.Rider;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UserAuthFailEventHandler extends ProtobufEventAbstractHandler<UsersAuth> {

    protected BlockingQueue<ConsumerRecord<byte[], byte[]>> msgQueue = new LinkedBlockingQueue<>(1000);

    public UserAuthFailEventHandler() {
        LOG = LoggerFactory.getLogger("UserAuthFailEventHandler");
        typeClass = UsersAuth.class;
        startHandle();
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
    public void putMsg(ConsumerRecord<byte[], byte[]> record) {
        try {
            msgQueue.put(record);
            LOG.info("Put record {} to FailEventHandler", record);

        } catch (InterruptedException e) {
            LOG.error("Put record fail {}", e);
        }
    }

    public void parseEvent(ConsumerRecord<byte[], byte[]> record){
        super.parseEvent(record);
    }

    @Override
    protected void handleEvent(ConsumerRecord<byte[], byte[]> record) {
        EbeanServer server = PostgressDbConf.getRiderDb();

        switch (crudOperation){
            case 'c':
                Rider rider = server.find(Rider.class)
                        .where()
                        .and()
                        .eq("email",after.getEmail())
                        .eq("userName",after.getUsername())
                        .endAnd()
                        .findUnique();
                if(rider != null){
                    try{
                        CRUD.delete(rider,server);
                        LOG.info("Delete rider {} successfully",rider);
                    }catch (Exception e){
                        LOG.error("Can't delete rider {}", rider);
                    }
                }else{
                    LOG.info("Rider with username {}, email {} not found",after.getUsername(),after.getEmail());
                }
                break;
            case 'u':
                Rider rider1 = server.find(Rider.class)
                        .where()
                        .and()
                        .eq("userId",after.getId())
                        .endAnd()
                        .findUnique();
                if(rider1 != null){
                    rider1.setCreatedAt(after.getCreatedAt());
                    rider1.setUpdatedAt(after.getUpdatedAt());
                    rider1.setEmail(after.getEmail());
                    rider1.setUsername(after.getUsername());
                    rider1.setState(after.getState());
                    rider1.setVersion(after.getVersion());
                    try{
                        CRUD.update(rider1,server);
                        LOG.info("Rollback update rider {} successfully",rider1);
                    }catch(Exception e){
                        LOG.error("Can't rollback update rider {}",rider1);
                    }
                }else {
                    LOG.info("Rider with userId {} not found",after.getId());
                }
                break;
        }
    }

    @Override
    protected boolean needSync() {
        return true;
    }
}
