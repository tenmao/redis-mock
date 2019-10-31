package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;
import com.google.common.collect.ImmutableList;

import java.util.List;

class RO_mget extends AbstractRedisOperation {
    RO_mget(RedisBase base, List<Slice> params) {
        super(base, params, null, 0, null);
    }

    @Override
    Slice response() {
        ImmutableList.Builder<Slice> builder = new ImmutableList.Builder<Slice>();
        for (Slice key : params()) {
            builder.add(Response.bulkString(base().rawGet(key)));

        }
        return Response.array(builder.build());
    }
}
