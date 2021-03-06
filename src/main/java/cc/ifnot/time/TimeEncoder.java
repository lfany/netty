package cc.ifnot.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

//public class TimeEncoder extends ChannelOutboundHandlerAdapter {
//
//    @Override
//    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
////        super.write(ctx, msg, promise);
//        UnixTime m = (UnixTime) msg;
//        ByteBuf encoded = ctx.alloc().buffer(4);
//        encoded.writeInt((int) m.value());
//        ctx.write(encoded, promise);
//    }
//}

public class TimeEncoder extends MessageToByteEncoder<UnixTime> {

    @Override
    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
        out.writeInt((int) msg.value());
    }
}
