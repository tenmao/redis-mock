package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

import static com.github.tenmao.redismock.Utils.convertToLong;

class RO_pexpire extends AbstractRedisOperation {
    RO_pexpire(RedisBase base, List<Slice> params) {
        super(base, params, 2, null, null);
    }

    long getValue(List<Slice> params) {
        return convertToLong(new String(params.get(1).data()));
    }

    @Override
    Slice response() {
        return Response.integer(base().setTTL(params().get(0), getValue(params())));
    }
}
