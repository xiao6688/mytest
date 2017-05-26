package com.itheima.solr;


import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class QueryDocument {
	
	//通过solrJ进行文旦过的查询
	@Test
	public void testQuery() throws Exception{
		//创建solrServer对象建立连接
		SolrServer server=new HttpSolrServer("http://localhost:8080/solr/collection1");
		//创建一个solrquery对象
		SolrQuery query=new SolrQuery();
		//设置查询条件
		query.setQuery("小黄人");
		//设置过滤条件
		query.addFilterQuery("product_catalog_name:幽默杂货");
		//设置排序的条件
		query.setSort("product_price",ORDER.asc);
		//分页处理
		query.setStart(0);
		query.setRows(5);
		//设置结果域中的列表
		query.setFields("id","product_name","product_price");
		//设置默认搜索域
		query.set("df","product_keywords");
		//开启高亮
		query.setHighlight(true);
		//设置高亮显示的域
		query.addHighlightField("product_name");
		//设置高亮显示的前缀
		query.setHighlightSimplePre("<em>");
		//设置高亮显示的后缀
		query.setHighlightSimplePost("</em>");
		//执行查询
		QueryResponse queryResponse=server.query(query);
		//获取查询结果
		SolrDocumentList documentList=queryResponse.getResults();
		//显示查询到的数量
		System.out.println("查到的结果数："+documentList.getNumFound());
		//取高亮结果
		Map<String, Map<String, List<String>>> highlighting=queryResponse.getHighlighting();
		//遍历查询到的结果
		for (SolrDocument solrDocument : documentList) {
			//显示查询到的id
			
			//System.out.println(solrDocument.get("id"));
			//取高亮结果
			String name="";
			List<String> list=highlighting.get(solrDocument.get("id")).get("product_name");
			//判断集合是否是空
			if(list!=null && list.size()>0){
				name=list.get(0);
			}else{
				name=(String) solrDocument.get("product_name");
			}
			
			//显示查找的结果
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("product_name"));
			System.out.println(solrDocument.get("product_price"));
			System.out.println("========================");
		}
		
		
	}

}
