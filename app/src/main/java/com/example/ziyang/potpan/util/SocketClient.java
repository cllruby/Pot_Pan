package com.example.ziyang.potpan.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import static com.example.ziyang.potpan.zzy_constants.IP;
import static com.example.ziyang.potpan.zzy_constants.POINT;
import static com.example.ziyang.potpan.zzy_constants.SOCKET_ERROR;
import static com.example.ziyang.potpan.zzy_constants.SOCKET_IOERROR;

/**
 * Created by Ziyang on 2016/5/11.
 */
public class SocketClient {
    static Socket s;
    private static DataInputStream din;
    private static DataOutputStream dout;
    public static String readinfo;
    public static byte[] data = null;
    static String getinfo;

    //发送字符串
    public static void ConnectSevert(String info) {
        try {
            InetAddress serverAddr = InetAddress.getByName(IP);
            s = new Socket(serverAddr,9999);
//            s.connect(new InetSocketAddress(IP, POINT), 5000);
        } catch (SocketTimeoutException e) {
            if (!s.isConnected()) {
                readinfo = SOCKET_ERROR;
            }
            return;
        } catch (IOException e) {
            if (!s.isConnected()) {
                readinfo = SOCKET_ERROR;
            }
            return;
        }
        try {
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            info = MyConverter.escape(info);//编码
            dout.writeInt(info.length());
            dout.write(info.getBytes());
            getinfo = din.readUTF();
            if (getinfo.equals("STR")) {
                readinfo = IOUtil.readstr(din);
            } else if (getinfo.equals("BYTE")) {
                data = IOUtil.readBytes(din);
            }
        } catch (Exception e) {
            if (!s.isClosed() && s.isConnected()) {
                readinfo = SOCKET_IOERROR;
                System.out.println("读取数据超时...");
            }
            return;
        } finally {
            try {
                dout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                din.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //发送图片
    public static void ConnectSevertBYTE(String info, String mz, byte[] data) {
        try {
            s = new Socket(IP, POINT);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            int len = data.length;
            info = MyConverter.escape(info);//编码
            dout.writeInt(info.length());
            dout.write(info.getBytes());
            dout.writeUTF(mz);
            dout.writeInt(len);
            dout.write(data);
            getinfo = din.readUTF();//读取输入流数据
            if (getinfo.equals("STR")) {
                readinfo = IOUtil.readstr(din);
            } else if (getinfo.equals("BYTE")) {
                data = IOUtil.readBytes(din);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                din.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

