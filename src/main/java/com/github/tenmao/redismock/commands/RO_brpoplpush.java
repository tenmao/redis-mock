package com.github.tenmao.redismock.commands;

import com.github.tenmao.redismock.RedisBase;
import com.github.tenmao.redismock.Response;
import com.github.tenmao.redismock.Slice;
import com.github.tenmao.redismock.SliceParser;

import java.util.Arrays;
import java.util.List;

import static com.github.tenmao.redismock.Utils.convertToLong;

class RO_brpoplpush extends RO_rpoplpush {
    private long count = 0L;

    RO_brpoplpush(RedisBase base, List<Slice> params) {
        //NOTE: The minimum number of arguments is 1 because this mock is used for brpoplpush as well which takes in 3 arguments
        super(base, params, 3);
    }

    @Override
    void doOptionalWork() {
        Slice source = params().get(0);
        long timeout = convertToLong(params().get(2).toString());

        //TODO: Remove active block dumb.
        long currentSleep = 0L;
        while (count == 0L && currentSleep < timeout * 1000) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentSleep = currentSleep + 100;
            count = getCount(source);
        }
    }

    @Override
    Slice response() {
        if (count != 0) {
            return super.response();
        } else {
            return Response.NULL;
        }
    }

    private long getCount(Slice source) {
        Slice index = new Slice("0");
        List<Slice> commands = Arrays.asList(source, index, index);
        Slice result = new com.github.tenmao.redismock.commands.RO_lrange(base(), commands).execute();
        return SliceParser.consumeCount(result.data());
    }
}
