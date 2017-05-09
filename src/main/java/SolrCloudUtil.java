import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/10.
 */
public class SolrCloudUtil {

    private static CloudSolrClient client = new CloudSolrClient("117.132.15.89:2181");

    public static void main(String[] args) throws IOException, SolrServerException {
        client.setDefaultCollection("query_ext");
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id",1);
        doc.addField("query","试试");
        client.add(doc);

        client.commit();
        client.close();


    }
}
