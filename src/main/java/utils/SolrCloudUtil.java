package utils;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/5/10.
 */
public class SolrCloudUtil {
    private static Logger logger = LoggerFactory.getLogger(SolrCloudUtil.class);

    private static CloudSolrClient client = new CloudSolrClient("117.132.15.89:2181");

    public static void main(String[] args) throws IOException, SolrServerException {


        client.setDefaultCollection("query_ext");

        /**
         * drop all
         */
//        client.deleteByQuery("*:*");
//        client.commit();


        /**
         * commit from file
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("inputFiles/SogouQ.reduced"),"gb2312"));
        String line = null;
        int lineNum = 0;
        while((line = reader.readLine())!=null){
            lineNum++;
            if(lineNum%100 == 0) {
                logger.info("processing: {}", lineNum);
                client.commit();
            }
            if(line.contains("\t")) {
                try {


                    String idStr = line.split("\t")[1];
                    String query = line.split("\t")[2];

                    long id = Long.parseLong(idStr);
                    query = query.startsWith("[") ? query.substring(1) : query;
                    query = query.endsWith("]") ? query.substring(0, query.length() - 1) : query;

                    SolrInputDocument doc = new SolrInputDocument();
                    doc.addField("id", id);
                    doc.addField("query", query);
                    client.add(doc);
                } catch (Exception e) {
                    logger.error("doc err: {}", line);
                }
            }
        }
        reader.close();

        client.commit();
        client.close();






    }
}
