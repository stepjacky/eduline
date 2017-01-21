package org.jackysoft.file;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class FileClientHandler extends SimpleChannelInboundHandler<String> {

	static final Log logger = LogFactory.getLog(FileClientHandler.class);

    public FileClientHandler() {
		super(true);		
	
	}



	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ChannelManager.getManager().setContext(ctx);
	}



	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		 logger.info(msg);       
		
	}



}
