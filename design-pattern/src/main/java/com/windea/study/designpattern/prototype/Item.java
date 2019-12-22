package com.windea.study.designpattern.prototype;

import java.io.*;

class Item implements Cloneable {
	@Override
	public Item clone() throws CloneNotSupportedException {
		return (Item) super.clone();
	}

	public Item deepClone() throws Exception {
		//序列化
		var bos = new ByteArrayOutputStream();
		var oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		oos.close();

		//反序列化
		var bis = new ByteArrayInputStream(bos.toByteArray());
		var ois = new ObjectInputStream(bis);
		var result = (Item) ois.readObject();
		ois.close();

		return result;
	}
}
