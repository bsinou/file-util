# file-util-merger
Simple bundle to perform merge and clean operation on the local file system.

## How-to

To launch the job:

```
cd path/to/your/project
java -cp build/classes:build/libs/* org.springframework.batch.core.launch.support.CommandLineJobRunner net.sinou.file.merger.jobs.ImportFileInfoBatchConfiguration readDirectoryContentJob
```