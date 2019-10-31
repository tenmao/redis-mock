package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.LinkedList;
import java.util.List;

import static com.github.tenmao.redismock.Utils.convertToInteger;
import static com.github.tenmao.redismock.Utils.deserializeObject;

class RO_lindex extends AbstractRedisOperation {
    RO_lindex(RedisBase base, List<Slice> params) {
        super(base, params, 2, null, null);
    }

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
        int index = convertToInteger(params().get(1).toString());
        if (index < 0) {
            index = list.size() + index;
            if (index < 0) {
                return Response.NULL;
            }
        }
        if (index >= list.size()) {
            return Response.NULL;
        }
        return Response.bulkString(list.get(index));
    }
}
