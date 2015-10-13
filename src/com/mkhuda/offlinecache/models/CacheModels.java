package com.mkhuda.offlinecache.models;

import java.io.IOException;

import android.content.Context;

import com.mkhuda.offlinecache.InternalStorage;

public class CacheModels {
	
	private String cache1;
	private String cache2;
	
	Context _context;
	
	public CacheModels(Context context) {
		this._context = context;
	}
	
	public CacheModels(String cache1, String cache2) {
		this.cache1 = cache1;
		this.cache2 = cache2;
	}
	
	public String getCache1() throws ClassNotFoundException, IOException {
		String a = (String) InternalStorage.readObject(_context, "John1");
		return a;
	}
	
	public String getCache2() throws ClassNotFoundException, IOException {
		String b = (String) InternalStorage.readObject(_context, "John2");
		return b;
		
	}
	
	public void setCache1(String object) {
		try {
			InternalStorage.writeObject(_context, "John1", object);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void setCache2(String object) {
		
		try {
			InternalStorage.writeObject(_context, "John2", object);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
