package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_del extends AbstractRedisOperation {
    RO_del(RedisBase base, List<Slice> params) {
        super(base, params, null, 0, null);
    }

    @Override
    Slice response() {
        int count = 0;
        for (Slice key : params()) {
            Slice value = base().rawGet(key);
            base().del(key);
            if (value != null) {
                count++;
            }
        }
        return Response.integer(count);
    }
}
