package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Slice;

import java.util.List;

import static com.github.tenmao.redismock.Utils.convertToLong;

class RO_psetex extends RO_setex {
    RO_psetex(RedisBase base, List<Slice> params) {
        super(base, params, 3);
    }

    @Override
    long valueToSet(List<Slice> params) {
        return convertToLong(new String(params.get(1).data()));
    }
}
