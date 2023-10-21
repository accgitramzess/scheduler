package com.genericscheduler.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.math.BigDecimal;
import java.util.function.Function;


public class NettyClient {

    public void run() {
        /*EventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>()
            {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelHandler() {
                        @Override
                        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

                        }

                        @Override
                        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

                        }

                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

                        }
                    });
                }
            }
        );

        bootstrap.connect();*/

        MapperTest mapperTest = new MapperTest();
        BigDecimal defaultValue = new BigDecimal(String.valueOf(12.2));
        mapperTest.getValueOrDefault("Label3", bidDecimalCustomMapper("ASSET_ID_TEST", "Label3", defaultValue));
        mapperTest.getValueOrDefault("Label2", bidDecimalCustomMapper("ASSET_ID_TEST2", "Label2", defaultValue));
    }

    private Function<String, BigDecimal> bidDecimalCustomMapper(String assetId, String label, BigDecimal defaultValue) {
        return (initialValue) -> {
            try {
                return new BigDecimal(initialValue);
            } catch (Exception ex) {
                System.out.println("Can not convert Security AssetId = " + assetId +
                        " UdfSet Label = " + label + " and Value = " + initialValue +
                        " to " + BigDecimal.class.getName() );
            }

            return defaultValue;
        };
    }

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.run();
    }
}
