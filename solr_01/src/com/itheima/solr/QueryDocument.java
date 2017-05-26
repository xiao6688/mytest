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
	
	//ͨ��solrJ�����ĵ����Ĳ�ѯ
	@Test
	public void testQuery() throws Exception{
		//����solrServer����������
		SolrServer server=new HttpSolrServer("http://localhost:8080/solr/collection1");
		//����һ��solrquery����
		SolrQuery query=new SolrQuery();
		//���ò�ѯ����
		query.setQuery("С����");
		//���ù�������
		query.addFilterQuery("product_catalog_name:��Ĭ�ӻ�");
		//�������������
		query.setSort("product_price",ORDER.asc);
		//��ҳ����
		query.setStart(0);
		query.setRows(5);
		//���ý�����е��б�
		query.setFields("id","product_name","product_price");
		//����Ĭ��������
		query.set("df","product_keywords");
		//��������
		query.setHighlight(true);
		//���ø�����ʾ����
		query.addHighlightField("product_name");
		//���ø�����ʾ��ǰ׺
		query.setHighlightSimplePre("<em>");
		//���ø�����ʾ�ĺ�׺
		query.setHighlightSimplePost("</em>");
		//ִ�в�ѯ
		QueryResponse queryResponse=server.query(query);
		//��ȡ��ѯ���
		SolrDocumentList documentList=queryResponse.getResults();
		//��ʾ��ѯ��������
		System.out.println("�鵽�Ľ������"+documentList.getNumFound());
		//ȡ�������
		Map<String, Map<String, List<String>>> highlighting=queryResponse.getHighlighting();
		//������ѯ���Ľ��
		for (SolrDocument solrDocument : documentList) {
			//��ʾ��ѯ����id
			
			//System.out.println(solrDocument.get("id"));
			//ȡ�������
			String name="";
			List<String> list=highlighting.get(solrDocument.get("id")).get("product_name");
			//�жϼ����Ƿ��ǿ�
			if(list!=null && list.size()>0){
				name=list.get(0);
			}else{
				name=(String) solrDocument.get("product_name");
			}
			
			//��ʾ���ҵĽ��
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("product_name"));
			System.out.println(solrDocument.get("product_price"));
			System.out.println("========================");
		}
		
		
	}

}
