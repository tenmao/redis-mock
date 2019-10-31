package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

import static com.github.tenmao.redismock.Utils.convertToLong;

abstract class RO_incrOrDecrBy extends AbstractRedisOperation {
    RO_incrOrDecrBy(RedisBase base, List<Slice> params, Integer expectedParams) {
        super(base, params, expectedParams, null, null);
    }

    abstract long incrementOrDecrementValue(List<Slice> params);

    @Override
    Slice response() {
        Slice key = params().get(0);
        long d = incrementOrDecrementValue(params());
        Slice v = base().rawGet(key);
        if (v == null) {
            base().rawPut(key, new Slice(String.valueOf(d)), -1L);
            return Response.integer(d);
        }

        long r = convertToLong(new String(v.data())) + d;
        base().rawPut(key, new Slice(String.valueOf(r)), -1L);
        return Response.integer(r);
    }
}
