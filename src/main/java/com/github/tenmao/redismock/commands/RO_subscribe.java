package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.RedisClient;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_subscribe extends AbstractRedisOperation {
    private final RedisClient client;

    RO_subscribe(RedisBase base, RedisClient client, List<Slice> params) {
        super(base, params, null, 0, null);
        this.client = client;
    }

    @Override
    Slice response() {
        params().forEach(channel -> base().addSubscriber(channel, client));
        List<Slice> numSubscriptions = base().getSubscriptions(client);

        return Response.subscribedToChannel(numSubscriptions);
    }
}
