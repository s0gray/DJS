package com.ogray.djs.node.utils;

/**
 * 	Copyright (C) 2021 Sergey A. Salata. All rights reserved.
 *
 *	This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


@Slf4j
public class Utils {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String toHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }


    private static JsonParser jsonParser = new BasicJsonParser();

    public static Map<String, Object> parseJson(String json) throws JsonParseException {
        Map<String, Object> map = jsonParser.parseMap(json);
        return map;
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;

        // read bytes from the input stream and store them in buffer
        while ((len = in.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
        }

        return os.toByteArray();
    }

    public static void writeFile(byte[] data, String name) throws IOException {
        Files.write(Paths.get(name), data);
    }

    public static boolean createFolder(String name) {
        return new File(name).mkdirs();
    }

}
