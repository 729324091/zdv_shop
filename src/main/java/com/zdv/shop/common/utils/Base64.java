package com.zdv.shop.common.utils;

/**
 *
 * @author shuxj
 * @version 1.0
 */

public class Base64
{

    /** Base64*/
    private static char Base64Code[] =
        {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
        'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
        'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '+', '/',
    };

    /** Base64����?*/
    private static final byte Base64Decode[] =
    {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
        55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
        5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
        24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34,
        35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51
    };

    /**
     */
    public Base64()
    {}

    /**
     * Base64
     */
    public static String encode(byte[] b)
    {
        int code = 0;


        StringBuffer sb = new StringBuffer( ( (b.length - 1) / 3) << 2 + 4);


        for (int i = 0; i < b.length; i++)
        {
            code |= (b[i] << (16 - i % 3 * 8)) & (0xff << (16 - i % 3 * 8));
            if (i % 3 == 2 || i == b.length - 1)
            {
                sb.append(Base64Code[ (code & 0xfc0000) >>> 18]);
                sb.append(Base64Code[ (code & 0x3f000) >>> 12]);
                sb.append(Base64Code[ (code & 0xfc0) >>> 6]);
                sb.append(Base64Code[code & 0x3f]);
                code = 0;
            }
        }

        if (b.length % 3 > 0)
        {
            sb.setCharAt(sb.length() - 1, '=');
        }
        if (b.length % 3 == 1)
        {
            sb.setCharAt(sb.length() - 2, '=');
        }
        return sb.toString();
    }

    public static byte[] decode(String s)
    {
        int sLen = s.length();
        int numGroups = sLen / 4;
        if (4 * numGroups != sLen)
        {
            throw new IllegalArgumentException(
                "String length must be a multiple of four.");
        }
        int missingBytesInLastGroup = 0;
        int numFullGroups = numGroups;
        if (sLen != 0)
        {
            if (s.charAt(sLen - 1) == '=')
            {
                missingBytesInLastGroup++;
                numFullGroups--;
            }
            if (s.charAt(sLen - 2) == '=')
            {
                missingBytesInLastGroup++;
            }
        }
        byte[] result = new byte[3 * numGroups - missingBytesInLastGroup];

        // Translate all full groups from base64 to byte array elements
        int inCursor = 0, outCursor = 0;
        for (int i = 0; i < numFullGroups; i++)
        {
            int ch0 = base64toInt(s.charAt(inCursor++));
            int ch1 = base64toInt(s.charAt(inCursor++));
            int ch2 = base64toInt(s.charAt(inCursor++));
            int ch3 = base64toInt(s.charAt(inCursor++));
            result[outCursor++] = (byte) ( (ch0 << 2) | (ch1 >> 4));
            result[outCursor++] = (byte) ( (ch1 << 4) | (ch2 >> 2));
            result[outCursor++] = (byte) ( (ch2 << 6) | ch3);
        }

        // Translate partial group, if present
        if (missingBytesInLastGroup != 0)
        {
            int ch0 = base64toInt(s.charAt(inCursor++));
            int ch1 = base64toInt(s.charAt(inCursor++));
            result[outCursor++] = (byte) ( (ch0 << 2) | (ch1 >> 4));

            if (missingBytesInLastGroup == 1)
            {
                int ch2 = base64toInt(s.charAt(inCursor++));
                result[outCursor++] = (byte) ( (ch1 << 4) | (ch2 >> 2));
            }
        }
        // assert inCursor == s.length()-missingBytesInLastGroup;
        // assert outCursor == result.length;
        return result;
    }

    private static int base64toInt(char c)
    {
        int result = Base64Decode[c];
        if (result < 0)
        {
            throw new IllegalArgumentException("Illegal character " + c);
        }
        return result;
    }
}
