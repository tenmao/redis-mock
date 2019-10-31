package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.LinkedList;
import java.util.List;

import static com.github.tenmao.redismock.Utils.deserializeObject;
import static com.github.tenmao.redismock.Utils.serializeObject;

abstract class RO_pop extends AbstractRedisOperation {
    RO_pop(RedisBase base, List<Slice> params) {
        super(base, params, 1, null, null);
    }

    abstract Slice popper(LinkedList<Slice> list);

    @Override
    Slice response() {
        Slice key = params().get(0);
        Slice data = base().rawGet(key);
        LinkedList<Slice> list;
        if (data != null) {
            list = deserializeObject(data);
        } else {
            return Response.NULL;
        }

        if (list.isEmpty()) {
            return Response.NULL;
        }
        Slice v = popper(list);
        base().rawPut(key, serializeObject(list), -1L);
        return Response.bulkString(v);
    }
}
