package com.company.util;

import java.io.*;
import java.util.Vector;

public class FileUtil {
    public  static final int bufferSize = 10;
    private File readFile;
    private InputStreamReader in;
    private Vector<char[]> buffers;
    private int forward = 0;
    private int fwBufferId = 0;
    private char cur_char = 0;
    private boolean end = false;

    public boolean isEnd() { return end; }
    public FileUtil(String readPath, String writePath) throws IOException {
        readFile = new File(readPath);
        in = new InputStreamReader(new FileInputStream(readFile));
        buffers = new Vector<char[]>();
        char[] buffer1 = new char[FileUtil.bufferSize];
        readNextBuffer(buffer1);
        char[] buffer2 = new char[FileUtil.bufferSize];
        buffers.add(buffer1);
        buffers.add(buffer2);
    }

    public boolean getNextBuffer() throws IOException {
        this.fwBufferId = (this.fwBufferId + 1) % 2;
        if (!readNextBuffer(this.buffers.get(this.fwBufferId)))
            return false;
        this.forward = 0;
        return true;
    }

    public boolean readNextBuffer(char[] buffer) throws IOException {
        int readNum = in.read(buffer);
        if (readNum <= 0)
            return false;
        try {
            if (readNum < buffer.length) {
                buffer[readNum] = 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return true;
    }

    public char getNextChar() throws IOException {
        if (this.forward == FileUtil.bufferSize) {
            if (!getNextBuffer()) {
                end = true;
                return 0;
            }
        } else if (this.buffers.get(this.fwBufferId)[this.forward] == 0) { // 0 means eof
            end = true;
            return 0;
        }
        cur_char = this.buffers.get(this.fwBufferId)[this.forward];
        this.forward++;
        return cur_char;
    }
}
