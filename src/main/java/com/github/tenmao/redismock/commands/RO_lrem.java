package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.github.tenmao.redismock.Utils.*;

class RO_lrem extends AbstractRedisOperation {
    RO_lrem(RedisBase base, List<Slice> params) {
        super(base, params, 3, null, null);
    }

    @Override
    Slice response() {
        Slice key = params().get(0);
        int numRemove = convertToInteger(new String(params().get(1).data()));
        Slice target = params().get(2);
        Slice data = base().rawGet(key);

        if (data == null) {
            return Response.integer(0);
        }

        LinkedList<Slice> list = deserializeObject(data);

        //Determine the directionality of the deletions
        int numRemoved = 0;
        Iterator<Slice> iterator;
        if (numRemove < 0) {
            iterator = list.descendingIterator();
        } else {
            iterator = list.iterator();
        }


        numRemove = Math.abs(numRemove);
        while (iterator.hasNext()) {
            Slice element = iterator.next();
            if (element.equals(target) && (numRemove == 0 || numRemoved < numRemove)) {
                iterator.remove();
                numRemoved++;
            }
        }

        base().rawPut(key, serializeObject(list), -1L);

        return Response.integer(numRemoved);
    }
}
