package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.List;

import static com.github.tenmao.redismock.Utils.convertToLong;

class RO_setex extends RO_set {
    RO_setex(RedisBase base, List<Slice> params) {
        super(base, params, 3);
    }

    RO_setex(RedisBase base, List<Slice> params, Integer expectedParams) {
        super(base, params, expectedParams);
    }

    @Override
    long valueToSet(List<Slice> params) {
        return convertToLong(new String(params.get(1).data())) * 1000;
    }

    @Override
    Slice response() {
        base().rawPut(params().get(0), params().get(2), valueToSet(params()));
        return Response.OK;
    }
}
