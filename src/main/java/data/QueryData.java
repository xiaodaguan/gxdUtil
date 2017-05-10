package data;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by guanxiaoda on 17/5/10.
 * not used
 */
public class QueryData {
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
