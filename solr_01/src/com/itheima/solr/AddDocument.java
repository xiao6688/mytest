package com.itheima.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class AddDocument {
	@Test
	public void testAddDoctment() throws Exception{
		//创建一个SolrServer对象，相当于和solr服务器建立连接，solrServer是抽象类，要使用httpserver类
		//参数是服务器的url,默认是collection1
		SolrServer server=new HttpSolrServer("http://localhost:8080/solr/collection1");
		//创建一个输入文档对象
		SolrInputDocument document=new SolrInputDocument();
		//向文档中添加域  注意:每个文档必须有id,每个域必须在schema.txt中定义
		document.addField("id","33");
		document.addField("title", "新添加的文件");
		//把文档对象写入索引库
		server.add(document);
		//提交
		server.commit();
		System.out.println("添加成功！");
	}
	
	//删除文档对象
	@Test
	public void deleteDocment() throws Exception{
		SolrServer server=new HttpSolrServer("http://localhost:8080/solr/collection1");
		server.deleteById("11");
		
		server.commit();
		System.out.println("删除成功！");
	}
	
	//通过查lunece询语法查询
	@Test
	public void deleteByQuery() throws Exception{
		SolrServer server=new HttpSolrServer("http://localhost:8080/solr/collection1");
		//根据lunece语法删除
		server.deleteByQuery("id:22");
		server.commit();
		System.out.println("删除成功！");
	}

}
