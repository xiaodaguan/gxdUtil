import oracle.net.jdbc.TNSAddress.AddressList;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guanxiaoda on 17/5/9.
 */
public class SolrUtil {
    private static Logger logger = LoggerFactory.getLogger(SolrUtil.class);

    private static SolrClient client = new CloudSolrClient("http://117.132.15.89:2181");


    private static void addList(List<String> list){
        int id = 0;
        for(String q:list){
            id++;

            if(id%100==0){
                logger.info("{} doc added.",id);
            }
            try {
                client.addBean(new QueryData(id,q));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SolrServerException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, SolrServerException {
        client
        List<String> queries = new ArrayList<String>();


        logger.info("reading file...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("inputFiles/SogouQ.sample"),"gb2312"));
        String line=null;
        while((line = reader.readLine())!=null){
            String query = RegexUtil.rExtract(line,"\\[.*\\]");
            queries.add(query);
        }
        reader.close();


        logger.info("adding beans...");
        addList(queries);

        logger.info("commiting...");
        client.commit();
        client.close();
        logger.info("done.");

    }


}

class QueryData{
    @Field
    int id;
    @Field
    String query;

    public QueryData(int id,String query){
        this.id = id;
        this.query = query;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}