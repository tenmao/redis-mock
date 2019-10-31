package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.RedisClient;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;
import java.util.Set;

class RO_publish extends AbstractRedisOperation {

    RO_publish(RedisBase base, List<Slice> params) {
        super(base, params, 2, null, null);
    }

    @Override
    Slice response() {
        Slice channel = params().get(0);
        Slice message = params().get(1);

        Set<RedisClient> subscibers = base().getSubscribers(channel);

        subscibers.forEach(subscriber -> {
            Slice response = Response.publishedMessage(channel, message);
            subscriber.sendResponse(response, "contacting subscriber");
        });

        return Response.integer(subscibers.size());
    }
}
