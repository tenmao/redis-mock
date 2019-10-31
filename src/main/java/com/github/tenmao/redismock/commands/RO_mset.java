package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

class RO_mset extends AbstractRedisOperation {
    RO_mset(RedisBase base, List<Slice> params) {
        super(base, params, null, 0, 2);
    }

    @Override
    Slice response() {
        for (int i = 0; i < params().size(); i += 2) {
            base().rawPut(params().get(i), params().get(i + 1), -1L);
        }
        return Response.OK;
    }
}
