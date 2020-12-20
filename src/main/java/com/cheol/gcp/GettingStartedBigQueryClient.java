package com.cheol.gcp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GettingStartedBigQueryClient {
    /***
     * https://cloud.google.com/bigquery/docs/reference/libraries
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String... args) throws InterruptedException, IOException {
//        BigQuery bigquery = createBigQuery("C:/Users/cheol/gcp/my-helloworld-project-265416-0f6ea0b849ce.json");
        BigQuery bigquery = createBigQuery(null);

        selectQuerySample(bigquery);
    }

    public static BigQuery createBigQuery(String keyFilePath) throws IOException {
        if (keyFilePath != null) {
            File credentialsPath = new File(keyFilePath);
            GoogleCredentials credentials;

            try (FileInputStream serviceAccountStream = new FileInputStream(credentialsPath)) {
                credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);
            }
            return BigQueryOptions.newBuilder().setCredentials(credentials).build().getService();
        }
        return BigQueryOptions.getDefaultInstance().getService();
    }

    public static void selectQuerySample(final BigQuery bigquery) throws InterruptedException {
        String query = "SELECT int64_field_0, string_field_1 FROM `my-helloworld-project-265416.hello_sample.hello_sample_bigquery`";
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();

        // Print the results.
        for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
            for (FieldValue val : row) {
                System.out.printf("%s,", val.toString());
            }
            System.out.printf("\n");
        }
    }

}
