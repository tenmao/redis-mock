package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Slice;

import java.util.List;

import static com.github.tenmao.redismock.Utils.convertToLong;

class RO_decrby extends RO_incrOrDecrBy {
    RO_decrby(RedisBase base, List<Slice> params) {
        super(base, params, 2);
    }

    RO_decrby(RedisBase base, List<Slice> params, Integer expectedParams) {
        super(base, params, expectedParams);
    }

    @Override
    long incrementOrDecrementValue(List<Slice> params) {
        return convertToLong(String.valueOf(params.get(1))) * -1;
    }
}
