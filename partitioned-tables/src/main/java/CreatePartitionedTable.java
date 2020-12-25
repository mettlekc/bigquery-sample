import com.google.cloud.bigquery.*;

public class CreatePartitionedTable {

    /***
     *
     * @param args
     */
    public static void main(String ... args) {

        String datasetName = "hello_sample";
        String tableName = "MY_TABLE_NAME";

        try {

            BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

            TableId tableId = TableId.of(datasetName, tableName);

            TimePartitioning partitioning = TimePartitioning.of(TimePartitioning.Type.DAY);

            Schema schema = Schema.of(
                    Field.of("stringField", StandardSQLTypeName.STRING),
                    Field.of("booleanField", StandardSQLTypeName.BOOL),
                    Field.of("dateField", StandardSQLTypeName.DATE)
            );

            StandardTableDefinition tableDefinition =
                    StandardTableDefinition.newBuilder()
                            .setSchema(schema)
                            .setTimePartitioning(partitioning)
                            .build();

            TableInfo tableInfo = TableInfo.newBuilder(tableId, tableDefinition).build();

            bigquery.create(tableInfo);
            System.out.println("Partitioned table created successfully");

        } catch (BigQueryException e) {
            System.out.println("Partitioned table was not created. \n" + e.toString());
        }

    }
}
