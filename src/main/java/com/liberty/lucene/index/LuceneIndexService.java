//package com.liberty.lucene.index;
//
//import java.io.IOException;
//
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.IndexWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.xdemo.example.LuceneIndexDemo.entity.News;
//
//@Service
//public class LuceneIndexService {
//
//	@Autowired
//	@Qualifier("indexWriter")
//	private IndexWriter indexWriter;
//	
//	public void addIndex(user){
//		Document doc = null;
//
//		for (News news : list) {
//			doc = new Document();
//			
//			Field title = new Field("title", news.getTitle(), Field.Store.YES,Field.Index.ANALYZED);
//			Field content = new Field("content", news.getContent(),Field.Store.YES, Field.Index.ANALYZED);
//			Field shortContent = new Field("shortContent", news.getShortContent(),Field.Store.YES, Field.Index.NO);
//			Field url = new Field("url", news.getUrl(), Field.Store.YES,Field.Index.NO);
//			Field date = new Field("date", news.getDate(), Field.Store.YES,Field.Index.NO);
//			doc.add(title);
//			doc.add(content);
//			doc.add(shortContent);
//			doc.add(url);
//			doc.add(date);
//			indexWriter.addDocument(doc);
//		}
//		indexWriter.commit();
//	}
//	
//	public void batchAddIndex(){
//		
//	}
//	
//	public void deleteIndex(){
//		indexWriter.deleteDocuments(term);
//	}
//	
//	/**
//	 * 删除索引索引
//	 * 
//	 */
//	public void deleteAllIndex(){
//		try {
//			indexWriter.deleteAll();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
