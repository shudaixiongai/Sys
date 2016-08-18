package com.corp.mms.checkno.servlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final char[] CHARS = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public static Random random = new Random();

	public static String getRandomString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 5; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

	public static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	public static Color getReverseColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Expires", "0");
		response.setContentType("image/jpeg");
		String radomString = getRandomString();

		request.getSession().setAttribute("checkCode", radomString);

		Color color = getRandomColor();
		Color reverse = getReverseColor(color);
		BufferedImage bi = new BufferedImage(100, 30, 1);
		Graphics2D g = bi.createGraphics();
		g.setFont(new Font("SansSerif", 1, 22));

		g.setColor(new Color(244, 244, 244));
		g.fillRect(0, 0, 100, 35);
		g.setColor(reverse);
		g.drawString(radomString, 15, 22);
		int i = 0;
		for (int n = random.nextInt(100); i < n; i++) {
			g.drawRect(random.nextInt(100), random.nextInt(30), 1, 1);
		}
		ServletOutputStream out = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(bi);
		out.flush();
	}
}