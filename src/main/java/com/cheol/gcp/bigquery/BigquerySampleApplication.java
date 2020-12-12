package com.cheol.gcp.bigquery;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BigquerySampleApplication {

    public static void main(String... args) throws InterruptedException, IOException {

        File credentialsPath = new File("C:/Users/cheol/gcp/my-helloworld-project-265416-0f6ea0b849ce.json");
        GoogleCredentials credentials;

        try (FileInputStream serviceAccountStream = new FileInputStream(credentialsPath)) {
            credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);
        }

//        BigQuery bigquery = BigQueryOptions.newBuilder().setCredentials(credentials).build().getService();
        BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

        // BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
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
