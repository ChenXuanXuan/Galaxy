package com.mex.GalaxyChain.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by LSJ on 16/11/13.
 */

public final class Base64 {
    private Base64() {
    }

    public static String decodeToString(byte[] in) {
        try {
            byte[] decode = decode(in, in.length);
            if (decode!=null){
                return new String(decode,"UTF-8");
            }
        } catch (UnsupportedEncodingException ignored) {
        }
        return null;
    }

    public static String decodeToString(String in) {
        return decodeToString(in.getBytes());
    }

    public static byte[] decode(byte[] in) {
        return decode(in, in.length);
    }

    public static byte[] decode(String in) {
        return decode(in.getBytes());
    }

    public static byte[] decode(byte[] in, int len) {
        // approximate output length
        int length = len / 4 * 3;
        // return an empty array on empty or short input without padding
        if (length == 0) {
            return new byte[]{0};
        }
        // temporary array
        byte[] out = new byte[length];
        // number of padding characters ('=')
        int pad = 0;
        byte chr;
        // compute the number of the padding characters
        // and adjust the length of the input
        for (;;len--) {
            chr = in[len-1];
            // skip the neutral characters
            if ((chr == '\n') || (chr == '\r') ||
                    (chr == ' ') || (chr == '\t')) {
                continue;
            }
            if (chr == '=') {
                pad++;
            } else {
                break;
            }
        }
        // index in the output array
        int outIndex = 0;
        // index in the input array
        int inIndex = 0;
        // holds the value of the input character
        int bits = 0;
        // holds the value of the input quantum
        int quantum = 0;
        for (int i=0; i<len; i++) {
            chr = in[i];
            // skip the neutral characters
            if ((chr == '\n') || (chr == '\r') ||
                    (chr == ' ') || (chr == '\t')) {
                continue;
            }
            if ((chr >= 'A') && (chr <= 'Z')) {
                // char ASCII value
                //  A    65    0
                //  Z    90    25 (ASCII - 65)
                bits = chr - 65;
            } else if ((chr >= 'a') && (chr <= 'z')) {
                // char ASCII value
                //  a    97    26
                //  z    122   51 (ASCII - 71)
                bits = chr - 71;
            } else if ((chr >= '0') && (chr <= '9')) {
                // char ASCII value
                //  0    48    52
                //  9    57    61 (ASCII + 4)
                bits = chr + 4;
            } else if (chr == '+' || chr == '-' ) {
                bits = 62;
            } else if (chr == '/' || chr == '_' ) {
                bits = 63;
            } else {
                return null;
            }
            // append the value to the quantum
            quantum = (quantum << 6) | (byte) bits;
            if (inIndex%4 == 3) {
                // 4 characters were read, so make the output:
                out[outIndex++] = (byte) (quantum >> 16);
                out[outIndex++] = (byte) (quantum >> 8);
                out[outIndex++] = (byte) quantum;
            }
            inIndex++;
        }
        if (pad > 0) {
            // adjust the quantum value according to the padding
            quantum = quantum << (6*pad);
            // make output
            out[outIndex++] = (byte) (quantum >> 16);
            if (pad == 1) {
                out[outIndex++] = (byte) (quantum >> 8);
            }
        }
        // create the resulting array
        byte[] result = new byte[outIndex];
        System.arraycopy(out, 0, result, 0, outIndex);
        return result;
    }

    /**
     * Lookup table for turning Base64 alphabet positions (6 bits)
     * into output bytes.
     */
    private static final byte ENCODE[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/',
    };

    /**
     * Lookup table for turning Base64 alphabet positions (6 bits)
     * into output bytes.
     */
    private static final byte ENCODE_WEBSAFE[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_',
    };


    public static String encode(String in){
        return encode(in.getBytes());
    }

    public static String encode(String in, boolean webSafe){
        return encode(in.getBytes(),webSafe);
    }

    public static String encode(byte[] in) {
        return encode(in,true);
    }

    public static String encode(byte[] in, boolean webSafe) {
        byte[] map = webSafe ? ENCODE_WEBSAFE : ENCODE;

        int length = (in.length + 2) * 4 / 3;
        byte[] out = new byte[length];
        int index = 0, end = in.length - in.length % 3;
        for (int i = 0; i < end; i += 3) {
            out[index++] = map[(in[i] & 0xff) >> 2];
            out[index++] = map[((in[i] & 0x03) << 4) | ((in[i+1] & 0xff) >> 4)];
            out[index++] = map[((in[i+1] & 0x0f) << 2) | ((in[i+2] & 0xff) >> 6)];
            out[index++] = map[(in[i+2] & 0x3f)];
        }
        switch (in.length % 3) {
            case 1:
                out[index++] = map[(in[end] & 0xff) >> 2];
                out[index++] = map[(in[end] & 0x03) << 4];
                out[index++] = '=';
                out[index++] = '=';
                break;
            case 2:
                out[index++] = map[(in[end] & 0xff) >> 2];
                out[index++] = map[((in[end] & 0x03) << 4) | ((in[end+1] & 0xff) >> 4)];
                out[index++] = map[((in[end+1] & 0x0f) << 2)];
                out[index++] = '=';
                break;
        }
        return new String(out, 0, index, Charset.forName("ASCII"));
    }
}
