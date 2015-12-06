package com.liberty.lucene.search;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;

public class IndexSearcherUtil {

	private static IndexWriter indexWriter;
	
	private static IndexSearcher indexSearcher;
	
	private static boolean updataFlag = false;
	
	/**
	 * 获取IndexSearcher
	 * @return
	 * @throws IOException
	 */
	public static synchronized IndexSearcher getSearcher() {
		if(indexSearcher == null || updataFlag == true){
			try {
				indexSearcher = new IndexSearcher(DirectoryReader.open(indexWriter.getDirectory()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updataFlag = false;
		}
		return indexSearcher;
	}
	
	/**
	 * 更新索引后更新，使得getSearcher()方法能够获取新的IndexSearcher
	 */
	public static void updateSearcher(){
		updataFlag = true;
	}
}
