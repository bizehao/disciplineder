package com.bzh.disciplineder.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Base64;

/**
 * @author 毕泽浩
 * @Description: 工具类
 * @time 2018/10/11 9:22
 */
public class CommonUtils {

	/***
	 * 图片转字节数组
	 * @param url 图片地址
	 * @return
	 */
	public static String imgtobyte(String url) {
		if(url != null){
			File file = new File(url);
			if (file.exists()) {
				try {
					FileInputStream in = new FileInputStream(file);
					FileChannel channel = in.getChannel();
					ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
					channel.read(buffer);
					in.close();
					byte[] mm = buffer.array();
					//编码
					Base64.Encoder encoder = Base64.getEncoder();
					String pic = encoder.encodeToString(mm).trim();
					//解码
					//Base64.Decoder decoder = Base64.getDecoder();
					/*byte[] sd = decoder.decode(nn);
					System.out.println(Arrays.toString(sd));*/
					return pic;
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return null;
			}
		}else {
			return null;
		}
	}
}
