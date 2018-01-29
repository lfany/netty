package cc.ifnot.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

//public class TimeDecoder extends ByteToMessageDecoder {
//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
//        if (in.readableBytes() < 4) {
//            return;
//        }
//
//        out.add(in.readBytes(4));
//    }
//}

public class TimeDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
