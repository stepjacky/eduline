package org.jackysoft.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class FileServer {
	static final Logger logger = LoggerFactory.getLogger(FileServer.class);
    public void start(String host,int port){
    	EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new FileClientInitializer());

            // Start the connection attempt.
            Channel ch;
		    ch = b.connect(host, port).sync().channel();
		    ch.closeFuture().sync();
           
        } catch (Exception e) {
        	logger.error(e.getMessage());
        	
		} 
        
        finally {
            group.shutdownGracefully();
        }
    }
}
