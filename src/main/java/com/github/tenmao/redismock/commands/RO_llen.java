package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

import static com.github.tenmao.redismock.Utils.deserializeObject;

class RO_llen extends AbstractRedisOperation {
    RO_llen(RedisBase base, List<Slice> params) {
        super(base, params, 1, null, null);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        Slice data = base().rawGet(key);
        LinkedList<Slice> list;
        if (data != null) {
            list = deserializeObject(data);
        } else {
            list = Lists.newLinkedList();
        }
        return Response.integer(list.size());
    }
}
