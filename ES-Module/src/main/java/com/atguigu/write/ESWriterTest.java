package com.atguigu.write;

import com.atguigu.bean.Stu;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

public class ESWriterTest {

    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();

        jestClientFactory.setHttpClientConfig(
                new HttpClientConfig.Builder("http://hadoop102:9200").build()
        );

        JestClient jestClient = jestClientFactory.getObject();

        Stu stu = new Stu("006", "斯内普");

        Index index = new Index.Builder(stu).index("stu_temp_01").type("_doc").id("1006").build();

        jestClient.execute(index);

        jestClient.shutdownClient();
    }
}
