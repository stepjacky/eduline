package org.jackysoft.file;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;

public class ChannelManager {
    static final Logger logger = LoggerFactory.getLogger(ChannelManager.class);
   
    static final BlockingQueue<CMD> queue=new ArrayBlockingQueue<>(1000);
    private  ChannelHandlerContext ctx;
    static final String END_LINE = "\r\n";
	private static ChannelManager INST=null;
	
	private ChannelManager(){
		new Thread(new Runnable(){

			@Override
			public void run() {
				logger.info("文档转换队列开始等待.. ");
				while(true){
					CMD cmd = queue.poll();
					if(cmd!=null){
						logger.info("消费一个命令.. "+cmd);
						ChannelManager.getManager().doCommand(cmd);
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						logger.error(e.getMessage());
					}
				}
				
			}
			
		}).start();
		
	}
	
	public static final ChannelManager getManager(){
		if(INST==null)INST=new ChannelManager();
		return INST;
	}
	
	
	
    protected void setContext(ChannelHandlerContext ctx){
    	if(this.ctx==null) {
    		
    		this.ctx = ctx;
    		logger.info(" a new channel context was added " + ctx.name());
    	}
    }
    
    public ChannelManager addCMD(CMD cmd){
    	if(!queue.offer(cmd)){
    	   logger.info("insert cmd unsuccessfully! ");	
    	}
    	return this;
    }
    
    protected void doCommand(CMD cmd){
    	if(ctx!=null){
    		logger.info("cmd was set ",cmd);
    	    ctx.write(cmd.toCommand());
    	    ctx.write(END_LINE);
    	    ctx.flush();
    	}else{
    		logger.info("please set channel handler context first");
    	}
    }
    
      
}
