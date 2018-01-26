package cc.ifnot.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

    private ChannelHandlerContext ctx;
    private ByteBuf content;
    private ChannelFutureListener trafficGenerator = future -> {
        if (future.isSuccess()) {
            generateTraffic();
        } else {
            future.cause().printStackTrace();
            future.channel().close();
        }
    };

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        super.channelActive(ctx);
        this.ctx = ctx;
        content = ctx.alloc().directBuffer(DiscardClient.SIZE).writeZero(DiscardClient.SIZE);

        generateTraffic();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
//        super.channelInactive(ctx);
        content.release();
    }

    private void generateTraffic() {
        ctx.writeAndFlush(content.duplicate().retain()).addListener(trafficGenerator);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        // Server is supposed to send nothing, but if it sends something, discard it.
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
