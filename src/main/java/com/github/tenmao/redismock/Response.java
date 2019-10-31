package com.github.tenmao.redismock;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaolu on 2015/4/20.
 */
public class Response {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Response.class);
    private static final String LINE_SEPARATOR = "\r\n";

    public static final Slice OK = new Slice("+OK" + LINE_SEPARATOR);
    public static final Slice NULL = new Slice("$-1" + LINE_SEPARATOR);
    public static final Slice SKIP = new Slice("Skip this submission");

    private Response() {
    }

    public static Slice bulkString(Slice s) {
        if (s == null) {
            return NULL;
        }
        ByteArrayDataOutput bo = ByteStreams.newDataOutput();
        bo.write(String.format("$%d%s", s.length(), LINE_SEPARATOR).getBytes());
        bo.write(s.data());
        bo.write(LINE_SEPARATOR.getBytes());
        return new Slice(bo.toByteArray());
    }

    public static Slice error(String s) {
        return new Slice(String.format("-%s%s", s, LINE_SEPARATOR));
    }

    public static Slice integer(long v) {
        return new Slice(String.format(":%d%s", v, LINE_SEPARATOR));
    }

    public static Slice array(List<Slice> values) {
        ByteArrayDataOutput bo = ByteStreams.newDataOutput();
        bo.write(String.format("*%d%s", values.size(), LINE_SEPARATOR).getBytes());
        for (Slice value : values) {
            bo.write(value.data());
        }
        return new Slice(bo.toByteArray());
    }

    public static Slice publishedMessage(Slice channel, Slice message) {
        Slice operation = SliceParser.consumeParameter("$7\r\nmessage\r\n".getBytes());

        List<Slice> slices = new ArrayList<>();
        slices.add(Response.bulkString(operation));
        slices.add(Response.bulkString(channel));
        slices.add(Response.bulkString(message));

        return array(slices);
    }

    public static Slice subscribedToChannel(List<Slice> channels) {
        Slice operation = SliceParser.consumeParameter("$9\r\nsubscribe\r\n".getBytes());

        List<Slice> slices = new ArrayList<>();
        slices.add(Response.bulkString(operation));
        channels.forEach(channel -> slices.add(bulkString(channel)));
        slices.add(Response.integer(channels.size()));

        return array(slices);
    }

    public static Slice unsubscribe(Slice channel, int remainingSubscriptions) {
        Slice operation = SliceParser.consumeParameter("$11\r\nunsubscribe\r\n".getBytes());

        List<Slice> slices = new ArrayList<>();
        slices.add(Response.bulkString(operation));
        slices.add(Response.bulkString(channel));
        slices.add(Response.integer(remainingSubscriptions));

        return array(slices);
    }

    public static Slice clientResponse(String command, Slice response) {
        String stringResponse = response.toString().replace("\n", "").replace("\r", "");
        if (!response.equals(SKIP)) {
            LOG.debug("Received command [" + command + "] sending reply [" + stringResponse + "]");
        }
        return response;
    }

}
