package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.RedisClient;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_quit extends AbstractRedisOperation {
    private final RedisClient client;

    RO_quit(RedisBase base, RedisClient client, List<Slice> params) {
        super(base, params, 0, null, null);
        this.client = client;
    }

    @Override
    Slice response() {
        client.sendResponse(Response.clientResponse("quit", Response.OK), "quit");
        client.close();

        return Response.SKIP;
    }
}
