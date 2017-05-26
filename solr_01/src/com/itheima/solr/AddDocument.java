package com.itheima.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class AddDocument {
	@Test
	public void testAddDoctment() throws Exception{
		//����һ��SolrServer�����൱�ں�solr�������������ӣ�solrServer�ǳ����࣬Ҫʹ��httpserver��
		//�����Ƿ�������url,Ĭ����collection1
		SolrServer server=new HttpSolrServer("http://localhost:8080/solr/collection1");
		//����һ�������ĵ�����
		SolrInputDocument document=new SolrInputDocument();
		//���ĵ��������  ע��:ÿ���ĵ�������id,ÿ���������schema.txt�ж���
		document.addField("id","33");
		document.addField("title", "����ӵ��ļ�");
		//���ĵ�����д��������
		server.add(document);
		//�ύ
		server.commit();
		System.out.println("��ӳɹ���1111111");
	}
	
	//ɾ���ĵ�����
	@Test
	public void deleteDocment() throws Exception{
		SolrServer server=new HttpSolrServer("http://localhost:8080/solr/collection1");
		server.deleteById("11");
		
		server.commit();
		System.out.println("ɾ���ɹ���");
	}
	
	//ͨ����luneceѯ�﷨��ѯ
	@Test
	public void deleteByQuery() throws Exception{
		SolrServer server=new HttpSolrServer("http://localhost:8080/solr/collection1");
		//����lunece�﷨ɾ��
		server.deleteByQuery("id:22");
		server.commit();
		System.out.println("ɾ���ɹ���");
	}

}
