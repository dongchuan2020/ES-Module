package com.atguigu.write;

import com.atguigu.bean.Stu;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;

import java.io.IOException;

public class ESWriterByBulkTest {

    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();

        jestClientFactory.setHttpClientConfig(
                new HttpClientConfig.Builder("http://hadoop102:9200").build()
        );

        JestClient jestClient = jestClientFactory.getObject();

        Stu stu1 = new Stu("012","纳威");
        Stu stu2 = new Stu("013", "金妮");

        //stu_temp_01
        Bulk.Builder builder = new Bulk.Builder();

        Index index1 = new Index.Builder(stu1).id("1012").build();
        Index index2 = new Index.Builder(stu2).id("1013").build();

        builder.defaultType("stu_temp_01");
        builder.defaultType("_doc");

        builder.addAction(index1);
        builder.addAction(index2);

        Bulk bulk = builder.build();

        jestClient.execute(bulk);

        jestClient.shutdownClient();
    }
}
